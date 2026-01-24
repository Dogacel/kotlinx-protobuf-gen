package dogacel.kotlinx.protobuf.gen

import kotlin.test.Test
import kotlin.test.assertEquals

class KDocFormatterTest {
    @Test
    fun formatSimpleKDocShouldReturnEmptyForNoComments() {
        val comments = ProtoComments()
        val result = KDocFormatter.formatSimpleKDoc(comments)
        assertEquals("", result)
    }

    @Test
    fun formatSimpleKDocShouldReturnLeadingComments() {
        val comments = ProtoComments(leadingComments = "This is a leading comment.")
        val result = KDocFormatter.formatSimpleKDoc(comments)
        assertEquals("This is a leading comment.", result)
    }

    @Test
    fun formatSimpleKDocShouldReturnTrailingComments() {
        val comments = ProtoComments(trailingComments = "This is a trailing comment.")
        val result = KDocFormatter.formatSimpleKDoc(comments)
        assertEquals("This is a trailing comment.", result)
    }

    @Test
    fun formatSimpleKDocShouldCombineComments() {
        val comments =
            ProtoComments(
                leadingComments = "Leading comment.",
                trailingComments = "Trailing comment.",
            )
        val result = KDocFormatter.formatSimpleKDoc(comments)
        assertEquals("Leading comment.\n\nTrailing comment.", result)
    }

    @Test
    fun formatSimpleKDocShouldIncludeDetachedComments() {
        val comments =
            ProtoComments(
                leadingDetachedComments = listOf("Detached comment 1.", "Detached comment 2."),
                leadingComments = "Leading comment.",
            )
        val result = KDocFormatter.formatSimpleKDoc(comments)
        assertEquals("Detached comment 1.\n\nDetached comment 2.\n\nLeading comment.", result)
    }

    @Test
    fun formatClassKDocShouldReturnEmptyForNoComments() {
        val classComment = ProtoComments()
        val paramDocs = emptyList<Pair<String, ProtoComments>>()
        val result = KDocFormatter.formatClassKDoc(classComment, paramDocs)
        assertEquals("", result)
    }

    @Test
    fun formatClassKDocShouldReturnOnlyClassComment() {
        val classComment = ProtoComments(leadingComments = "This is a class comment.")
        val paramDocs = emptyList<Pair<String, ProtoComments>>()
        val result = KDocFormatter.formatClassKDoc(classComment, paramDocs)
        assertEquals("This is a class comment.", result)
    }

    @Test
    fun formatClassKDocShouldReturnOnlyParamComments() {
        val classComment = ProtoComments()
        val paramDocs =
            listOf(
                "id" to ProtoComments(leadingComments = "The ID field."),
                "name" to ProtoComments(leadingComments = "The name field."),
            )
        val result = KDocFormatter.formatClassKDoc(classComment, paramDocs)
        assertEquals("@param id The ID field.\n@param name The name field.", result)
    }

    @Test
    fun formatClassKDocShouldCombineClassAndParamComments() {
        val classComment = ProtoComments(leadingComments = "A documented class.")
        val paramDocs =
            listOf(
                "id" to ProtoComments(leadingComments = "The ID field."),
                "name" to ProtoComments(leadingComments = "The name field."),
            )
        val result = KDocFormatter.formatClassKDoc(classComment, paramDocs)
        assertEquals("A documented class.\n\n@param id The ID field.\n@param name The name field.", result)
    }

    @Test
    fun formatClassKDocShouldSkipParamsWithNoComments() {
        val classComment = ProtoComments(leadingComments = "A documented class.")
        val paramDocs =
            listOf(
                "id" to ProtoComments(leadingComments = "The ID field."),
                "name" to ProtoComments(),
                "description" to ProtoComments(leadingComments = "The description field."),
            )
        val result = KDocFormatter.formatClassKDoc(classComment, paramDocs)
        assertEquals("A documented class.\n\n@param id The ID field.\n@param description The description field.", result)
    }

    @Test
    fun sanitizeCommentShouldEscapeKDocCloser() {
        val comment = "This comment contains */ which should be escaped."
        val result = KDocFormatter.sanitizeComment(comment)
        assertEquals("This comment contains *\u200B/ which should be escaped.", result)
    }

    @Test
    fun sanitizeCommentShouldNormalizeLineEndings() {
        val comment = "Line 1\r\nLine 2\rLine 3"
        val result = KDocFormatter.sanitizeComment(comment)
        assertEquals("Line 1\nLine 2\nLine 3", result)
    }

    @Test
    fun sanitizeCommentShouldTrimTrailingWhitespace() {
        val comment = "Line 1   \nLine 2  "
        val result = KDocFormatter.sanitizeComment(comment)
        assertEquals("Line 1\nLine 2", result)
    }
}
