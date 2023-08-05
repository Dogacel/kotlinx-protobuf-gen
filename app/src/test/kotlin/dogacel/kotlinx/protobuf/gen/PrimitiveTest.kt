package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.kotlin.toByteStringUtf8
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.junit.jupiter.api.Assertions.assertEquals
import testgen.primitives.PrimitivesMessage
import kotlin.test.Test

class PrimitiveTest {
    @Test
    fun shouldDeSerAll() {
        val message = primitives.Primitives.PrimitivesMessage.newBuilder()
            .setOptionalInt32(32)
            .setOptionalInt64(57L)
            .setOptionalUint32(12)
            .setOptionalUint64(68L)
            .setOptionalFixed32(4358)
            .setOptionalFixed64(543587L)
            .setOptionalSint32(283)
            .setOptionalSint64(349L)
            .setOptionalSfixed32(21934)
            .setOptionalSfixed64(901235L)
            .setOptionalFloat(0.123f)
            .setOptionalDouble(0.391284)
            .setOptionalBool(true)
            .setOptionalString("Hello")
            .setOptionalBytes("934u9234".toByteStringUtf8())
            .build()

        val result: PrimitivesMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())
        val deser = primitives.Primitives.PrimitivesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = primitives.Primitives.PrimitivesMessage.newBuilder().build()

        val result: PrimitivesMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())
        val deser = primitives.Primitives.PrimitivesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        assertEquals(message, deser)
    }
}
