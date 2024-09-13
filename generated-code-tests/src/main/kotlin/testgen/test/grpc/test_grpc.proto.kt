package testgen.test.grpc

import kotlin.String
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class TestUnaryRequest(
  @ProtoNumber(number = 1)
  public val name: String? = "",
)

@Serializable
public data class TestUnaryResponse(
  @ProtoNumber(number = 1)
  public val message: String? = "",
)

public abstract class TestService {
  public open suspend fun testUnary(testUnaryRequest: TestUnaryRequest): TestUnaryResponse = TODO()

  public open suspend fun testClientStream(testUnaryRequest: Flow<TestUnaryRequest>):
      TestUnaryResponse = TODO()

  public open suspend fun testServerStream(testUnaryRequest: TestUnaryRequest):
      Flow<TestUnaryResponse> = TODO()

  public open suspend fun testBidiStream(testUnaryRequest: Flow<TestUnaryRequest>):
      Flow<TestUnaryResponse> = TODO()
}
