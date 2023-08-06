package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.squareup.kotlinpoet.*

object DefaultValues {
    /**
     * Get a default value for the given [Descriptors.FieldDescriptor].
     *
     * @param fieldDescriptor a [Descriptors.FieldDescriptor] to get default value for.
     * @param typeNames a map of [Descriptors.GenericDescriptor] to [TypeName] to get type names which is used
     *        to reference other types using full name in code.
     * @return default value of the given type
     */
    fun defaultValueOf(
        fieldDescriptor: Descriptors.FieldDescriptor,
        typeNames: Map<Descriptors.GenericDescriptor, TypeName> = mapOf()
    ): Any? {
        if (fieldDescriptor.isRepeated) {
            if (fieldDescriptor.isMapField) {
                return CodeBlock.of("emptyMap()")
            }
            return CodeBlock.of("emptyList()")
        }

        if (fieldDescriptor.hasOptionalKeyword()) {
            return null
        }

        return when (fieldDescriptor.type) {
            Descriptors.FieldDescriptor.Type.BOOL -> CodeBlock.of("false")
            Descriptors.FieldDescriptor.Type.INT32 -> CodeBlock.of("0")
            Descriptors.FieldDescriptor.Type.FIXED32 -> CodeBlock.of("0")
            Descriptors.FieldDescriptor.Type.SFIXED32 -> CodeBlock.of("0")
            Descriptors.FieldDescriptor.Type.SINT32 -> CodeBlock.of("0")
            Descriptors.FieldDescriptor.Type.UINT32 -> CodeBlock.of("0U")
            Descriptors.FieldDescriptor.Type.INT64 -> CodeBlock.of("0L")
            Descriptors.FieldDescriptor.Type.FIXED64 -> CodeBlock.of("0L")
            Descriptors.FieldDescriptor.Type.SFIXED64 -> CodeBlock.of("0L")
            Descriptors.FieldDescriptor.Type.SINT64 -> CodeBlock.of("0L")
            Descriptors.FieldDescriptor.Type.UINT64 -> CodeBlock.of("0UL")
            Descriptors.FieldDescriptor.Type.DOUBLE -> CodeBlock.of("0.0")
            Descriptors.FieldDescriptor.Type.FLOAT -> CodeBlock.of("0.0f")
            Descriptors.FieldDescriptor.Type.BYTES -> CodeBlock.of("byteArrayOf()")
            Descriptors.FieldDescriptor.Type.STRING -> CodeBlock.of("\"\"")

            Descriptors.FieldDescriptor.Type.GROUP -> null

            Descriptors.FieldDescriptor.Type.ENUM -> {
                val typeName = typeNames[fieldDescriptor.enumType]
                    ?: throw IllegalStateException("Enum type not found: ${fieldDescriptor.enumType.fullName}")

                return CodeBlock.of("%L.%L", typeName, fieldDescriptor.defaultValue)
            }

            Descriptors.FieldDescriptor.Type.MESSAGE -> null
            null -> throw IllegalStateException("Field type is null: $fieldDescriptor")
        }
    }
}
