package testgen.primitives

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
public data class PrimitivesMessage(
  @ProtoNumber(number = 1)
  public val optional_int32: Int = 0,
  @ProtoNumber(number = 2)
  public val optional_int64: Long = 0L,
  @ProtoNumber(number = 3)
  public val optional_uint32: UInt = 0U,
  @ProtoNumber(number = 4)
  public val optional_uint64: ULong = 0UL,
  @ProtoNumber(number = 5)
  public val optional_sint32: Int = 0,
  @ProtoNumber(number = 6)
  public val optional_sint64: Long = 0L,
  @ProtoNumber(number = 7)
  public val optional_fixed32: Int = 0,
  @ProtoNumber(number = 8)
  public val optional_fixed64: Long = 0L,
  @ProtoNumber(number = 9)
  public val optional_sfixed32: Int = 0,
  @ProtoNumber(number = 10)
  public val optional_sfixed64: Long = 0L,
  @ProtoNumber(number = 11)
  public val optional_float: Float = 0.0f,
  @ProtoNumber(number = 12)
  public val optional_double: Double = 0.0,
  @ProtoNumber(number = 13)
  public val optional_bool: Boolean = false,
  @ProtoNumber(number = 14)
  public val optional_string: String = "",
  @ProtoNumber(number = 15)
  public val optional_bytes: ByteArray = byteArrayOf(),
)
