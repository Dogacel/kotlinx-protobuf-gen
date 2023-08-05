package dogacel.kotlinx.protobuf.gen

import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import testgen.messages.MessagesMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class MessageTest {
    @Test
    fun shouldDeSerAll() {
        val message = messages.Messages.MessagesMessage.newBuilder()
            .setId("2908590234sadkA_+AS#$")
            .setOptionalForeignMessage(
                messages.Messages.ForeignMessage.newBuilder()
                    .setC(2983741)
            )
            .setOptionalNestedMessage(
                messages.Messages.MessagesMessage.NestedMessage.newBuilder()
                    .setA(-902394)
                    .setCorecursive(
                        messages.Messages.MessagesMessage.newBuilder()
                            .setId("_324")
                            .setOptionalForeignMessage(
                                messages.Messages.ForeignMessage.newBuilder()
                                    .setC(2983741)
                            )
                    )
            )
            .build()

        val messageBytes = message.toByteArray()
        val result: MessagesMessage = ProtoBuf.decodeFromByteArray(messageBytes)

        assertEquals(message.id, result.id)
        assertEquals(message.optionalForeignMessage.c, result.optionalForeignMessage?.c)
        assertEquals(message.optionalNestedMessage.a, result.optionalNestedMessage?.a)
        assertEquals(message.optionalNestedMessage.corecursive.id, result.optionalNestedMessage?.corecursive?.id)
        assertEquals(
            message.optionalNestedMessage.corecursive.optionalForeignMessage.c,
            result.optionalNestedMessage?.corecursive?.optionalForeignMessage?.c
        )

        val deser = messages.Messages.MessagesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = messages.Messages.MessagesMessage.newBuilder().build()

        val messageBytes = message.toByteArray()
        val result: MessagesMessage = ProtoBuf.decodeFromByteArray(messageBytes)

        assertEquals(message.id, result.id)
        assertEquals(null, result.optionalForeignMessage)
        assertEquals(null, result.optionalNestedMessage)

        val deser = messages.Messages.MessagesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }
}
