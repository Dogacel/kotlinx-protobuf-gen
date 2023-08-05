package dogacel.kotlinx.protobuf.gen

import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.junit.jupiter.api.Assertions.assertEquals
import testgen.oneof.OneofMessage
import kotlin.test.Ignore
import kotlin.test.Test

class OneofTest {
    @Test
    @Ignore("Oneof is not supported yet")
    fun shouldDeSerAll() {
        val message = oneof.Oneof.OneofMessage.newBuilder().build()

        val result: OneofMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        val deser = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }

    @Test
    @Ignore("Oneof is not supported yet")
    fun shouldDeSerEmpty() {
        val message = oneof.Oneof.OneofMessage.newBuilder().build()

        val result: OneofMessage = ProtoBuf.decodeFromByteArray(message.toByteArray())

        val deser = oneof.Oneof.OneofMessage.parseFrom(ProtoBuf.encodeToByteArray(result))
        assertEquals(message, deser)
    }
}
