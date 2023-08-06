package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

object TypeNames {
    /**
     * Returns the [TypeName] of the given [fieldDescriptor]. The given [TypeName] can be used globally to
     * reference the type that has the given [fieldDescriptor].
     *
     * @param fieldDescriptor a [Descriptors.FieldDescriptor] to get [TypeName] for.
     * @param typeNames a map of [Descriptors.GenericDescriptor] to [TypeName] to get type names which is used
     *        to reference other types using full name in code.
     * @return [TypeName] of the given [fieldDescriptor].
     */
    fun typeNameOf(
        fieldDescriptor: Descriptors.FieldDescriptor,
        typeNames: Map<Descriptors.GenericDescriptor, TypeName>
    ): TypeName {
        var primitiveType = when (fieldDescriptor.type) {
            Descriptors.FieldDescriptor.Type.BOOL -> BOOLEAN
            Descriptors.FieldDescriptor.Type.INT32 -> INT
            Descriptors.FieldDescriptor.Type.FIXED32 -> INT
            Descriptors.FieldDescriptor.Type.SFIXED32 -> INT
            Descriptors.FieldDescriptor.Type.SINT32 -> INT
            Descriptors.FieldDescriptor.Type.UINT32 -> U_INT
            Descriptors.FieldDescriptor.Type.INT64 -> LONG
            Descriptors.FieldDescriptor.Type.FIXED64 -> LONG
            Descriptors.FieldDescriptor.Type.SFIXED64 -> LONG
            Descriptors.FieldDescriptor.Type.SINT64 -> LONG
            Descriptors.FieldDescriptor.Type.UINT64 -> U_LONG
            Descriptors.FieldDescriptor.Type.DOUBLE -> DOUBLE
            Descriptors.FieldDescriptor.Type.FLOAT -> FLOAT
            Descriptors.FieldDescriptor.Type.BYTES -> BYTE_ARRAY
            Descriptors.FieldDescriptor.Type.STRING -> STRING

            Descriptors.FieldDescriptor.Type.GROUP -> ANY
            Descriptors.FieldDescriptor.Type.ENUM -> typeNames[fieldDescriptor.enumType]
                ?: throw IllegalStateException("Enum type not found: ${fieldDescriptor.enumType.fullName}")

            Descriptors.FieldDescriptor.Type.MESSAGE -> typeNames[fieldDescriptor.messageType]
                ?: throw IllegalStateException("Message type not found: ${fieldDescriptor.messageType.fullName}")

            null -> throw IllegalStateException("Field type is null: $fieldDescriptor")
        }

        if (fieldDescriptor.hasOptionalKeyword() || fieldDescriptor.type == Descriptors.FieldDescriptor.Type.MESSAGE) {
            primitiveType = primitiveType.copy(nullable = true)
        }

        if (fieldDescriptor.isRepeated) {
            if (fieldDescriptor.isMapField) {
                val k = typeNameOf(
                    fieldDescriptor.messageType.fields[0],
                    typeNames
                )
                val v = typeNameOf(
                    fieldDescriptor.messageType.fields[1],
                    typeNames
                )
                return ClassName("kotlin.collections", "Map").parameterizedBy(k, v)
            }
            return ClassName("kotlin.collections", "List").parameterizedBy(primitiveType)
        }

        return primitiveType
    }
}
