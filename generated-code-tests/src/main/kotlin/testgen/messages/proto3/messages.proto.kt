package testgen.messages.proto3

import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class MessagesMessage(
  @ProtoNumber(number = 1)
  public val id: String = "",
  @ProtoNumber(number = 2)
  public val optionalNestedMessage: NestedMessage? = null,
  @ProtoNumber(number = 3)
  public val optionalForeignMessage: ForeignMessage? = null,
) {
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val a: Int = 0,
    @ProtoNumber(number = 2)
    public val corecursive: MessagesMessage? = null,
  )
}

@Serializable
public data class ForeignMessage(
  @ProtoNumber(number = 1)
  public val c: Int = 0,
)
