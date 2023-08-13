package testgen.wkt

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import testgen.google.protobuf.Any
import testgen.google.protobuf.BoolValue
import testgen.google.protobuf.BytesValue
import testgen.google.protobuf.DoubleValue
import testgen.google.protobuf.Duration
import testgen.google.protobuf.FieldMask
import testgen.google.protobuf.FloatValue
import testgen.google.protobuf.Int32Value
import testgen.google.protobuf.Int64Value
import testgen.google.protobuf.ListValue
import testgen.google.protobuf.StringValue
import testgen.google.protobuf.Struct
import testgen.google.protobuf.Timestamp
import testgen.google.protobuf.UInt32Value
import testgen.google.protobuf.UInt64Value
import testgen.google.protobuf.Value

@Serializable
public data class MessageWithWellKnownTypes(
  @ProtoNumber(number = 1)
  public val optionalBoolWrapper: BoolValue? = null,
  @ProtoNumber(number = 2)
  public val optionalInt32Wrapper: Int32Value? = null,
  @ProtoNumber(number = 3)
  public val optionalInt64Wrapper: Int64Value? = null,
  @ProtoNumber(number = 4)
  public val optionalUint32Wrapper: UInt32Value? = null,
  @ProtoNumber(number = 5)
  public val optionalUint64Wrapper: UInt64Value? = null,
  @ProtoNumber(number = 6)
  public val optionalFloatWrapper: FloatValue? = null,
  @ProtoNumber(number = 7)
  public val optionalDoubleWrapper: DoubleValue? = null,
  @ProtoNumber(number = 8)
  public val optionalStringWrapper: StringValue? = null,
  @ProtoNumber(number = 9)
  public val optionalBytesWrapper: BytesValue? = null,
  @ProtoNumber(number = 10)
  public val optionalDuration: Duration? = null,
  @ProtoNumber(number = 11)
  public val optionalTimestamp: Timestamp? = null,
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
