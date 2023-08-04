package generated.test

import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class TestMessage(
  @ProtoNumber(number = 1)
  public val id: Int,
  @ProtoNumber(number = 2)
  public val name: String,
)

@Serializable
public data class TestMessageInMessage(
  @ProtoNumber(number = 1)
  public val id: Int,
  @ProtoNumber(number = 2)
  public val message: Any,
)

@Serializable
public data class TestWrapper(
  @ProtoNumber(number = 1)
  public val id: Int,
  @ProtoNumber(number = 2)
  public val message: Any,
)
