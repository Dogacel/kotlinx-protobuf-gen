package dogacel.kotlinx.protobuf.gen.proto3

import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import testgen.oneof.proto3.OneofMessage
import kotlin.test.Test
import oneof.proto3.Oneof

class OneofTest {
    @Test
    fun shouldDeSerAll() {
        val message = Oneof.OneofMessage.newBuilder()

        fun validateDeser(messageParam: Oneof.OneofMessage): OneofMessage {
            val result: OneofMessage = ProtoBuf.decodeFromByteArray(messageParam.toByteArray())

            val deser = Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
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
            validateDeser(message.setOneofEnum(Oneof.OneofMessage.NestedEnum.BAZ).build()).oneofEnum
        )
        assertEquals("1", validateDeser(message.setLeft("1").build()).left)
        assertEquals("1", validateDeser(message.setRight("1").build()).right)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = Oneof.OneofMessage.newBuilder().build()

        val result: OneofMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        val deser = Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    fun oneofRules() {
        assertFailsWith<IllegalArgumentException> {
            OneofMessage(
                oneofDouble = 420.0,
                oneofString = "300"
            )
        }

        val emptyMessage = Oneof.OneofMessage.getDefaultInstance()
        val emptyConstructed: OneofMessage = ProtoBuf.decodeFromByteArray(emptyMessage.toByteArray())

        assertEquals(OneofMessage(), emptyConstructed)

        assertFailsWith<IllegalArgumentException> {
            OneofMessage(
                oneofEnum = OneofMessage.NestedEnum.BAZ
            ).copy(oneofUint32 = 123U)
        }
    }
}
