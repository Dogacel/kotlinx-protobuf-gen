package dogacel.kotlinx.protobuf.gen

import enums.Enums
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import testgen.enums.MessageWithEnum
import kotlin.test.Test
import kotlin.test.assertEquals

class EnumTest {
    @Test
    fun shouldDeSerEnum() {
        Enums.TestEnum.entries.filterNot {
            it in listOf(
                Enums.TestEnum.NEG, // TODO: NEG is not supported yet, "Index -1 out of bounds for length 5"
                Enums.TestEnum.UNRECOGNIZED,
            )
        }.forEach {
            val someProtoEnum = it
            val someWrapperMessage = enums.Enums.MessageWithEnum.newBuilder()
                .setTestEnum(someProtoEnum)
                .build()

            val bytes = someWrapperMessage.toByteArray()

            val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)
            val deser = enums.Enums.MessageWithEnum.parseFrom(
                ProtoBuf.encodeToByteArray(result)
            )

            assertEquals(
                someWrapperMessage,
                deser,
            )
        }
    }

    @Test
    fun shouldDeSerAliasEnum() {
        (Enums.AliasedEnum.entries
                + listOf(
            Enums.AliasedEnum.QUX,
            Enums.AliasedEnum.qux,
            Enums.AliasedEnum.bAz,
        ))
            .filterNot { it == Enums.AliasedEnum.UNRECOGNIZED }.forEach {
                val someProtoEnum = it
                val someWrapperMessage = enums.Enums.MessageWithEnum.newBuilder()
                    .setAliasedEnum(someProtoEnum)
                    .build()

                val bytes = someWrapperMessage.toByteArray()

                val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)
                val deser = enums.Enums.MessageWithEnum.parseFrom(
                    ProtoBuf.encodeToByteArray(result)
                )

                assertEquals(
                    someWrapperMessage,
                    deser,
                )
            }
    }

    @Test
    fun shouldDeSerNestedEnum() {
        Enums.MessageWithEnum.NestedEnum.entries.filterNot {
            it == Enums.MessageWithEnum.NestedEnum.UNRECOGNIZED
        }.forEach {
            val someProtoEnum = it
            val someWrapperMessage = enums.Enums.MessageWithEnum.newBuilder()
                .setNestedEnum(someProtoEnum)
                .build()

            val bytes = someWrapperMessage.toByteArray()

            val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)
            val deser = enums.Enums.MessageWithEnum.parseFrom(
                ProtoBuf.encodeToByteArray(result)
            )

            assertEquals(
                someWrapperMessage,
                deser,
            )
        }
    }
}
