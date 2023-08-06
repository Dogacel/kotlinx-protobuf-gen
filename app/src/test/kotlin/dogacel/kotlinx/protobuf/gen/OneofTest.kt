package dogacel.kotlinx.protobuf.gen

import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.junit.jupiter.api.Assertions.assertEquals
import testgen.oneof.OneofMessage
import kotlin.test.Ignore
import kotlin.test.Test

class OneofTest {
    @Test
    fun shouldDeSerAll() {
        val message = oneof.Oneof.OneofMessage.newBuilder()

        fun validateDeser(messageParam: oneof.Oneof.OneofMessage): OneofMessage {
            val result: OneofMessage = ProtoBuf.decodeFromByteArray(messageParam.toByteArray())

            val deser = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
            assertEquals(messageParam, deser)
            return result
        }

        assertEquals(1U, validateDeser(message.setOneofUint32(1).build()).oneofUint32)
        assertEquals("1", validateDeser(message.setOneofString("1").build()).oneofString)
        assertEquals(true, validateDeser(message.setOneofBool(true).build()).oneofBool)
        assertEquals(1UL, validateDeser(message.setOneofUint64(1).build()).oneofUint64)
        assertEquals(1.0f, validateDeser(message.setOneofFloat(1.0f).build()).oneofFloat)
        assertEquals(1.0, validateDeser(message.setOneofDouble(1.0).build()).oneofDouble)
        assertEquals(
            OneofMessage.NestedEnum.BAZ,
            validateDeser(message.setOneofEnum(oneof.Oneof.OneofMessage.NestedEnum.BAZ).build()).oneofEnum
        )
        assertEquals("1", validateDeser(message.setLeft("1").build()).left)
        assertEquals("1", validateDeser(message.setRight("1").build()).right)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = oneof.Oneof.OneofMessage.newBuilder().build()

        val result: OneofMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        val deser = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    @Ignore("Only one oneof field can be set at a time, current implementation doesn't account it.")
    fun failMultipleOneofs() {
        val kMessage = OneofMessage(
            oneofDouble = 420.0,
            oneofString = "300"
        )

        // Should probably throw an exception or should be modelled using sealed classes.
        val pMessage = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(kMessage))

        assertEquals(kMessage.oneofDouble, pMessage.oneofDouble)
        assertEquals(kMessage.oneofString, pMessage.oneofString)
    }
}
