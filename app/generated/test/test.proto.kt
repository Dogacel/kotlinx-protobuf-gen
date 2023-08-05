package test

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
