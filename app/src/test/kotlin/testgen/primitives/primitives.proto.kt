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
  public val optionalInt32: Int = 0,
  @ProtoNumber(number = 2)
  public val optionalInt64: Long = 0L,
  @ProtoNumber(number = 3)
  public val optionalUint32: UInt = 0U,
  @ProtoNumber(number = 4)
  public val optionalUint64: ULong = 0UL,
  @ProtoNumber(number = 5)
  public val optionalSint32: Int = 0,
  @ProtoNumber(number = 6)
  public val optionalSint64: Long = 0L,
  @ProtoNumber(number = 7)
  public val optionalFixed32: Int = 0,
  @ProtoNumber(number = 8)
  public val optionalFixed64: Long = 0L,
  @ProtoNumber(number = 9)
  public val optionalSfixed32: Int = 0,
  @ProtoNumber(number = 10)
  public val optionalSfixed64: Long = 0L,
  @ProtoNumber(number = 11)
  public val optionalFloat: Float = 0.0f,
  @ProtoNumber(number = 12)
  public val optionalDouble: Double = 0.0,
  @ProtoNumber(number = 13)
  public val optionalBool: Boolean = false,
  @ProtoNumber(number = 14)
  public val optionalString: String = "",
  @ProtoNumber(number = 15)
  public val optionalBytes: ByteArray = byteArrayOf(),
)
