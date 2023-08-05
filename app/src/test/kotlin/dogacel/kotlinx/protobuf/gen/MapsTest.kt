package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.kotlin.toByteStringUtf8
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.junit.jupiter.api.Assertions
import testgen.maps.MapsMessage
import kotlin.test.Test

class MapsTest {
    @Test
    fun shouldDeSerAll() {
        val message = maps.Maps.MapsMessage.newBuilder()
            .putAllMapInt32Int32(mapOf(1 to 2, 3 to 4))
            .putAllMapInt64Int64(mapOf(1L to 2L, 3L to 4L))
            .putAllMapUint32Uint32(mapOf(1 to 2, 3 to 4))
            .putAllMapUint64Uint64(mapOf(1L to 2L, 3L to 4L))
            .putAllMapSint32Sint32(mapOf(1 to 2, 3 to 4))
            .putAllMapSint64Sint64(mapOf(1L to 2L, 3L to 4L))
//            .putAllMapFixed32Fixed32(mapOf(1 to 2, 3 to 4))
//            .putAllMapFixed64Fixed64(mapOf(1L to 2L, 3L to 4L))
//            .putAllMapSfixed32Sfixed32(mapOf(1 to 2, 3 to 4))
//            .putAllMapSfixed64Sfixed64(mapOf(1L to 2L, 3L to 4L))
            .putAllMapInt32Float(mapOf(1 to 2f, 3 to 4f))
            .putAllMapInt32Double(mapOf(1 to 2.0, 3 to 4.0))
            .putAllMapBoolBool(mapOf(true to false, false to true))
            .putAllMapStringString(mapOf("1" to "2", "3" to "4"))
            .putAllMapStringBytes(mapOf("1" to "2".toByteStringUtf8(), "3" to "4".toByteStringUtf8()))
            .putAllMapStringNestedMessage(
                mapOf(
                    "1" to maps.Maps.MapsMessage.NestedMessage.getDefaultInstance(),
                    "3" to maps.Maps.MapsMessage.NestedMessage.getDefaultInstance()
                )
            )
            .putAllMapStringForeignMessage(
                mapOf(
                    "1" to maps.Maps.ForeignMessage.getDefaultInstance(),
                    "3" to maps.Maps.ForeignMessage.getDefaultInstance()
                )
            )
            .putAllMapStringNestedEnum(
                mapOf(
                    "1" to maps.Maps.MapsMessage.NestedEnum.FOO,
                    "3" to maps.Maps.MapsMessage.NestedEnum.BAR
                )
            )
            .putAllMapStringForeignEnum(
                mapOf(
                    "1" to maps.Maps.ForeignEnum.FOREIGN_FOO,
                    "3" to maps.Maps.ForeignEnum.FOREIGN_BAZ
                )
            )
            .build()

        val result: MapsMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())
        val deser = maps.Maps.MapsMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        Assertions.assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = maps.Maps.MapsMessage.newBuilder().build()

        val result: MapsMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())
        val deser = maps.Maps.MapsMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        Assertions.assertEquals(message, deser)
    }
}
