package testgen.wkt

import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlin.time.Duration
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import testgen.google.protobuf.Any
import testgen.google.protobuf.FieldMask
import testgen.google.protobuf.ListValue
import testgen.google.protobuf.Struct
import testgen.google.protobuf.Value

@Serializable
public data class MessageWithWellKnownTypes(
  @ProtoNumber(number = 1)
  public val optionalBoolWrapper: Boolean? = null,
  @ProtoNumber(number = 2)
  public val optionalInt32Wrapper: Int? = null,
  @ProtoNumber(number = 3)
  public val optionalInt64Wrapper: Long? = null,
  @ProtoNumber(number = 4)
  public val optionalUint32Wrapper: UInt? = null,
  @ProtoNumber(number = 5)
  public val optionalUint64Wrapper: ULong? = null,
  @ProtoNumber(number = 6)
  public val optionalFloatWrapper: Float? = null,
  @ProtoNumber(number = 7)
  public val optionalDoubleWrapper: Double? = null,
  @ProtoNumber(number = 8)
  public val optionalStringWrapper: String? = null,
  @ProtoNumber(number = 9)
  public val optionalBytesWrapper: ByteArray? = null,
  @ProtoNumber(number = 10)
  public val optionalDuration: Duration? = null,
  @ProtoNumber(number = 11)
  public val optionalTimestamp: Instant? = null,
  @ProtoNumber(number = 12)
  public val optionalFieldMask: FieldMask? = null,
  @ProtoNumber(number = 13)
  public val optionalStruct: Struct? = null,
  @ProtoNumber(number = 14)
  public val optionalAny: Any? = null,
  @ProtoNumber(number = 15)
  public val optionalValue: Value? = null,
  @ProtoNumber(number = 16)
  public val repeatedListValue: ListValue? = null,
)
