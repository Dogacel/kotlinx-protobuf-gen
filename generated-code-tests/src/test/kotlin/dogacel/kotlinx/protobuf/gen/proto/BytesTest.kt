package dogacel.kotlinx.protobuf.gen.proto

import com.google.protobuf.ByteString
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.*
import test.MessagesUnderTest
import kotlin.test.Test

@OptIn(ExperimentalStdlibApi::class)
class BytesTest {
    @Test
    fun shouldDeSerAll() {
        val bytes = ByteString.fromHex("0a0b0c0d0e0f19239a9100000000faab912b1c18")
        val protoMessage = MessagesUnderTest.BytesUnderTest.newBuilder()
            .setBytes(bytes)
            .build()

        val generatedClass = ProtoBuf.decodeFromByteArray<ByteWrapper>(protoMessage.toByteArray())

        println(bytes.toByteArray().toHexString())
        println(generatedClass.data.toByteArray().toHexString())
    }
}

@kotlinx.serialization.Serializable
data class ByteWrapper(
    @ProtoNumber(1)
    @ProtoPacked
    @ProtoType(ProtoIntegerType.DEFAULT)
    val data: List<Byte> = emptyList()
)

@Serializable
data class ComparableByteArray(
    val data: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ComparableByteArray

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}
