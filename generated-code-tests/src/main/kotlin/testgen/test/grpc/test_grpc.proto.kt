package testgen.test.grpc

import kotlin.String
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class TestUnaryRequest(
  @ProtoNumber(number = 1)
  public val name: String = "",
)

@Serializable
public data class TestUnaryResponse(
  @ProtoNumber(number = 1)
  public val message: String = "",
)

public abstract class TestService {
  public fun TestUnary(TestUnaryRequest: TestUnaryRequest): TestUnaryResponse = TODO()

  public fun TestClientStream(TestUnaryRequest: Flow<TestUnaryRequest>): TestUnaryResponse = TODO()

  public fun TestServerStream(TestUnaryRequest: TestUnaryRequest): Flow<TestUnaryResponse> = TODO()

  public fun TestBidiStream(TestUnaryRequest: Flow<TestUnaryRequest>): Flow<TestUnaryResponse> =
      TODO()
}
