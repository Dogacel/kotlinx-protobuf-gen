package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.google.protobuf.Descriptors.FieldDescriptor.Type
import com.google.protobuf.compiler.PluginProtos
import com.squareup.kotlinpoet.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlin.io.path.Path

object CodeGenerator {
    fun generate(request: PluginProtos.CodeGeneratorRequest) {
        val fileNameToDescriptor = mutableMapOf<String, Descriptors.FileDescriptor>()
        request.protoFileList.forEach { file ->
            val deps = file.dependencyList.map { dep ->
                fileNameToDescriptor[dep]
                    ?: throw IllegalStateException("Dependency $dep not found for file ${file.name}")
            }
            fileNameToDescriptor[file.name] = Descriptors.FileDescriptor.buildFrom(file, deps.toTypedArray())
        }

        fileNameToDescriptor.map {
            generateFile(it.key, it.value)
        }
    }

    private fun Descriptors.FieldDescriptor.toGeneratedType(classSpecs: Map<Descriptors.Descriptor, TypeName>): TypeName {
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

    fun buildClassSpecs(
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

    fun buildClassSpecs(fileDescriptor: Descriptors.FileDescriptor): Map<Descriptors.Descriptor, ClassName> {
        val publicDependencies = fileDescriptor.publicDependencies.map {
            buildClassSpecs(it)
        }

        val dependencies = fileDescriptor.dependencies.map {
            buildClassSpecs(it)
        }

        val source = fileDescriptor.messageTypes.flatMap {
            buildClassSpecs(it, fileDescriptor.`package`, listOf())
        }.toMap()

        return (publicDependencies + dependencies + source).flatMap { it.entries }
            .associate { it.key to it.value }
    }

    fun generateFile(fileName: String, fileDescriptor: Descriptors.FileDescriptor) {

        val classSpecs = buildClassSpecs(fileDescriptor)

        val packageName = if (fileDescriptor.`package`.isNotEmpty()) {
            "generated." + fileDescriptor.`package`
        } else {
            "generated"
        }

        val fileSpec = FileSpec.builder(packageName, fileDescriptor.name)


        fileDescriptor.messageTypes.forEach { messageType ->
            val typeSpec = TypeSpec.classBuilder(messageType.name)
                .addModifiers(KModifier.DATA)
                .addAnnotation(Serializable::class)

            val constructor = FunSpec.constructorBuilder().also { builder ->
                messageType.fields.forEach {
                    builder
                        .addParameter(
                            ParameterSpec
                                .builder(it.name, it.toGeneratedType(classSpecs))
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
                    PropertySpec.builder(it.name, it.toGeneratedType(classSpecs))
                        .initializer(it.name)
                        .build()
                )
            }

            fileSpec.addType(typeSpec.build())
        }

        println(fileSpec.build().toString())

        fileSpec.build().writeTo(Path("./generated"))
    }
}
