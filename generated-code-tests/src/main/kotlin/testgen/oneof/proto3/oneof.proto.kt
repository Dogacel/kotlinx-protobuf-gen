package testgen.oneof.proto3

import kotlin.Boolean
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
  public val oneofUint32: UInt? = null,
  @ProtoNumber(number = 2)
  public val oneofNestedMessage: NestedMessage? = null,
  @ProtoNumber(number = 3)
  public val oneofString: String? = null,
  @ProtoNumber(number = 5)
  public val oneofBool: Boolean? = null,
  @ProtoNumber(number = 6)
  public val oneofUint64: ULong? = null,
  @ProtoNumber(number = 7)
  public val oneofFloat: Float? = null,
  @ProtoNumber(number = 8)
  public val oneofDouble: Double? = null,
  @ProtoNumber(number = 9)
  public val oneofEnum: NestedEnum? = null,
  @ProtoNumber(number = 10)
  public val left: String? = null,
  @ProtoNumber(number = 11)
  public val right: String? = null,
) {
  init {
    require(
      listOfNotNull(
        oneofUint32,
        oneofNestedMessage,
        oneofString,
        oneofBool,
        oneofUint64,
        oneofFloat,
        oneofDouble,
        oneofEnum,
      ).size <= 1
    ) { "Should only contain one of oneof_field." } 
    require(
      listOfNotNull(
        left,
        right,
      ).size <= 1
    ) { "Should only contain one of second_oneof_field." } 
  }
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
