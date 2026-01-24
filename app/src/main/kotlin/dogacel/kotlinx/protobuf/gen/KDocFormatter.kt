package dogacel.kotlinx.protobuf.gen

/**
 * Utility object for formatting comments into KDoc format.
 */
object KDocFormatter {
    /**
     * Formats a class KDoc with the class description and @param tags for fields.
     *
     * @param classComment The comment for the class itself.
     * @param paramDocs A list of pairs where each pair is (paramName, paramComment).
     * @return Formatted KDoc string, or empty string if no comments.
     */
    fun formatClassKDoc(
        classComment: ProtoComments,
        paramDocs: List<Pair<String, ProtoComments>>,
    ): String {
        val hasClassComment = classComment.hasComments()
        val hasParamComments = paramDocs.any { it.second.hasComments() }

        if (!hasClassComment && !hasParamComments) {
            return ""
        }

        val parts = mutableListOf<String>()

        // Add class description
        if (hasClassComment) {
            parts.add(sanitizeComment(classComment.getCombinedComment()))
        }

        // Add @param tags
        val paramLines =
            paramDocs
                .filter { it.second.hasComments() }
                .map { (name, comment) ->
                    "@param $name ${sanitizeComment(comment.getCombinedComment()).replace("\n", " ")}"
                }

        if (paramLines.isNotEmpty()) {
            if (parts.isNotEmpty()) {
                parts.add("") // Add blank line between description and params
            }
            parts.addAll(paramLines)
        }

        return parts.joinToString("\n")
    }

    /**
     * Formats a simple KDoc from a ProtoComments.
     *
     * @param comment The ProtoComments to format.
     * @return Formatted KDoc string, or empty string if no comments.
     */
    fun formatSimpleKDoc(comment: ProtoComments): String {
        if (!comment.hasComments()) {
            return ""
        }
        return sanitizeComment(comment.getCombinedComment())
    }

    /**
     * Sanitizes a comment string for use in KDoc.
     * Escapes special characters that could break KDoc formatting.
     *
     * @param comment The raw comment string.
     * @return Sanitized comment string safe for KDoc.
     */
    fun sanitizeComment(comment: String): String {
        return comment
            // Escape KDoc comment closers
            .replace("*/", "*\u200B/")
            // Normalize line endings
            .replace("\r\n", "\n")
            .replace("\r", "\n")
            // Trim trailing whitespace from each line
            .lines()
            .joinToString("\n") { it.trimEnd().removePrefix(" ") }
            .trim()
    }
}
