package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.google.protobuf.Descriptors.EnumDescriptor
import com.google.protobuf.Descriptors.FieldDescriptor.Type
import com.google.protobuf.compiler.PluginProtos
import com.google.protobuf.fileDescriptorProto
import com.squareup.kotlinpoet.*
import dogacel.kotlinx.protobuf.gen.CodeGen.toGeneratedType
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlin.io.path.Path

/**
 * A class that generates the Kotlin code for the given [PluginProtos.CodeGeneratorRequest].
 * This [request] is sent via the protobuf compiler plugin.
 */
class CodeGenerator {
    private val typeNames: Map<Descriptors.Descriptor, TypeName>
    private val filesInOrder: List<Descriptors.FileDescriptor>

    constructor(request: PluginProtos.CodeGeneratorRequest) {
        // https://protobuf.dev/reference/java/api-docs/com/google/protobuf/compiler/PluginProtos.CodeGeneratorRequest
        // FileDescriptorProtos for all files in files_to_generate and everything
        // they import.  The files will appear in topological order, so each file
        // appears before any file that imports it.
        val files = mutableMapOf<String, Descriptors.FileDescriptor>()
        filesInOrder = request.protoFileList.map { file ->
            files.computeIfAbsent(
                file.name,
            ) {
                val deps = file.dependencyList.map { dep ->
                    files[dep] ?: throw IllegalStateException("Dependency $dep not found for file ${file.name}")
                }
                Descriptors.FileDescriptor.buildFrom(file, deps.toTypedArray())
            }
        }
        typeNames = filesInOrder.flatMap { fileDescriptor ->
            CodeGen.buildClassSpecs(fileDescriptor)
        }.toMap()
    }

    constructor(vararg fileDescriptors: Descriptors.FileDescriptor) {
        filesInOrder = fileDescriptors.toList()
        typeNames = filesInOrder.flatMap { fileDescriptor ->
            CodeGen.buildClassSpecs(fileDescriptor)
        }.toMap()
    }

    fun generate() {
        filesInOrder.forEach { fileDescriptor ->
            val fileSpec = generateSingleFile(fileDescriptor)
            fileSpec.build().writeTo(Path("./generated"))
        }
    }

    fun generateSingleFile(fileDescriptor: Descriptors.FileDescriptor): FileSpec.Builder {
        val packageName = fileDescriptor.`package` ?: ""

        val fileSpec = FileSpec.builder(packageName, fileDescriptor.name)

        fileDescriptor.messageTypes.forEach { messageType ->
            val typeSpec = generateSingleClass(messageType)
            fileSpec.addType(typeSpec.build())
        }

        fileDescriptor.enumTypes.forEach { enumType ->
            val typeSpec = generateSingleEnum(enumType)
            fileSpec.addType(typeSpec.build())
        }

        return fileSpec
    }

    fun generateSingleClass(messageType: Descriptors.Descriptor): TypeSpec.Builder {

        val typeSpec = TypeSpec.classBuilder(messageType.name)
            .addModifiers(KModifier.DATA)
            .addAnnotation(Serializable::class)

        val constructor = FunSpec.constructorBuilder().also { builder ->
            messageType.fields.forEach {
                builder
                    .addParameter(
                        ParameterSpec
                            .builder(it.name, it.toGeneratedType(typeNames))
                            .addAnnotation(
                                AnnotationSpec.builder(ProtoNumber::class)
                                    .addMember("number = %L", it.number)
                                    .build()
                            )
                            .build()
                    )
            }
        }.build()

        typeSpec.primaryConstructor(constructor)

        messageType.fields.forEach {
            typeSpec.addProperty(
                PropertySpec.builder(it.name, it.toGeneratedType(typeNames))
                    .initializer(it.name)
                    .build()
            )
        }

        return typeSpec
    }

    private fun generateSingleEnum(enumDescriptor: EnumDescriptor): TypeSpec.Builder {
        val typeSpec = TypeSpec.enumBuilder(enumDescriptor.name)

        enumDescriptor.values.forEach { valueDescriptor ->
            typeSpec.addEnumConstant(
                valueDescriptor.name,
                TypeSpec.anonymousClassBuilder()
                    .addSuperclassConstructorParameter("%L", valueDescriptor.number)
                    .build()
            )
        }

        return typeSpec
    }
}

object CodeGen {

    fun Descriptors.FieldDescriptor.toGeneratedType(classSpecs: Map<Descriptors.Descriptor, TypeName>): TypeName {
        if (this.isExtension) {
            println("This is an extension ${this.fullName}")
        }

        if (this.isMapField) {
            println("This is a map field ${this.fullName}")
        }

        if (this.isOptional) {
            println("This is an optional field ${this.fullName}")
        }

        if (this.isPackable) {
            println("This is a packable field ${this.fullName}}")
        }

        if (this.isRequired) {
            println("This is a required field ${this.fullName}")
        }

        if (this.isRepeated) {
            println("This is a repeated field ${this.fullName}")
        }

        if (this.isPacked) {
            println("This is a reserved field ${this.fullName}")
        }

        return when (this.type) {
            Type.BOOL -> BOOLEAN
            Type.INT32 -> INT
            Type.FIXED32 -> INT
            Type.SFIXED32 -> INT
            Type.SINT32 -> INT
            Type.UINT32 -> U_INT
            Type.INT64 -> LONG
            Type.FIXED64 -> LONG
            Type.SFIXED64 -> LONG
            Type.SINT64 -> LONG
            Type.UINT64 -> U_LONG
            Type.DOUBLE -> DOUBLE
            Type.FLOAT -> FLOAT
            Type.BYTES -> BYTE_ARRAY
            Type.STRING -> STRING
            Type.ENUM -> ENUM

            Type.GROUP -> ANY
            Type.MESSAGE -> classSpecs[this.messageType]
                ?: throw IllegalStateException("Message type not found: ${this.messageType.fullName}")

            else -> ANY
        }
    }

    private fun buildClassSpecs(
        descriptor: Descriptors.Descriptor,
        packageName: String,
        simpleNames: List<String>
    ): List<Pair<Descriptors.Descriptor, ClassName>> {
        return descriptor.nestedTypes.flatMap { nestedType ->
            buildClassSpecs(nestedType, packageName, simpleNames + descriptor.name)
        } + Pair(
            descriptor,
            ClassName(packageName, simpleNames + descriptor.name)
        )
    }

    fun buildClassSpecs(fileDescriptor: Descriptors.FileDescriptor): List<Pair<Descriptors.Descriptor, ClassName>> {
        val publicDependencies = fileDescriptor.publicDependencies.flatMap {
            buildClassSpecs(it)
        }

        val dependencies = fileDescriptor.dependencies.flatMap {
            buildClassSpecs(it)
        }

        val source = fileDescriptor.messageTypes.flatMap {
            buildClassSpecs(it, fileDescriptor.`package`, listOf())
        }

        return (publicDependencies + dependencies + source)
    }
}
