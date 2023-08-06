package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.Descriptors
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.MemberName
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked
import kotlinx.serialization.protobuf.ProtoType

object Annotations {

    /**
     * Get [AnnotationSpec]s required to serialize a [Descriptors.EnumValueDescriptor] correctly.
     *
     * Currently only proto number is supported.
     *
     * @param enumValueDescriptor
     * @return
     */
    fun annotationsOf(
        enumValueDescriptor: Descriptors.EnumValueDescriptor
    ): List<AnnotationSpec> {
        return listOf(
            AnnotationSpec.builder(ProtoNumber::class)
                .addMember("number = %L", enumValueDescriptor.number)
                .build()
        )
    }

    /**
     * Get [AnnotationSpec]s required to serialize a [Descriptors.FieldDescriptor] correctly.
     *
     * Proto number, packing and encoding type is supported.
     *
     * @param fieldDescriptor a [Descriptors.FieldDescriptor] to get annotations for.
     * @return a list of [AnnotationSpec]s to generate annotations from.
     */
    fun annotationsOf(
        fieldDescriptor: Descriptors.FieldDescriptor
    ): List<AnnotationSpec> {
        val annotations: MutableList<AnnotationSpec> = mutableListOf()

        annotations += AnnotationSpec.builder(ProtoNumber::class)
            .addMember("number = %L", fieldDescriptor.number)
            .build()

        if (fieldDescriptor.isPackable && fieldDescriptor.isPacked) {
            annotations += AnnotationSpec.builder(ProtoPacked::class).build()
        }

        // Documentation says, UintXX and SfixedXX is not supported but this configuration passes unit tests.
        // https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-protobuf/kotlinx.serialization.protobuf/-proto-integer-type/
        when (fieldDescriptor.type) {
            Descriptors.FieldDescriptor.Type.FIXED32,
            Descriptors.FieldDescriptor.Type.FIXED64,
            Descriptors.FieldDescriptor.Type.SFIXED32,
            Descriptors.FieldDescriptor.Type.SFIXED64
            -> annotations += AnnotationSpec.builder(ProtoType::class)
                .addMember("type = %M", MemberName("kotlinx.serialization.protobuf.ProtoIntegerType", "FIXED"))
                .build()

            Descriptors.FieldDescriptor.Type.SINT32,
            Descriptors.FieldDescriptor.Type.SINT64
            -> annotations += AnnotationSpec.builder(ProtoType::class)
                .addMember("type = %M", MemberName("kotlinx.serialization.protobuf.ProtoIntegerType", "SIGNED"))
                .build()

            else -> Unit
        }

        return annotations
    }
}
