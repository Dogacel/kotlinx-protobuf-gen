@file:OptIn(ExperimentalSerializationApi::class)

package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.google.protobuf.compiler.PluginProtos
import com.squareup.kotlinpoet.*
import dogacel.kotlinx.protobuf.gen.DefaultValues.defaultValueOf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import java.nio.file.Path
import kotlin.io.path.Path

data class CodeGeneratorOptions(
    val packagePrefix: String = "",
)

/**
 * A class that generates the Kotlin code for the given [PluginProtos.CodeGeneratorRequest].
 * This request is sent via the protobuf compiler plugin.
 */
class CodeGenerator {
    private val typeNames: Map<Descriptors.GenericDescriptor, TypeName>
    private val filesInOrder: List<Descriptors.FileDescriptor>
    private val options: CodeGeneratorOptions

    constructor(
        request: PluginProtos.CodeGeneratorRequest,
        options: CodeGeneratorOptions = CodeGeneratorOptions()
    ) {
        this.options = options
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
            CodeGen.buildClassSpecs(fileDescriptor, options.packagePrefix)
        }.toMap()
    }

    constructor(
        vararg fileDescriptors: Descriptors.FileDescriptor,
        options: CodeGeneratorOptions = CodeGeneratorOptions()
    ) {
        this.options = options
        filesInOrder = fileDescriptors.toList()
        typeNames = filesInOrder.flatMap { fileDescriptor ->
            CodeGen.buildClassSpecs(fileDescriptor, options.packagePrefix)
        }.toMap()
    }

    /**
     * Generate the source files to the given [path].
     */
    fun generate(path: Path = Path("./generated")) {
        filesInOrder.forEach { fileDescriptor ->
            val fileSpec = generateSingleFile(fileDescriptor)
            fileSpec.build().writeTo(path)
        }
    }

    /**
     * Generate the code for the given [Descriptors.FileDescriptor]. Returns a [FileSpec.Builder] so users
     * can add additional code to the file.
     *
     * @param fileDescriptor [Descriptors.FileDescriptor] to generate code for.
     * @return [FileSpec.Builder] that contains the generated code.
     */
    fun generateSingleFile(fileDescriptor: Descriptors.FileDescriptor): FileSpec.Builder {
        val packageName = if (options.packagePrefix.isNotEmpty()) {
            options.packagePrefix + '.' + fileDescriptor.`package`
        } else {
            fileDescriptor.`package`
        }

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

    /**
     * Generate a single parameter for the given [Descriptors.FieldDescriptor]. Returns a
     * [ParameterSpec.Builder] so users can add additional code to the parameter.
     *
     * @param fieldDescriptor [Descriptors.FieldDescriptor] to generate code for.
     * @return [ParameterSpec.Builder] that contains the generated code.
     */
    fun generateSingleParameter(fieldDescriptor: Descriptors.FieldDescriptor): ParameterSpec.Builder {
        val fieldTypeName = TypeNames.typeNameOf(fieldDescriptor, typeNames)

        val builder = ParameterSpec.builder(fieldDescriptor.name, fieldTypeName)

        builder.addAnnotation(
            AnnotationSpec.builder(ProtoNumber::class)
                .addMember("number = %L", fieldDescriptor.number)
                .build()
        )

        val defaultValue = defaultValueOf(fieldDescriptor, typeNames)

        builder.defaultValue("%L", defaultValue)

        return builder
    }

    /**
     * Generate a single class for the given [Descriptors.Descriptor]. Returns a [TypeSpec.Builder] so users
     * can add additional code to the class.
     *
     * @param messageType [Descriptors.Descriptor] to generate code for.
     * @return [TypeSpec.Builder] that contains the generated code.
     */
    fun generateSingleClass(messageType: Descriptors.Descriptor): TypeSpec.Builder {
        val typeSpec = TypeSpec.classBuilder(messageType.name)
            .addModifiers(KModifier.DATA)
            .addAnnotation(Serializable::class)

        val constructor = FunSpec.constructorBuilder().also { builder ->
            messageType.fields.forEach {
                builder
                    .addParameter(
                        generateSingleParameter(it).build()
                    )
            }
        }.build()

        typeSpec.primaryConstructor(constructor)

        messageType.fields.forEach {
            val type = TypeNames.typeNameOf(it, typeNames)
            typeSpec.addProperty(
                PropertySpec.builder(it.name, type)
                    .initializer(it.name)
                    .build()
            )
        }

        return typeSpec
    }

    /**
     * Generate a single enum for the given [Descriptors.EnumDescriptor]. Returns a [TypeSpec.Builder] so users
     * can add additional code to the enum.
     *
     * @param enumDescriptor [Descriptors.EnumDescriptor] to generate code for.
     * @return [TypeSpec.Builder] that contains the generated code.
     */
    private fun generateSingleEnum(enumDescriptor: Descriptors.EnumDescriptor): TypeSpec.Builder {
        val typeSpec = TypeSpec.enumBuilder(enumDescriptor.name)

        enumDescriptor.values.forEach { valueDescriptor ->
            typeSpec.addEnumConstant(
                valueDescriptor.name,
                TypeSpec.anonymousClassBuilder()
                    .addAnnotation(
                        AnnotationSpec.builder(ProtoNumber::class)
                            .addMember("number = %L", valueDescriptor.number)
                            .build()
                    )
                    .build()
            )
        }

        return typeSpec
    }
}

/**
 * Utilities for generating code.
 */
object CodeGen {


    private fun buildEnumSpecs(
        enumDescriptor: Descriptors.EnumDescriptor,
        packageName: String,
        simpleNames: List<String>
    ): Pair<Descriptors.GenericDescriptor, ClassName> {
        return Pair(
            enumDescriptor,
            ClassName(packageName, simpleNames + enumDescriptor.name)
        )
    }

    private fun buildClassSpecs(
        descriptor: Descriptors.Descriptor,
        packageName: String,
        simpleNames: List<String>
    ): List<Pair<Descriptors.GenericDescriptor, ClassName>> {
        val messages = descriptor.nestedTypes.flatMap { nestedType ->
            buildClassSpecs(nestedType, packageName, simpleNames + descriptor.name)
        }

        val enums = descriptor.enumTypes.map {
            buildEnumSpecs(it, packageName, simpleNames + descriptor.name)
        }

        val self = Pair(
            descriptor,
            ClassName(packageName, simpleNames + descriptor.name)
        )
        return (messages + enums + self)
    }

    fun buildClassSpecs(
        fileDescriptor: Descriptors.FileDescriptor,
        packagePrefix: String = ""
    ): List<Pair<Descriptors.GenericDescriptor, ClassName>> {
        val publicDependencies = fileDescriptor.publicDependencies.flatMap {
            buildClassSpecs(it, packagePrefix)
        }

        val dependencies = fileDescriptor.dependencies.flatMap {
            buildClassSpecs(it, packagePrefix)
        }

        val packageName = if (packagePrefix.isNotEmpty()) {
            packagePrefix + '.' + fileDescriptor.`package`
        } else {
            fileDescriptor.`package`
        }

        val messages = fileDescriptor.messageTypes.flatMap {
            buildClassSpecs(it, packageName, listOf())
        }

        val enums = fileDescriptor.enumTypes.map {
            buildEnumSpecs(it, packageName, listOf())
        }

        return (publicDependencies + dependencies + messages + enums)
    }
}
