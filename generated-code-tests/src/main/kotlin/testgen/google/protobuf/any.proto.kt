package testgen.google.protobuf

import kotlin.ByteArray
import kotlin.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class Any(
  @ProtoNumber(number = 1)
  public val typeUrl: String = "",
  @ProtoNumber(number = 2)
  public val `value`: ByteArray = byteArrayOf(),
)
