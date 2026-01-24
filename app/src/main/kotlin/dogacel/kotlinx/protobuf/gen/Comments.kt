package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.DescriptorProtos
import com.google.protobuf.Descriptors

/**
 * Holds comments extracted from protobuf source files.
 *
 * @property leadingComments Comments that appear before the element.
 * @property trailingComments Comments that appear on the same line after the element.
 * @property leadingDetachedComments Comments that are detached (separated by blank lines) before the element.
 */
data class ProtoComments(
    val leadingComments: String = "",
    val trailingComments: String = "",
    val leadingDetachedComments: List<String> = emptyList(),
) {
    /**
     * Returns true if there are any comments available.
     */
    fun hasComments(): Boolean =
        leadingComments.isNotBlank() || trailingComments.isNotBlank() || leadingDetachedComments.any { it.isNotBlank() }

    /**
     * Returns all comments combined, with leading comments taking priority.
     */
    fun getCombinedComment(): String {
        val parts = mutableListOf<String>()
        leadingDetachedComments.filter { it.isNotBlank() }.forEach { parts.add(it.trim()) }
        if (leadingComments.isNotBlank()) parts.add(leadingComments.trim())
        if (trailingComments.isNotBlank()) parts.add(trailingComments.trim())
        return parts.joinToString("\n\n")
    }
}

/**
 * Type alias for location maps that map paths to SourceCodeInfo.Location.
 */
typealias LocationMap = Map<List<Int>, DescriptorProtos.SourceCodeInfo.Location>

/**
 * Utility object for extracting comments from protobuf SourceCodeInfo.
 *
 * Paths in SourceCodeInfo.Location identify elements by field numbers and indices:
 * - FileDescriptorProto: 4=message_type, 5=enum_type, 6=service
 * - DescriptorProto: 2=field, 3=nested_type, 4=enum_type
 * - EnumDescriptorProto: 2=value
 * - ServiceDescriptorProto: 2=method
 */
object Comments {
    // Field numbers from google/protobuf/descriptor.proto
    private const val FILE_MESSAGE_TYPE_FIELD = 4
    private const val FILE_ENUM_TYPE_FIELD = 5
    private const val FILE_SERVICE_FIELD = 6

    private const val MESSAGE_FIELD_FIELD = 2
    private const val MESSAGE_NESTED_TYPE_FIELD = 3
    private const val MESSAGE_ENUM_TYPE_FIELD = 4

    private const val ENUM_VALUE_FIELD = 2

    private const val SERVICE_METHOD_FIELD = 2

    /**
     * Builds a location map from SourceCodeInfo, mapping paths to locations.
     *
     * @param sourceCodeInfo The SourceCodeInfo from a FileDescriptorProto.
     * @return A map from paths to locations.
     */
    fun buildLocationMap(sourceCodeInfo: DescriptorProtos.SourceCodeInfo): LocationMap {
        return sourceCodeInfo.locationList.associateBy { it.pathList }
    }

    /**
     * Extracts comments for a message descriptor.
     *
     * @param descriptor The message descriptor.
     * @param locationMap The location map built from SourceCodeInfo.
     * @return ProtoComments for the message.
     */
    fun getCommentsForMessage(
        descriptor: Descriptors.Descriptor,
        locationMap: LocationMap,
    ): ProtoComments {
        val path = buildMessagePath(descriptor)
        return getCommentsForPath(path, locationMap)
    }

    /**
     * Extracts comments for a field descriptor.
     *
     * @param descriptor The field descriptor.
     * @param locationMap The location map built from SourceCodeInfo.
     * @return ProtoComments for the field.
     */
    fun getCommentsForField(
        descriptor: Descriptors.FieldDescriptor,
        locationMap: LocationMap,
    ): ProtoComments {
        val parentPath = buildMessagePath(descriptor.containingType)
        val fieldIndex = descriptor.containingType.fields.indexOf(descriptor)
        val path = parentPath + listOf(MESSAGE_FIELD_FIELD, fieldIndex)
        return getCommentsForPath(path, locationMap)
    }

    /**
     * Extracts comments for an enum descriptor.
     *
     * @param descriptor The enum descriptor.
     * @param locationMap The location map built from SourceCodeInfo.
     * @return ProtoComments for the enum.
     */
    fun getCommentsForEnum(
        descriptor: Descriptors.EnumDescriptor,
        locationMap: LocationMap,
    ): ProtoComments {
        val path = buildEnumPath(descriptor)
        return getCommentsForPath(path, locationMap)
    }

    /**
     * Extracts comments for an enum value descriptor.
     *
     * @param descriptor The enum value descriptor.
     * @param locationMap The location map built from SourceCodeInfo.
     * @return ProtoComments for the enum value.
     */
    fun getCommentsForEnumValue(
        descriptor: Descriptors.EnumValueDescriptor,
        locationMap: LocationMap,
    ): ProtoComments {
        val enumPath = buildEnumPath(descriptor.type)
        val valueIndex = descriptor.type.values.indexOf(descriptor)
        val path = enumPath + listOf(ENUM_VALUE_FIELD, valueIndex)
        return getCommentsForPath(path, locationMap)
    }

    /**
     * Extracts comments for a service descriptor.
     *
     * @param descriptor The service descriptor.
     * @param locationMap The location map built from SourceCodeInfo.
     * @return ProtoComments for the service.
     */
    fun getCommentsForService(
        descriptor: Descriptors.ServiceDescriptor,
        locationMap: LocationMap,
    ): ProtoComments {
        val serviceIndex = descriptor.file.services.indexOf(descriptor)
        val path = listOf(FILE_SERVICE_FIELD, serviceIndex)
        return getCommentsForPath(path, locationMap)
    }

    /**
     * Extracts comments for a method descriptor.
     *
     * @param descriptor The method descriptor.
     * @param locationMap The location map built from SourceCodeInfo.
     * @return ProtoComments for the method.
     */
    fun getCommentsForMethod(
        descriptor: Descriptors.MethodDescriptor,
        locationMap: LocationMap,
    ): ProtoComments {
        val serviceIndex = descriptor.service.file.services.indexOf(descriptor.service)
        val methodIndex = descriptor.service.methods.indexOf(descriptor)
        val path = listOf(FILE_SERVICE_FIELD, serviceIndex, SERVICE_METHOD_FIELD, methodIndex)
        return getCommentsForPath(path, locationMap)
    }

    /**
     * Builds the path to a message descriptor.
     */
    private fun buildMessagePath(descriptor: Descriptors.Descriptor): List<Int> {
        val containingType = descriptor.containingType
        return if (containingType != null) {
            // Nested message
            val parentPath = buildMessagePath(containingType)
            val nestedIndex = containingType.nestedTypes.indexOf(descriptor)
            parentPath + listOf(MESSAGE_NESTED_TYPE_FIELD, nestedIndex)
        } else {
            // Top-level message
            val messageIndex = descriptor.file.messageTypes.indexOf(descriptor)
            listOf(FILE_MESSAGE_TYPE_FIELD, messageIndex)
        }
    }

    /**
     * Builds the path to an enum descriptor.
     */
    private fun buildEnumPath(descriptor: Descriptors.EnumDescriptor): List<Int> {
        val containingType = descriptor.containingType
        return if (containingType != null) {
            // Nested enum
            val parentPath = buildMessagePath(containingType)
            val enumIndex = containingType.enumTypes.indexOf(descriptor)
            parentPath + listOf(MESSAGE_ENUM_TYPE_FIELD, enumIndex)
        } else {
            // Top-level enum
            val enumIndex = descriptor.file.enumTypes.indexOf(descriptor)
            listOf(FILE_ENUM_TYPE_FIELD, enumIndex)
        }
    }

    /**
     * Extracts ProtoComments from the location at the given path.
     */
    private fun getCommentsForPath(
        path: List<Int>,
        locationMap: LocationMap,
    ): ProtoComments {
        val location = locationMap[path] ?: return ProtoComments()
        return ProtoComments(
            leadingComments = location.leadingComments ?: "",
            trailingComments = location.trailingComments ?: "",
            leadingDetachedComments = location.leadingDetachedCommentsList ?: emptyList(),
        )
    }
}
