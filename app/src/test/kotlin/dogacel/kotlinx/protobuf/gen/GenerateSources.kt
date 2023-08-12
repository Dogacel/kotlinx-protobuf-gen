package dogacel.kotlinx.protobuf.gen

import com.google.protobuf_test_messages.proto3.TestMessagesProto3
import test.grpc.TestGrpc
import kotlin.io.path.Path

fun main() {
    val codeGeneratorOptions = CodeGeneratorOptions(
        packagePrefix = "testgen"
    )
    val codeGenerator = CodeGenerator(
        demo.Demo.getDescriptor(),
        primitives.Primitives.getDescriptor(),
        enums.Enums.getDescriptor(),
        repeateds.Repeateds.getDescriptor(),
        maps.Maps.getDescriptor(),
        messages.Messages.getDescriptor(),
        oneof.Oneof.getDescriptor(),
        TestMessagesProto3.getDescriptor(),
        TestGrpc.getDescriptor(),
        wkt.WellKnownType.getDescriptor(),
        options = codeGeneratorOptions
    )

    codeGenerator.generateFiles(Path("./app/src/test/kotlin"))
}
