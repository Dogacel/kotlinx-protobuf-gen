package dogacel.kotlinx.protobuf.gen.json

import com.google.protobuf.util.JsonFormat
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.test.assertEquals
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
            .setOptionalSint32(-283)
            .setOptionalSint64(349L)
            .setOptionalSfixed32(21934)
            .setOptionalSfixed64(901235L)
            .setOptionalFloat(0.123f)
            .setOptionalDouble(0.391284)
            .setOptionalBool(true)
            .setOptionalString("Hello")
//            .setOptionalBytes("934u9234".toByteStringUtf8())
            .build()

        val protoJson = JsonFormat.printer().print(message)
        val result: PrimitivesMessage = Json.decodeFromString(protoJson)

        assertEquals(message.optionalInt32, result.optionalInt32)
        assertEquals(message.optionalInt64, result.optionalInt64)
        assertEquals(message.optionalUint32.toUInt(), result.optionalUint32)
        assertEquals(message.optionalUint64.toULong(), result.optionalUint64)
        assertEquals(message.optionalFixed32, result.optionalFixed32)
        assertEquals(message.optionalFixed64, result.optionalFixed64)
        assertEquals(message.optionalSint32, result.optionalSint32)
        assertEquals(message.optionalSint64, result.optionalSint64)
        assertEquals(message.optionalSfixed32, result.optionalSfixed32)
        assertEquals(message.optionalSfixed64, result.optionalSfixed64)
        assertEquals(message.optionalFloat, result.optionalFloat)
        assertEquals(message.optionalDouble, result.optionalDouble)
        assertEquals(message.optionalBool, result.optionalBool)
        assertEquals(message.optionalString, result.optionalString)

        val deser = primitives.Primitives.PrimitivesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = primitives.Primitives.PrimitivesMessage.newBuilder().build()

        val result: PrimitivesMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        assertEquals(message.optionalInt32, result.optionalInt32)
        assertEquals(message.optionalInt64, result.optionalInt64)
        assertEquals(message.optionalUint32.toUInt(), result.optionalUint32)
        assertEquals(message.optionalUint64.toULong(), result.optionalUint64)
        assertEquals(message.optionalFixed32, result.optionalFixed32)
        assertEquals(message.optionalFixed64, result.optionalFixed64)
        assertEquals(message.optionalSint32, result.optionalSint32)
        assertEquals(message.optionalSint64, result.optionalSint64)
        assertEquals(message.optionalSfixed32, result.optionalSfixed32)
        assertEquals(message.optionalSfixed64, result.optionalSfixed64)
        assertEquals(message.optionalFloat, result.optionalFloat)
        assertEquals(message.optionalDouble, result.optionalDouble)
        assertEquals(message.optionalBool, result.optionalBool)
        assertEquals(message.optionalString, result.optionalString)

        val deser = primitives.Primitives.PrimitivesMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }
}
