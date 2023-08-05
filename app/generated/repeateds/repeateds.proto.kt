package repeateds

import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class RepeatedsMessage(
  @ProtoNumber(number = 1)
  public val repeated_int32: Int,
  @ProtoNumber(number = 2)
  public val repeated_int64: Long,
  @ProtoNumber(number = 3)
  public val repeated_uint32: UInt,
  @ProtoNumber(number = 4)
  public val repeated_uint64: ULong,
  @ProtoNumber(number = 5)
  public val repeated_sint32: Int,
  @ProtoNumber(number = 6)
  public val repeated_sint64: Long,
  @ProtoNumber(number = 7)
  public val repeated_fixed32: Int,
  @ProtoNumber(number = 8)
  public val repeated_fixed64: Long,
  @ProtoNumber(number = 9)
  public val repeated_sfixed32: Int,
  @ProtoNumber(number = 10)
  public val repeated_sfixed64: Long,
  @ProtoNumber(number = 11)
  public val repeated_float: Float,
  @ProtoNumber(number = 12)
  public val repeated_double: Double,
  @ProtoNumber(number = 13)
  public val repeated_bool: Boolean,
  @ProtoNumber(number = 14)
  public val repeated_string: String,
  @ProtoNumber(number = 15)
  public val repeated_bytes: ByteArray,
)
