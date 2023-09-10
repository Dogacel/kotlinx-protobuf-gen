import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService
import io.grpc.BindableService
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import sample.grpc.*

class SampleServiceImpl : SampleService() {
    override suspend fun sayHello(sayHelloRequest: SayHelloRequest): SayHelloResponse {
        return SayHelloResponse(
            message = "Hello, ${sayHelloRequest.name}!"
        )
    }

    override suspend fun add(addRequest: AddRequest): AddResponse {
        return AddResponse(
            result = addRequest.a + addRequest.b
        )
    }
}

fun SampleService.asBindable(): BindableService {
    return object : SampleServiceGrpcKt.SampleServiceCoroutineImplBase() {
        override suspend fun add(request: SampleServiceOuterClass.AddRequest): SampleServiceOuterClass.AddResponse {
            val requestKt = ProtoBuf.decodeFromByteArray<AddRequest>(request.toByteArray())
            val responseBinary = ProtoBuf.encodeToByteArray(this@asBindable.add(requestKt))
            return SampleServiceOuterClass.AddResponse.parseFrom(responseBinary)
        }

        override suspend fun sayHello(request: SampleServiceOuterClass.SayHelloRequest): SampleServiceOuterClass.SayHelloResponse {
            val requestKt = ProtoBuf.decodeFromByteArray<SayHelloRequest>(request.toByteArray())
            val responseBinary = ProtoBuf.encodeToByteArray(this@asBindable.sayHello(requestKt))
            return SampleServiceOuterClass.SayHelloResponse.parseFrom(responseBinary)
        }
    }
}

fun main() {
    val grpcService = GrpcService.builder()
        .addService(SampleServiceImpl().asBindable())
        .enableUnframedRequests(true)
        .build()

    val server = Server.builder()
        .http(9000)
        .service(grpcService)
        .serviceUnder("/docs", DocService())
        .build()

    server.start().join()
}
