package dogacel.kotlinx.protobuf.gen.proto3

import com.google.protobuf.*
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import testgen.wkt.proto3.MessageWithWellKnownTypes
import wkt.proto3.WellKnownType

class WellKnownTypesTest {
    private fun builder(): WellKnownType.MessageWithWellKnownTypes.Builder =
        WellKnownType.MessageWithWellKnownTypes.newBuilder()

    private fun WellKnownType.MessageWithWellKnownTypes.Builder.test(
        scope: (
            original: WellKnownType.MessageWithWellKnownTypes,
            result: MessageWithWellKnownTypes
        ) -> Unit
    ) {
        val message = this.build()
        val messageBytes = message.toByteArray()
        val result: MessageWithWellKnownTypes = ProtoBuf.decodeFromByteArray(messageBytes)

        scope(message, result)

        val deser = WellKnownType.MessageWithWellKnownTypes.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    fun boolValue() {
        builder().test { original, result ->
            assertEquals(null, result.optionalBoolWrapper)
            assertEquals(false, original.hasOptionalBoolWrapper())
        }

        builder().setOptionalBoolWrapper(BoolValue.of(true)).test { original, result ->
            assertEquals(true, result.optionalBoolWrapper)
            assertEquals(true, original.optionalBoolWrapper.value)
        }

        builder().setOptionalBoolWrapper(BoolValue.of(false)).test { original, result ->
            assertEquals(false, result.optionalBoolWrapper)
            assertEquals(false, original.optionalBoolWrapper.value)
        }
    }

    @Test
    fun intValue() {
        builder().test { original, result ->
            assertEquals(null, result.optionalInt32Wrapper)
            assertEquals(false, original.hasOptionalInt32Wrapper())
        }

        builder().setOptionalInt32Wrapper(Int32Value.of(1)).test { original, result ->
            assertEquals(1, result.optionalInt32Wrapper)
            assertEquals(1, original.optionalInt32Wrapper.value)
        }

        builder().setOptionalInt32Wrapper(Int32Value.of(0)).test { original, result ->
            assertEquals(0, result.optionalInt32Wrapper)
            assertEquals(0, original.optionalInt32Wrapper.value)
        }
    }

    @Test
    fun string() {
        builder().test { original, result ->
            assertEquals(null, result.optionalStringWrapper)
            assertEquals(false, original.hasOptionalStringWrapper())
        }

        builder().setOptionalStringWrapper(StringValue.of("123abc__")).test { original, result ->
            assertEquals("123abc__", result.optionalStringWrapper)
            assertEquals("123abc__", original.optionalStringWrapper.value)
        }

        builder().setOptionalStringWrapper(StringValue.of("")).test { original, result ->
            assertEquals("", result.optionalStringWrapper)
            assertEquals("", original.optionalStringWrapper.value)
        }
    }

    @Test
    fun duration() {
        builder().test { original, result ->
            assertEquals(null, result.optionalDuration)
            assertEquals(false, original.hasOptionalDuration())
        }

        builder().setOptionalDuration(Duration.newBuilder().setSeconds(1).setNanos(2).build())
            .test { original, result ->
                assertEquals(1.seconds + 2.nanoseconds, result.optionalDuration)
                assertEquals(Duration.newBuilder().setSeconds(1).setNanos(2).build(), original.optionalDuration)
            }
    }

    @Test
    fun timestamp() {
        builder().test { original, result ->
            assertEquals(null, result.optionalTimestamp)
            assertEquals(false, original.hasOptionalTimestamp())
        }

        builder().setOptionalTimestamp(Timestamp.newBuilder().setSeconds(1).setNanos(2).build())
            .test { original, result ->
                assertEquals(Instant.fromEpochSeconds(1, 2), result.optionalTimestamp)
                assertEquals(
                    Timestamp.newBuilder().setSeconds(1).setNanos(2).build(),
                    original.optionalTimestamp
                )
            }
    }
}
