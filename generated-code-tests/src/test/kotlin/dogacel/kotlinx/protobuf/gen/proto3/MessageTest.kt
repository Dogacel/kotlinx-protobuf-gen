package dogacel.kotlinx.protobuf.gen.proto3

import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import testgen.messages.proto3.MessageNoFields
import testgen.messages.proto3.MessagesMessage
import kotlin.test.Test
import kotlin.test.assertEquals
import messages.proto3.MessageNoFieldsOuterClass
import messages.proto3.Messages

class MessageTest {
    @Test
    fun shouldDeSerAll() {
        val message = Messages.MessagesMessage.newBuilder()
            .setId("2908590234sadkA_+AS#$")
            .setOptionalForeignMessage(
                Messages.ForeignMessage.newBuilder()
                    .setC(2983741)
            )
            .setOptionalNestedMessage(
                Messages.MessagesMessage.NestedMessage.newBuilder()
                    .setA(-902394)
                    .setCorecursive(
                        Messages.MessagesMessage.newBuilder()
                            .setId("_324")
                            .setOptionalForeignMessage(
                                Messages.ForeignMessage.newBuilder()
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
        assertEquals(
            message.optionalNestedMessage.corecursive.id,
            result.optionalNestedMessage?.corecursive?.id
        )
        assertEquals(
            message.optionalNestedMessage.corecursive.optionalForeignMessage.c,
            result.optionalNestedMessage?.corecursive?.optionalForeignMessage?.c
        )

        val deser = Messages.MessagesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = Messages.MessagesMessage.newBuilder().build()

        val messageBytes = message.toByteArray()
        val result: MessagesMessage = ProtoBuf.decodeFromByteArray(messageBytes)

        assertEquals(message.id, result.id)
        assertEquals(null, result.optionalForeignMessage)
        assertEquals(null, result.optionalNestedMessage)

        val deser = Messages.MessagesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    fun shouldSerNoField() {
        val message = MessageNoFieldsOuterClass.MessageNoFields.newBuilder().build()

        val messageBytes = message.toByteArray()
        val result: MessageNoFields = ProtoBuf.decodeFromByteArray(messageBytes)

        val deser = MessageNoFieldsOuterClass.MessageNoFields.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }
}
