package dogacel.kotlinx.protobuf.gen.proto

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
            // NEG is not supported yet, "Index -1 out of bounds for length 5"
            // https://github.com/Kotlin/kotlinx.serialization/pull/2400
            it in listOf(
                Enums.TestEnum.NEG,
                Enums.TestEnum.UNRECOGNIZED
            )
        }.forEach {
            val someProtoEnum = it
            val someWrapperMessage = Enums.MessageWithEnum.newBuilder()
                .setTestEnum(someProtoEnum)
                .build()

            val bytes = someWrapperMessage.toByteArray()
            val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)

            assertEquals(
                someProtoEnum.name,
                result.testEnum?.name
            )

            val deser = Enums.MessageWithEnum.parseFrom(
                ProtoBuf.encodeToByteArray(result)
            )

            assertEquals(
                someWrapperMessage,
                deser
            )
        }
    }

    @Test
    fun shouldDeSerAliasEnum() {
        (
            Enums.AliasedEnum.entries +
                listOf(
                    Enums.AliasedEnum.QUX,
                    Enums.AliasedEnum.qux,
                    Enums.AliasedEnum.bAz
                )
            )
            .filterNot { it == Enums.AliasedEnum.UNRECOGNIZED }.forEach {
                val someProtoEnum = it
                val someWrapperMessage = Enums.MessageWithEnum.newBuilder()
                    .setAliasedEnum(someProtoEnum)
                    .build()

                val bytes = someWrapperMessage.toByteArray()
                val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)

                assertEquals(
                    someProtoEnum.name,
                    result.aliasedEnum?.name
                )

                val deser = Enums.MessageWithEnum.parseFrom(
                    ProtoBuf.encodeToByteArray(result)
                )

                assertEquals(
                    someWrapperMessage,
                    deser
                )
            }
    }

    @Test
    fun shouldDeSerNestedEnum() {
        Enums.MessageWithEnum.NestedEnum.entries.filterNot {
            it == Enums.MessageWithEnum.NestedEnum.UNRECOGNIZED
        }.forEach {
            val someProtoEnum = it
            val someWrapperMessage = Enums.MessageWithEnum.newBuilder()
                .setNestedEnum(someProtoEnum)
                .build()

            val bytes = someWrapperMessage.toByteArray()
            val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)

            assertEquals(
                someProtoEnum.name,
                result.nestedEnum?.name
            )

            val deser = Enums.MessageWithEnum.parseFrom(
                ProtoBuf.encodeToByteArray(result)
            )

            assertEquals(
                someWrapperMessage,
                deser
            )
        }
    }
}
