package dogacel.kotlinx.protobuf.gen.wkt

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

//    @ProtoNumber(number = 12)
//    public val optionalFieldMask: FieldMask? = null,
//    @ProtoNumber(number = 13)
//    public val optionalStruct: Struct? = null,
//    @ProtoNumber(number = 14)
//    public val optionalAny: Any? = null,
//    @ProtoNumber(number = 15)
//    public val optionalValue: Value? = null,
//    @ProtoNumber(number = 16)
//    public val repeatedListValue: ListValue? = null,

typealias BoolValueP =
    @Serializable(with = BoolValueSerializer::class)
    Boolean?
typealias Int32ValueP =
    @Serializable(with = Int32ValueSerializer::class)
    Int?
typealias Int64ValueP =
    @Serializable(with = Int64ValueSerializer::class)
    Long?
typealias UInt32ValueP =
    @Serializable(with = UInt32ValueSerializer::class)
    UInt?
typealias UInt64ValueP =
    @Serializable(with = UInt64ValueSerializer::class)
    ULong?
typealias FloatValueP =
    @Serializable(with = FloatValueSerializer::class)
    Float?
typealias DoubleValueP =
    @Serializable(with = DoubleValueSerializer::class)
    Double?
typealias StringValueP =
    @Serializable(with = StringValueSerializer::class)
    String?
typealias BytesValueP =
    @Serializable(with = BytesValueSerializer::class)
    ByteArray?
typealias DurationP =
    @Serializable(with = DurationSerializer::class)
    Duration?
typealias TimestampP =
    @Serializable(with = TimestampSerializer::class)
    Instant?

object BoolValueSerializer : KSerializer<Boolean?> {
    @Serializable
    private data class Boxed(
        val `value`: Boolean = false,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Boolean?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): Boolean? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object Int32ValueSerializer : KSerializer<Int?> {
    @Serializable
    private data class Boxed(
        val `value`: Int = 0,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Int?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): Int? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object Int64ValueSerializer : KSerializer<Long?> {
    @Serializable
    private data class Boxed(
        val `value`: Long = 0L,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Long?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): Long? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object UInt32ValueSerializer : KSerializer<UInt?> {
    @Serializable
    private data class Boxed(
        val `value`: UInt = 0U,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: UInt?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): UInt? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object UInt64ValueSerializer : KSerializer<ULong?> {
    @Serializable
    private data class Boxed(
        val `value`: ULong = 0UL,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: ULong?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): ULong? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object FloatValueSerializer : KSerializer<Float?> {
    @Serializable
    private data class Boxed(
        val `value`: Float = 0f,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Float?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): Float? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object DoubleValueSerializer : KSerializer<Double?> {
    @Serializable
    private data class Boxed(
        val `value`: Double = 0.0,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Double?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): Double? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object StringValueSerializer : KSerializer<String?> {
    @Serializable
    private data class Boxed(
        val `value`: String = "",
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: String?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): String? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object BytesValueSerializer : KSerializer<ByteArray?> {
    @Serializable
    private data class Boxed(
        val `value`: ByteArray = ByteArray(0),
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: ByteArray?,
    ) {
        encoder.encodeNullableSerializableValue(Boxed.serializer(), value?.let { Boxed(it) })
    }

    override fun deserialize(decoder: Decoder): ByteArray? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.`value`
    }
}

object DurationSerializer : KSerializer<Duration?> {
    @Serializable
    private data class Boxed(
        val seconds: Long = 0L,
        val nanos: Int = 0,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Duration?,
    ) {
        encoder.encodeNullableSerializableValue(
            Boxed.serializer(),
            value?.toComponents { seconds, nanoseconds ->
                Boxed(
                    seconds = seconds,
                    nanos = nanoseconds,
                )
            },
        )
    }

    override fun deserialize(decoder: Decoder): Duration? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.let {
            it.seconds.seconds + it.nanos.nanoseconds
        }
    }
}

object TimestampSerializer : KSerializer<Instant?> {
    @Serializable
    private data class Boxed(
        val seconds: Long = 0L,
        val nanos: Int = 0,
    )

    override val descriptor: SerialDescriptor = Boxed.serializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: Instant?,
    ) {
        encoder.encodeNullableSerializableValue(
            Boxed.serializer(),
            value?.let {
                Boxed(
                    seconds = it.epochSeconds,
                    nanos = it.nanosecondsOfSecond,
                )
            },
        )
    }

    override fun deserialize(decoder: Decoder): Instant? {
        return decoder.decodeNullableSerializableValue(Boxed.serializer())?.let {
            Instant.fromEpochSeconds(it.seconds, it.nanos)
        }
    }
}
