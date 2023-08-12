package testgen.test.grpc

import kotlin.String
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

  public fun TestClientStream(TestUnaryRequest: TestUnaryRequest): TestUnaryResponse = TODO()

  public fun TestServerStream(TestUnaryRequest: TestUnaryRequest): TestUnaryResponse = TODO()

  public fun TestBidiStream(TestUnaryRequest: TestUnaryRequest): TestUnaryResponse = TODO()
}
