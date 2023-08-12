package dogacel.kotlinx.protobuf.gen

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlinx.serialization.protobuf.ProtoNumber
import org.junit.jupiter.api.Assertions.assertEquals
import testgen.oneof.OneofMessage
import kotlin.test.Ignore
import kotlin.test.Test

class OneofPOC {
    @Test
    @Ignore
    fun shouldDeSerAll() {
        val message = oneof.Oneof.OneofMessage.newBuilder()

        fun validateDeser(messageParam: oneof.Oneof.OneofMessage): OneofPOCMessage {
            val result: OneofPOCMessage = ProtoBuf.decodeFromByteArray(messageParam.toByteArray())

            val deser = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
            assertEquals(messageParam, deser)
            return result
        }

        assertEquals(1U, validateDeser(message.setOneofUint32(1).build()).oneofField?.value)
        assertEquals("1", validateDeser(message.setOneofString("1").build()).oneofField?.value)
        assertEquals(true, validateDeser(message.setOneofBool(true).build()).oneofField?.value)
        assertEquals(1UL, validateDeser(message.setOneofUint64(1).build()).oneofField?.value)
        assertEquals(1.0f, validateDeser(message.setOneofFloat(1.0f).build()).oneofField?.value)
        assertEquals(1.0, validateDeser(message.setOneofDouble(1.0).build()).oneofField?.value)
        assertEquals(
            OneofPOCMessage.NestedEnum.BAZ,
            validateDeser(
                message.setOneofEnum(oneof.Oneof.OneofMessage.NestedEnum.BAZ).build()
            ).oneofField?.value
        )
        assertEquals("1", validateDeser(message.setLeft("1").build()).secondOneOfField?.value)
        assertEquals("1", validateDeser(message.setRight("1").build()).secondOneOfField?.value)
    }

    @Test
    fun shouldDeSerEmpty() {
        val message = oneof.Oneof.OneofMessage.newBuilder().build()

        val result: OneofPOCMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        val deser = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }
}

interface KOneof<T : Any> {
    val value: T
}

@Serializable
public data class OneofPOCMessage(
    public val oneofField: OneofField<@Contextual Any>? = null,
    public val secondOneOfField: SecondOneOfField<@Contextual Any>? = null
) {
    sealed interface OneofField<T : Any> : KOneof<T> {
        @JvmInline
        value class Uint32(
            @ProtoNumber(number = 1)
            override val value: UInt
        ) : OneofField<UInt>

        @JvmInline
        value class NestedMessage(
            @ProtoNumber(number = 2)
            override val value: OneofMessage
        ) : OneofField<OneofMessage>

        @JvmInline
        value class String(
            @ProtoNumber(number = 3)
            override val value: kotlin.String
        ) : OneofField<kotlin.String>

        @JvmInline
        value class Bool(
            @ProtoNumber(number = 5)
            override val value: kotlin.Boolean
        ) : OneofField<kotlin.Boolean>

        @JvmInline
        value class Uint64(
            @ProtoNumber(number = 6)
            override val value: ULong
        ) : OneofField<kotlin.ULong>

        @JvmInline
        value class Float(
            @ProtoNumber(number = 7)
            override val value: kotlin.Float
        ) : OneofField<kotlin.Float>

        @JvmInline
        value class Double(
            @ProtoNumber(number = 8)
            override val value: kotlin.Double
        ) : OneofField<kotlin.Double>

        @JvmInline
        value class Enum(
            @ProtoNumber(number = 9)
            override val value: NestedEnum
        ) : OneofField<NestedEnum>
    }

    @Serializable
    sealed interface SecondOneOfField<T : Any> : KOneof<T> {
        @JvmInline
        value class Left(
            @ProtoNumber(number = 10)
            override val value: String
        ) : SecondOneOfField<String>

        @JvmInline
        value class Right(
            @ProtoNumber(number = 11)
            override val value: String
        ) : SecondOneOfField<String>
    }

    @Serializable
    public data class NestedMessage(
        @ProtoNumber(number = 1)
        public val a: Int = 0,
        @ProtoNumber(number = 2)
        public val corecursive: OneofMessage? = null
    )

    @Serializable
    public enum class NestedEnum {
        @ProtoNumber(number = 0)
        FOO,

        @ProtoNumber(number = 1)
        BAR,

        @ProtoNumber(number = 2)
        BAZ,

        @ProtoNumber(number = -1)
        NEG
    }
}
