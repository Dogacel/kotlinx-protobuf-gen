package testgen.repeateds

import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlin.collections.List
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class RepeatedsMessage(
  @ProtoNumber(number = 1)
  public val repeated_int32: List<Int> = listOf(),
  @ProtoNumber(number = 2)
  public val repeated_int64: List<Long> = listOf(),
  @ProtoNumber(number = 3)
  public val repeated_uint32: List<UInt> = listOf(),
  @ProtoNumber(number = 4)
  public val repeated_uint64: List<ULong> = listOf(),
  @ProtoNumber(number = 5)
  public val repeated_sint32: List<Int> = listOf(),
  @ProtoNumber(number = 6)
  public val repeated_sint64: List<Long> = listOf(),
  @ProtoNumber(number = 7)
  public val repeated_fixed32: List<Int> = listOf(),
  @ProtoNumber(number = 8)
  public val repeated_fixed64: List<Long> = listOf(),
  @ProtoNumber(number = 9)
  public val repeated_sfixed32: List<Int> = listOf(),
  @ProtoNumber(number = 10)
  public val repeated_sfixed64: List<Long> = listOf(),
  @ProtoNumber(number = 11)
  public val repeated_float: List<Float> = listOf(),
  @ProtoNumber(number = 12)
  public val repeated_double: List<Double> = listOf(),
  @ProtoNumber(number = 13)
  public val repeated_bool: List<Boolean> = listOf(),
  @ProtoNumber(number = 14)
  public val repeated_string: List<String> = listOf(),
  @ProtoNumber(number = 15)
  public val repeated_bytes: List<ByteArray> = listOf(),
)
