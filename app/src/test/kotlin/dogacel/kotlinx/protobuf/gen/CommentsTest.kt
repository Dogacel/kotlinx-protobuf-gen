package dogacel.kotlinx.protobuf.gen

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CommentsTest {
    @Test
    fun protoCommentsHasCommentsReturnsFalseForEmpty() {
        val comments = ProtoComments()
        assertFalse(comments.hasComments())
    }

    @Test
    fun protoCommentsHasCommentsReturnsTrueForLeading() {
        val comments = ProtoComments(leadingComments = "Leading comment")
        assertTrue(comments.hasComments())
    }

    @Test
    fun protoCommentsHasCommentsReturnsTrueForTrailing() {
        val comments = ProtoComments(trailingComments = "Trailing comment")
        assertTrue(comments.hasComments())
    }

    @Test
    fun protoCommentsHasCommentsReturnsTrueForDetached() {
        val comments = ProtoComments(leadingDetachedComments = listOf("Detached comment"))
        assertTrue(comments.hasComments())
    }

    @Test
    fun protoCommentsHasCommentsReturnsFalseForBlankStrings() {
        val comments = ProtoComments(leadingComments = "   ", trailingComments = "  \n  ")
        assertFalse(comments.hasComments())
    }

    @Test
    fun protoCommentsGetCombinedCommentReturnsEmptyForNoComments() {
        val comments = ProtoComments()
        assertEquals("", comments.getCombinedComment())
    }

    @Test
    fun protoCommentsGetCombinedCommentReturnsLeading() {
        val comments = ProtoComments(leadingComments = "Leading comment")
        assertEquals("Leading comment", comments.getCombinedComment())
    }

    @Test
    fun protoCommentsGetCombinedCommentReturnsTrailing() {
        val comments = ProtoComments(trailingComments = "Trailing comment")
        assertEquals("Trailing comment", comments.getCombinedComment())
    }

    @Test
    fun protoCommentsGetCombinedCommentCombinesAllComments() {
        val comments =
            ProtoComments(
                leadingDetachedComments = listOf("Detached 1", "Detached 2"),
                leadingComments = "Leading comment",
                trailingComments = "Trailing comment",
            )
        assertEquals("Detached 1\n\nDetached 2\n\nLeading comment\n\nTrailing comment", comments.getCombinedComment())
    }

    @Test
    fun protoCommentsGetCombinedCommentTrimsWhitespace() {
        val comments = ProtoComments(leadingComments = "  Leading comment  ")
        assertEquals("Leading comment", comments.getCombinedComment())
    }

    @Test
    fun protoCommentsGetCombinedCommentSkipsBlankDetached() {
        val comments =
            ProtoComments(
                leadingDetachedComments = listOf("  ", "Valid comment", "   "),
                leadingComments = "Leading",
            )
        assertEquals("Valid comment\n\nLeading", comments.getCombinedComment())
    }
}
