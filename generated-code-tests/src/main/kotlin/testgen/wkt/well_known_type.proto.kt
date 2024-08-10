package testgen.wkt

import dogacel.kotlinx.protobuf.gen.wkt.BoolValueP
import dogacel.kotlinx.protobuf.gen.wkt.BytesValueP
import dogacel.kotlinx.protobuf.gen.wkt.DoubleValueP
import dogacel.kotlinx.protobuf.gen.wkt.DurationP
import dogacel.kotlinx.protobuf.gen.wkt.FloatValueP
import dogacel.kotlinx.protobuf.gen.wkt.Int32ValueP
import dogacel.kotlinx.protobuf.gen.wkt.Int64ValueP
import dogacel.kotlinx.protobuf.gen.wkt.StringValueP
import dogacel.kotlinx.protobuf.gen.wkt.TimestampP
import dogacel.kotlinx.protobuf.gen.wkt.UInt32ValueP
import dogacel.kotlinx.protobuf.gen.wkt.UInt64ValueP
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
  public val optionalBoolWrapper: BoolValueP? = null,
  @ProtoNumber(number = 2)
  public val optionalInt32Wrapper: Int32ValueP? = null,
  @ProtoNumber(number = 3)
  public val optionalInt64Wrapper: Int64ValueP? = null,
  @ProtoNumber(number = 4)
  public val optionalUint32Wrapper: UInt32ValueP? = null,
  @ProtoNumber(number = 5)
  public val optionalUint64Wrapper: UInt64ValueP? = null,
  @ProtoNumber(number = 6)
  public val optionalFloatWrapper: FloatValueP? = null,
  @ProtoNumber(number = 7)
  public val optionalDoubleWrapper: DoubleValueP? = null,
  @ProtoNumber(number = 8)
  public val optionalStringWrapper: StringValueP? = null,
  @ProtoNumber(number = 9)
  public val optionalBytesWrapper: BytesValueP? = null,
  @ProtoNumber(number = 10)
  public val optionalDuration: DurationP? = null,
  @ProtoNumber(number = 11)
  public val optionalTimestamp: TimestampP? = null,
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
