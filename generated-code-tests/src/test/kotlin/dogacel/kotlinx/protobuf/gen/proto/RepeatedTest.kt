package dogacel.kotlinx.protobuf.gen.proto

import com.google.protobuf.kotlin.toByteStringUtf8
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.junit.jupiter.api.Assertions.assertEquals
import testgen.repeateds.RepeatedsMessage
import kotlin.test.Test

class RepeatedTest {
    @Test
    fun shouldDeSerAll() {
        val message = repeateds.Repeateds.RepeatedsMessage.newBuilder()
            .addAllRepeatedInt32(listOf(134, -231, 31))
            .addAllRepeatedInt64(listOf(2384L, 214L, -12933L))
//            .addAllRepeatedUint32(listOf(1234, 9192, 317))
//            .addAllRepeatedUint64(listOf(4921L, 22900L, 34589L))
            .addAllRepeatedSint32(listOf(2391, -90413, 12903))
            .addAllRepeatedSint64(listOf(41L, 93492892839938989L, 9L))
//            .addAllRepeatedFixed32(listOf(93, -1293, 1923291))
//            .addAllRepeatedFixed64(listOf(1293L, -21398210943284L, 193L))
//            .addAllRepeatedSfixed32(listOf(1293, -1293, 1923291))
//            .addAllRepeatedSfixed64(listOf(1293L, 193L))
            .addAllRepeatedFloat(listOf(1.0f, 2.0f, -3.0f))
            .addAllRepeatedDouble(listOf(1.0, -2.0, 3.0))
            .addAllRepeatedBool(listOf(true, false))
            .addAllRepeatedString(listOf("foo", "bar", "baz", "lol"))
            .addAllRepeatedBytes(
                listOf(
                    "asdkj23u9u90ASDIJ4)%_#Q",
                    "",
                    "iewdu923+_A"
                ).map { it.toByteStringUtf8() }
            )
            .addAllRepeatedNestedMessage(
                listOf(
                    repeateds.Repeateds.RepeatedsMessage.NestedMessage.newBuilder().setA(-1).build(),
                    repeateds.Repeateds.RepeatedsMessage.NestedMessage.newBuilder().setA(3).build()
                )
            )
            .addAllRepeatedForeignMessage(
                listOf(
                    repeateds.Repeateds.ForeignMessage.newBuilder().setC(1).build(),
                    repeateds.Repeateds.ForeignMessage.newBuilder().build(),
                    repeateds.Repeateds.ForeignMessage.newBuilder().setC(-3).build()
                )
            )
//            .addAllRepeatedNestedEnum(
//                listOf(
//                    repeateds.Repeateds.RepeatedsMessage.NestedEnum.FOO,
//                    repeateds.Repeateds.RepeatedsMessage.NestedEnum.BAR,
//                    repeateds.Repeateds.RepeatedsMessage.NestedEnum.BAZ
//                )
//            )
//            .addAllRepeatedForeignEnum(
//                listOf(
//                    repeateds.Repeateds.ForeignEnum.FOREIGN_FOO,
//                    repeateds.Repeateds.ForeignEnum.FOREIGN_BAR,
//                    repeateds.Repeateds.ForeignEnum.FOREIGN_BAZ
//                )
//            )
            .build()

        val result: RepeatedsMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        assertEquals(message.repeatedInt32List, result.repeatedInt32)
        assertEquals(message.repeatedInt64List, result.repeatedInt64)
        assertEquals(message.repeatedUint32List.map { it.toUInt() }, result.repeatedUint32)
        assertEquals(message.repeatedUint64List.map { it.toULong() }, result.repeatedUint64)
//        assertEquals(message.repeatedSint32List, result.repeatedSint32)
//        assertEquals(message.repeatedSint64List, result.repeatedSint64)
        assertEquals(message.repeatedFixed32List, result.repeatedFixed32)
        assertEquals(message.repeatedFixed64List, result.repeatedFixed64)
        assertEquals(message.repeatedSfixed32List, result.repeatedSfixed32)
        assertEquals(message.repeatedSfixed64List, result.repeatedSfixed64)
        assertEquals(message.repeatedFloatList, result.repeatedFloat)
        assertEquals(message.repeatedDoubleList, result.repeatedDouble)
        assertEquals(message.repeatedBoolList, result.repeatedBool)
        assertEquals(message.repeatedStringList, result.repeatedString)
//        assertEquals(message.repeatedBytesList, result.repeatedBytes)
//        assertEquals(message.repeatedNestedMessageList, result.repeatedNestedMessage)
//        assertEquals(message.repeatedForeignMessageList, result.repeatedForeignMessage)
        assertEquals(message.repeatedNestedEnumList.map { it.name }, result.repeatedNestedEnum.map { it.name })
        assertEquals(
            message.repeatedForeignEnumList.map { it.name },
            result.repeatedForeignEnum.map { it.name }
        )

        val deser = repeateds.Repeateds.RepeatedsMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = repeateds.Repeateds.RepeatedsMessage.newBuilder().build()

        val result: RepeatedsMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        assertEquals(message.repeatedInt32List, result.repeatedInt32)
        assertEquals(message.repeatedInt64List, result.repeatedInt64)
        assertEquals(message.repeatedUint32List.map { it.toUInt() }, result.repeatedUint32)
        assertEquals(message.repeatedUint64List.map { it.toULong() }, result.repeatedUint64)
        assertEquals(message.repeatedSint32List, result.repeatedSint32)
        assertEquals(message.repeatedSint64List, result.repeatedSint64)
        assertEquals(message.repeatedFixed32List, result.repeatedFixed32)
        assertEquals(message.repeatedFixed64List, result.repeatedFixed64)
        assertEquals(message.repeatedSfixed32List, result.repeatedSfixed32)
        assertEquals(message.repeatedSfixed64List, result.repeatedSfixed64)
        assertEquals(message.repeatedFloatList, result.repeatedFloat)
        assertEquals(message.repeatedDoubleList, result.repeatedDouble)
        assertEquals(message.repeatedBoolList, result.repeatedBool)
        assertEquals(message.repeatedStringList, result.repeatedString)
        assertEquals(message.repeatedBytesList, result.repeatedBytes)
        assertEquals(message.repeatedNestedMessageList, result.repeatedNestedMessage)
        assertEquals(message.repeatedForeignMessageList, result.repeatedForeignMessage)
        assertEquals(message.repeatedNestedEnumList.map { it.name }, result.repeatedNestedEnum.map { it.name })
        assertEquals(
            message.repeatedForeignEnumList.map { it.name },
            result.repeatedForeignEnum.map { it.name }
        )

        val deser = repeateds.Repeateds.RepeatedsMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        assertEquals(message, deser)
    }
}
