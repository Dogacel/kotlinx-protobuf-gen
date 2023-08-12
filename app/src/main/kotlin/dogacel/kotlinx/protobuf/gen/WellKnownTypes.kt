package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.squareup.kotlinpoet.*

abstract class WellKnownTypes {
    /**
     * Get well known type name for the given descriptor.
     *
     * @param descriptor The descriptor to get the well known type name for.
     * @return The well known type name or `null` if the descriptor is not a well known type.
     */
    abstract fun getFor(descriptor: Descriptors.GenericDescriptor): TypeName?
}


object DefaultWellKnownTypes : WellKnownTypes() {
    private val typeMapping: Map<Descriptors.GenericDescriptor, TypeName> = mapOf(
//        com.google.protobuf.Any.getDescriptor() to ANY,
        com.google.protobuf.BoolValue.getDescriptor() to BOOLEAN.copy(nullable = true),
        com.google.protobuf.BytesValue.getDescriptor() to BYTE_ARRAY.copy(nullable = true),
        com.google.protobuf.DoubleValue.getDescriptor() to DOUBLE.copy(nullable = true),
        com.google.protobuf.Duration.getDescriptor() to ClassName(
            "kotlin.time",
            "Duration"
        ).copy(nullable = true),
        com.google.protobuf.Empty.getDescriptor() to UNIT,
//        com.google.protobuf.FieldMask.getDescriptor() to ???,
        com.google.protobuf.FloatValue.getDescriptor() to FLOAT.copy(nullable = true),
        com.google.protobuf.Int32Value.getDescriptor() to INT.copy(nullable = true),
        com.google.protobuf.Int64Value.getDescriptor() to LONG.copy(nullable = true),
//        com.google.protobuf.ListValue.getDescriptor() to ???,
        com.google.protobuf.NullValue.getDescriptor() to NOTHING.copy(nullable = true),
        com.google.protobuf.StringValue.getDescriptor() to STRING.copy(nullable = true),
//        com.google.protobuf.Struct.getDescriptor() to ???,
        com.google.protobuf.Timestamp.getDescriptor() to ClassName(
            "kotlinx.datetime",
            "Instant"
        ).copy(nullable = true),
        com.google.protobuf.UInt32Value.getDescriptor() to U_INT.copy(nullable = true),
        com.google.protobuf.UInt64Value.getDescriptor() to U_LONG.copy(nullable = true),
//        com.google.protobuf.Value.getDescriptor() to ???,
    )

    override fun getFor(descriptor: Descriptors.GenericDescriptor): TypeName? {
        return typeMapping[descriptor]
    }
}
