package dogacel.kotlinx.protobuf.gen.wkt

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

object NoWellKnownTypes : WellKnownTypes() {
    override fun getFor(descriptor: Descriptors.GenericDescriptor): TypeName? {
        return null
    }
}

object DefaultWellKnownTypes : WellKnownTypes() {
    private val typeMapping: Map<String, TypeName> = mapOf(
//        com.google.protobuf.Any.getDescriptor() to ANY,
        com.google.protobuf.BoolValue.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "BoolValueP"
        ),
        com.google.protobuf.Int32Value.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "Int32ValueP"
        ),
        com.google.protobuf.Int64Value.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "Int64ValueP"
        ),
        com.google.protobuf.UInt32Value.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "UInt32ValueP"
        ),
        com.google.protobuf.UInt64Value.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "UInt64ValueP"
        ),
        com.google.protobuf.FloatValue.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "FloatValueP"
        ),
        com.google.protobuf.StringValue.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "StringValueP"
        ),
        com.google.protobuf.BytesValue.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "BytesValueP"
        ),
        com.google.protobuf.DoubleValue.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "DoubleValueP"
        ),
        com.google.protobuf.Duration.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "DurationP"
        ),
        com.google.protobuf.Timestamp.getDescriptor() to ClassName(
            "dogacel.kotlinx.protobuf.gen.wkt",
            "TimestampP"
        ),
        com.google.protobuf.Empty.getDescriptor() to UNIT,
//        com.google.protobuf.FieldMask.getDescriptor() to ???,
//        com.google.protobuf.ListValue.getDescriptor() to ???,
        com.google.protobuf.NullValue.getDescriptor() to NOTHING.copy(nullable = true)
//        com.google.protobuf.Struct.getDescriptor() to ???,
//        com.google.protobuf.Value.getDescriptor() to ???,
    ).mapKeys {
        it.key.fullName
    }

    override fun getFor(descriptor: Descriptors.GenericDescriptor): TypeName? {
        return typeMapping[descriptor.fullName]
    }
}
