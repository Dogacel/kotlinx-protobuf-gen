package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.kotlin.toByteStringUtf8
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.junit.jupiter.api.Assertions.assertEquals
import testgen.maps.MapsMessage
import kotlin.test.Test

class MapTest {
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

        assertEquals(message.mapInt32Int32Map, result.mapInt32Int32)
        assertEquals(message.mapInt64Int64Map, result.mapInt64Int64)
        assertEquals(message.mapUint32Uint32Map, result.mapUint32Uint32.mapKeys { it.key.toInt() }.mapValues { it.value.toInt() })
        assertEquals(message.mapUint64Uint64Map, result.mapUint64Uint64.mapKeys { it.key.toLong() }.mapValues { it.value.toLong() })
//        assertEquals(message.mapSint32Sint32Map, result.mapSint32Sint32)
//        assertEquals(message.mapSint64Sint64Map, result.mapSint64Sint64)
        assertEquals(message.mapFixed32Fixed32Map, result.mapFixed32Fixed32)
        assertEquals(message.mapFixed64Fixed64Map, result.mapFixed64Fixed64)
        assertEquals(message.mapSfixed32Sfixed32Map, result.mapSfixed32Sfixed32)
        assertEquals(message.mapSfixed64Sfixed64Map, result.mapSfixed64Sfixed64)
        assertEquals(message.mapInt32FloatMap, result.mapInt32Float)
        assertEquals(message.mapInt32DoubleMap, result.mapInt32Double)
        assertEquals(message.mapBoolBoolMap, result.mapBoolBool)
        assertEquals(message.mapStringStringMap, result.mapStringString)
//        assertEquals(message.mapStringBytesMap, result.mapStringBytes)
//        assertEquals(message.mapStringNestedMessageMap, result.mapStringNestedMessage)
//        assertEquals(message.mapStringForeignMessageMap, result.mapStringForeignMessage)
        assertEquals(message.mapStringNestedEnumMap.mapValues { it.value.name }, result.mapStringNestedEnum.mapValues { it.value.name })
        assertEquals(message.mapStringForeignEnumMap.mapValues { it.value.name }, result.mapStringForeignEnum.mapValues { it.value.name })

        val deser = maps.Maps.MapsMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        assertEquals(message, deser)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = maps.Maps.MapsMessage.newBuilder().build()

        val result: MapsMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        assertEquals(message.mapInt32Int32Map, result.mapInt32Int32)
        assertEquals(message.mapInt64Int64Map, result.mapInt64Int64)
        assertEquals(message.mapUint32Uint32Map, result.mapUint32Uint32.mapKeys { it.key.toInt() }.mapValues { it.value.toInt() })
        assertEquals(message.mapUint64Uint64Map, result.mapUint64Uint64.mapKeys { it.key.toLong() }.mapValues { it.value.toLong() })
        assertEquals(message.mapSint32Sint32Map, result.mapSint32Sint32)
        assertEquals(message.mapSint64Sint64Map, result.mapSint64Sint64)
        assertEquals(message.mapFixed32Fixed32Map, result.mapFixed32Fixed32)
        assertEquals(message.mapFixed64Fixed64Map, result.mapFixed64Fixed64)
        assertEquals(message.mapSfixed32Sfixed32Map, result.mapSfixed32Sfixed32)
        assertEquals(message.mapSfixed64Sfixed64Map, result.mapSfixed64Sfixed64)
        assertEquals(message.mapInt32FloatMap, result.mapInt32Float)
        assertEquals(message.mapInt32DoubleMap, result.mapInt32Double)
        assertEquals(message.mapBoolBoolMap, result.mapBoolBool)
        assertEquals(message.mapStringStringMap, result.mapStringString)
        assertEquals(message.mapStringBytesMap, result.mapStringBytes)
        assertEquals(message.mapStringNestedMessageMap, result.mapStringNestedMessage)
        assertEquals(message.mapStringForeignMessageMap, result.mapStringForeignMessage)
        assertEquals(message.mapStringNestedEnumMap.mapValues { it.value.name }, result.mapStringNestedEnum.mapValues { it.value.name })
        assertEquals(message.mapStringForeignEnumMap.mapValues { it.value.name }, result.mapStringForeignEnum.mapValues { it.value.name })


        val deser = maps.Maps.MapsMessage.parseFrom(ProtoBuf.encodeToByteArray(result))

        assertEquals(message, deser)
    }
}
