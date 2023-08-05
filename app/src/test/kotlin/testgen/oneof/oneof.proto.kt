package testgen.oneof

import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class OneofMessage(
  @ProtoNumber(number = 1)
  public val oneofUint32: UInt = 0U,
  @ProtoNumber(number = 2)
  public val oneofNestedMessage: NestedMessage? = null,
  @ProtoNumber(number = 3)
  public val oneofString: String = "",
  @ProtoNumber(number = 4)
  public val oneofBytes: ByteArray = byteArrayOf(),
  @ProtoNumber(number = 5)
  public val oneofBool: Boolean = false,
  @ProtoNumber(number = 6)
  public val oneofUint64: ULong = 0UL,
  @ProtoNumber(number = 7)
  public val oneofFloat: Float = 0.0f,
  @ProtoNumber(number = 8)
  public val oneofDouble: Double = 0.0,
  @ProtoNumber(number = 9)
  public val oneofEnum: NestedEnum = testgen.oneof.OneofMessage.NestedEnum.FOO,
  @ProtoNumber(number = 10)
  public val left: String = "",
  @ProtoNumber(number = 11)
  public val right: String = "",
) {
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val a: Int = 0,
    @ProtoNumber(number = 2)
    public val corecursive: OneofMessage? = null,
  )

  @Serializable
  public enum class NestedEnum {
    @ProtoNumber(number = 0)
    FOO,
    @ProtoNumber(number = 1)
    BAR,
    @ProtoNumber(number = 2)
    BAZ,
    @ProtoNumber(number = -1)
    NEG,
  }
}
