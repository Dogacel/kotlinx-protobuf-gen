package generated.protobuf_test_messages.proto3

import google.protobuf.Any
import google.protobuf.BoolValue
import google.protobuf.BytesValue
import google.protobuf.DoubleValue
import google.protobuf.Duration
import google.protobuf.FieldMask
import google.protobuf.FloatValue
import google.protobuf.Int32Value
import google.protobuf.Int64Value
import google.protobuf.ListValue
import google.protobuf.StringValue
import google.protobuf.Struct
import google.protobuf.Timestamp
import google.protobuf.UInt32Value
import google.protobuf.UInt64Value
import google.protobuf.Value
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Enum
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import protobuf_test_messages.proto3.ForeignMessage

@Serializable
public data class TestAllTypesProto3(
  @ProtoNumber(number = 1)
  public val optional_int32: Int,
  @ProtoNumber(number = 2)
  public val optional_int64: Long,
  @ProtoNumber(number = 3)
  public val optional_uint32: UInt,
  @ProtoNumber(number = 4)
  public val optional_uint64: ULong,
  @ProtoNumber(number = 5)
  public val optional_sint32: Int,
  @ProtoNumber(number = 6)
  public val optional_sint64: Long,
  @ProtoNumber(number = 7)
  public val optional_fixed32: Int,
  @ProtoNumber(number = 8)
  public val optional_fixed64: Long,
  @ProtoNumber(number = 9)
  public val optional_sfixed32: Int,
  @ProtoNumber(number = 10)
  public val optional_sfixed64: Long,
  @ProtoNumber(number = 11)
  public val optional_float: Float,
  @ProtoNumber(number = 12)
  public val optional_double: Double,
  @ProtoNumber(number = 13)
  public val optional_bool: Boolean,
  @ProtoNumber(number = 14)
  public val optional_string: String,
  @ProtoNumber(number = 15)
  public val optional_bytes: ByteArray,
  @ProtoNumber(number = 18)
  public val optional_nested_message:
      protobuf_test_messages.proto3.TestAllTypesProto3.NestedMessage,
  @ProtoNumber(number = 19)
  public val optional_foreign_message: ForeignMessage,
  @ProtoNumber(number = 21)
  public val optional_nested_enum: Enum,
  @ProtoNumber(number = 22)
  public val optional_foreign_enum: Enum,
  @ProtoNumber(number = 23)
  public val optional_aliased_enum: Enum,
  @ProtoNumber(number = 24)
  public val optional_string_piece: String,
  @ProtoNumber(number = 25)
  public val optional_cord: String,
  @ProtoNumber(number = 27)
  public val recursive_message: protobuf_test_messages.proto3.TestAllTypesProto3,
  @ProtoNumber(number = 31)
  public val repeated_int32: Int,
  @ProtoNumber(number = 32)
  public val repeated_int64: Long,
  @ProtoNumber(number = 33)
  public val repeated_uint32: UInt,
  @ProtoNumber(number = 34)
  public val repeated_uint64: ULong,
  @ProtoNumber(number = 35)
  public val repeated_sint32: Int,
  @ProtoNumber(number = 36)
  public val repeated_sint64: Long,
  @ProtoNumber(number = 37)
  public val repeated_fixed32: Int,
  @ProtoNumber(number = 38)
  public val repeated_fixed64: Long,
  @ProtoNumber(number = 39)
  public val repeated_sfixed32: Int,
  @ProtoNumber(number = 40)
  public val repeated_sfixed64: Long,
  @ProtoNumber(number = 41)
  public val repeated_float: Float,
  @ProtoNumber(number = 42)
  public val repeated_double: Double,
  @ProtoNumber(number = 43)
  public val repeated_bool: Boolean,
  @ProtoNumber(number = 44)
  public val repeated_string: String,
  @ProtoNumber(number = 45)
  public val repeated_bytes: ByteArray,
  @ProtoNumber(number = 48)
  public val repeated_nested_message:
      protobuf_test_messages.proto3.TestAllTypesProto3.NestedMessage,
  @ProtoNumber(number = 49)
  public val repeated_foreign_message: ForeignMessage,
  @ProtoNumber(number = 51)
  public val repeated_nested_enum: Enum,
  @ProtoNumber(number = 52)
  public val repeated_foreign_enum: Enum,
  @ProtoNumber(number = 54)
  public val repeated_string_piece: String,
  @ProtoNumber(number = 55)
  public val repeated_cord: String,
  @ProtoNumber(number = 75)
  public val packed_int32: Int,
  @ProtoNumber(number = 76)
  public val packed_int64: Long,
  @ProtoNumber(number = 77)
  public val packed_uint32: UInt,
  @ProtoNumber(number = 78)
  public val packed_uint64: ULong,
  @ProtoNumber(number = 79)
  public val packed_sint32: Int,
  @ProtoNumber(number = 80)
  public val packed_sint64: Long,
  @ProtoNumber(number = 81)
  public val packed_fixed32: Int,
  @ProtoNumber(number = 82)
  public val packed_fixed64: Long,
  @ProtoNumber(number = 83)
  public val packed_sfixed32: Int,
  @ProtoNumber(number = 84)
  public val packed_sfixed64: Long,
  @ProtoNumber(number = 85)
  public val packed_float: Float,
  @ProtoNumber(number = 86)
  public val packed_double: Double,
  @ProtoNumber(number = 87)
  public val packed_bool: Boolean,
  @ProtoNumber(number = 88)
  public val packed_nested_enum: Enum,
  @ProtoNumber(number = 89)
  public val unpacked_int32: Int,
  @ProtoNumber(number = 90)
  public val unpacked_int64: Long,
  @ProtoNumber(number = 91)
  public val unpacked_uint32: UInt,
  @ProtoNumber(number = 92)
  public val unpacked_uint64: ULong,
  @ProtoNumber(number = 93)
  public val unpacked_sint32: Int,
  @ProtoNumber(number = 94)
  public val unpacked_sint64: Long,
  @ProtoNumber(number = 95)
  public val unpacked_fixed32: Int,
  @ProtoNumber(number = 96)
  public val unpacked_fixed64: Long,
  @ProtoNumber(number = 97)
  public val unpacked_sfixed32: Int,
  @ProtoNumber(number = 98)
  public val unpacked_sfixed64: Long,
  @ProtoNumber(number = 99)
  public val unpacked_float: Float,
  @ProtoNumber(number = 100)
  public val unpacked_double: Double,
  @ProtoNumber(number = 101)
  public val unpacked_bool: Boolean,
  @ProtoNumber(number = 102)
  public val unpacked_nested_enum: Enum,
  @ProtoNumber(number = 56)
  public val map_int32_int32: protobuf_test_messages.proto3.TestAllTypesProto3.MapInt32Int32Entry,
  @ProtoNumber(number = 57)
  public val map_int64_int64: protobuf_test_messages.proto3.TestAllTypesProto3.MapInt64Int64Entry,
  @ProtoNumber(number = 58)
  public val map_uint32_uint32:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapUint32Uint32Entry,
  @ProtoNumber(number = 59)
  public val map_uint64_uint64:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapUint64Uint64Entry,
  @ProtoNumber(number = 60)
  public val map_sint32_sint32:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapSint32Sint32Entry,
  @ProtoNumber(number = 61)
  public val map_sint64_sint64:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapSint64Sint64Entry,
  @ProtoNumber(number = 62)
  public val map_fixed32_fixed32:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapFixed32Fixed32Entry,
  @ProtoNumber(number = 63)
  public val map_fixed64_fixed64:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapFixed64Fixed64Entry,
  @ProtoNumber(number = 64)
  public val map_sfixed32_sfixed32:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapSfixed32Sfixed32Entry,
  @ProtoNumber(number = 65)
  public val map_sfixed64_sfixed64:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapSfixed64Sfixed64Entry,
  @ProtoNumber(number = 66)
  public val map_int32_float: protobuf_test_messages.proto3.TestAllTypesProto3.MapInt32FloatEntry,
  @ProtoNumber(number = 67)
  public val map_int32_double: protobuf_test_messages.proto3.TestAllTypesProto3.MapInt32DoubleEntry,
  @ProtoNumber(number = 68)
  public val map_bool_bool: protobuf_test_messages.proto3.TestAllTypesProto3.MapBoolBoolEntry,
  @ProtoNumber(number = 69)
  public val map_string_string:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapStringStringEntry,
  @ProtoNumber(number = 70)
  public val map_string_bytes: protobuf_test_messages.proto3.TestAllTypesProto3.MapStringBytesEntry,
  @ProtoNumber(number = 71)
  public val map_string_nested_message:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapStringNestedMessageEntry,
  @ProtoNumber(number = 72)
  public val map_string_foreign_message:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapStringForeignMessageEntry,
  @ProtoNumber(number = 73)
  public val map_string_nested_enum:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapStringNestedEnumEntry,
  @ProtoNumber(number = 74)
  public val map_string_foreign_enum:
      protobuf_test_messages.proto3.TestAllTypesProto3.MapStringForeignEnumEntry,
  @ProtoNumber(number = 111)
  public val oneof_uint32: UInt,
  @ProtoNumber(number = 112)
  public val oneof_nested_message: protobuf_test_messages.proto3.TestAllTypesProto3.NestedMessage,
  @ProtoNumber(number = 113)
  public val oneof_string: String,
  @ProtoNumber(number = 114)
  public val oneof_bytes: ByteArray,
  @ProtoNumber(number = 115)
  public val oneof_bool: Boolean,
  @ProtoNumber(number = 116)
  public val oneof_uint64: ULong,
  @ProtoNumber(number = 117)
  public val oneof_float: Float,
  @ProtoNumber(number = 118)
  public val oneof_double: Double,
  @ProtoNumber(number = 119)
  public val oneof_enum: Enum,
  @ProtoNumber(number = 201)
  public val optional_bool_wrapper: BoolValue,
  @ProtoNumber(number = 202)
  public val optional_int32_wrapper: Int32Value,
  @ProtoNumber(number = 203)
  public val optional_int64_wrapper: Int64Value,
  @ProtoNumber(number = 204)
  public val optional_uint32_wrapper: UInt32Value,
  @ProtoNumber(number = 205)
  public val optional_uint64_wrapper: UInt64Value,
  @ProtoNumber(number = 206)
  public val optional_float_wrapper: FloatValue,
  @ProtoNumber(number = 207)
  public val optional_double_wrapper: DoubleValue,
  @ProtoNumber(number = 208)
  public val optional_string_wrapper: StringValue,
  @ProtoNumber(number = 209)
  public val optional_bytes_wrapper: BytesValue,
  @ProtoNumber(number = 211)
  public val repeated_bool_wrapper: BoolValue,
  @ProtoNumber(number = 212)
  public val repeated_int32_wrapper: Int32Value,
  @ProtoNumber(number = 213)
  public val repeated_int64_wrapper: Int64Value,
  @ProtoNumber(number = 214)
  public val repeated_uint32_wrapper: UInt32Value,
  @ProtoNumber(number = 215)
  public val repeated_uint64_wrapper: UInt64Value,
  @ProtoNumber(number = 216)
  public val repeated_float_wrapper: FloatValue,
  @ProtoNumber(number = 217)
  public val repeated_double_wrapper: DoubleValue,
  @ProtoNumber(number = 218)
  public val repeated_string_wrapper: StringValue,
  @ProtoNumber(number = 219)
  public val repeated_bytes_wrapper: BytesValue,
  @ProtoNumber(number = 301)
  public val optional_duration: Duration,
  @ProtoNumber(number = 302)
  public val optional_timestamp: Timestamp,
  @ProtoNumber(number = 303)
  public val optional_field_mask: FieldMask,
  @ProtoNumber(number = 304)
  public val optional_struct: Struct,
  @ProtoNumber(number = 305)
  public val optional_any: Any,
  @ProtoNumber(number = 306)
  public val optional_value: Value,
  @ProtoNumber(number = 311)
  public val repeated_duration: Duration,
  @ProtoNumber(number = 312)
  public val repeated_timestamp: Timestamp,
  @ProtoNumber(number = 313)
  public val repeated_fieldmask: FieldMask,
  @ProtoNumber(number = 324)
  public val repeated_struct: Struct,
  @ProtoNumber(number = 315)
  public val repeated_any: Any,
  @ProtoNumber(number = 316)
  public val repeated_value: Value,
  @ProtoNumber(number = 317)
  public val repeated_list_value: ListValue,
  @ProtoNumber(number = 401)
  public val fieldname1: Int,
  @ProtoNumber(number = 402)
  public val field_name2: Int,
  @ProtoNumber(number = 403)
  public val _field_name3: Int,
  @ProtoNumber(number = 404)
  public val field__name4_: Int,
  @ProtoNumber(number = 405)
  public val field0name5: Int,
  @ProtoNumber(number = 406)
  public val field_0_name6: Int,
  @ProtoNumber(number = 407)
  public val fieldName7: Int,
  @ProtoNumber(number = 408)
  public val FieldName8: Int,
  @ProtoNumber(number = 409)
  public val field_Name9: Int,
  @ProtoNumber(number = 410)
  public val Field_Name10: Int,
  @ProtoNumber(number = 411)
  public val FIELD_NAME11: Int,
  @ProtoNumber(number = 412)
  public val FIELD_name12: Int,
  @ProtoNumber(number = 413)
  public val __field_name13: Int,
  @ProtoNumber(number = 414)
  public val __Field_name14: Int,
  @ProtoNumber(number = 415)
  public val field__name15: Int,
  @ProtoNumber(number = 416)
  public val field__Name16: Int,
  @ProtoNumber(number = 417)
  public val field_name17__: Int,
  @ProtoNumber(number = 418)
  public val Field_name18__: Int,
)

@Serializable
public data class ForeignMessage(
  @ProtoNumber(number = 1)
  public val c: Int,
)
