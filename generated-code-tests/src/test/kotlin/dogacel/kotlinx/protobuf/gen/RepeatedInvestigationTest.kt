package dogacel.kotlinx.protobuf.gen

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlinx.serialization.protobuf.ProtoNumber
import kotlin.test.Test

class RepeatedInvestigationTest {
    @Test
    fun shouldDeSerAll() {
        val message = repeateds.Repeateds.RepeatedsMessage.newBuilder()
            .addAllRepeatedInt32(listOf(134, -231, 31))
            .addAllRepeatedInt64(listOf(2384L, 214L, -12933L))
            .addAllRepeatedUint32(listOf(1234, 9192, 317))
            .addAllRepeatedUint64(listOf(4921L, 22900L, 34589L))
            .addAllRepeatedSint32(listOf(2391, -90413, 12903))
            .addAllRepeatedSint64(listOf(41L, 93492892839938989L, 9L))
            .addAllRepeatedFixed32(listOf(93, -1293, 1923291))
            .addAllRepeatedFixed64(listOf(1293L, -21398210943284L, 193L))
            .addAllRepeatedSfixed32(listOf(1293, -1293, 1923291))
            .addAllRepeatedSfixed64(listOf(1293L, 193L))
            .build()

        val messageBytes = message.toByteArray()

        val deser: RepeatedWrapper = ProtoBuf.decodeFromByteArray(messageBytes)

        println(deser)
    }
}

@Serializable
data class RepeatedWrapper(
    @ProtoNumber(1)
    val repeatedInt32: List<Int>,
    @ProtoNumber(2)
    val repeatedInt64: List<Long>,
    @ProtoNumber(3)
    val repeatedUint32: List<Int>,
    @ProtoNumber(4)
    val repeatedUint64: List<Long>,
    @ProtoNumber(5)
    val repeatedSint32: List<Int>,
    @ProtoNumber(6)
    val repeatedSint64: List<Long>
)
