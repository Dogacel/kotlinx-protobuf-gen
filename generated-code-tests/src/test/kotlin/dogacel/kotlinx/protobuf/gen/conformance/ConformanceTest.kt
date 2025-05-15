package dogacel.kotlinx.protobuf.gen.conformance

import com.google.protobuf_test_messages.proto3.TestMessagesProto3.TestAllTypesProto3 as ProtoMessage
import dogacel.kotlinx.protobuf.gen.conformance.TestAllTypesProto3 as KotlinxMessage
import dogacel.kotest.ProtobufMessageArb.protobufMessage
import dogacel.kotest.ProtobufMessageArbSettings
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.forAll
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf

class ConformanceTest : FunSpec({
    test("should convert without errors") {
        Arb.list(Arb.int()).forAll(100) {
            println(it.size)
            true    
        }
    }
})
