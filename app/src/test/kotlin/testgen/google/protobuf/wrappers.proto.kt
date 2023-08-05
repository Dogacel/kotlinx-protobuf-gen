package testgen.google.protobuf

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
public data class DoubleValue(
  @ProtoNumber(number = 1)
  public val `value`: Double = 0.0,
)

@Serializable
public data class FloatValue(
  @ProtoNumber(number = 1)
  public val `value`: Float = 0.0f,
)

@Serializable
public data class Int64Value(
  @ProtoNumber(number = 1)
  public val `value`: Long = 0L,
)

@Serializable
public data class UInt64Value(
  @ProtoNumber(number = 1)
  public val `value`: ULong = 0UL,
)

@Serializable
public data class Int32Value(
  @ProtoNumber(number = 1)
  public val `value`: Int = 0,
)

@Serializable
public data class UInt32Value(
  @ProtoNumber(number = 1)
  public val `value`: UInt = 0U,
)

@Serializable
public data class BoolValue(
  @ProtoNumber(number = 1)
  public val `value`: Boolean = false,
)

@Serializable
public data class StringValue(
  @ProtoNumber(number = 1)
  public val `value`: String = "",
)

@Serializable
public data class BytesValue(
  @ProtoNumber(number = 1)
  public val `value`: ByteArray = byteArrayOf(),
)
