package testgen.google.protobuf

import kotlin.Int
import kotlin.Long
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class Timestamp(
  @ProtoNumber(number = 1)
  public val seconds: Long = 0L,
  @ProtoNumber(number = 2)
  public val nanos: Int = 0,
)
