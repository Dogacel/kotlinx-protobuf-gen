package testgen.google.protobuf

import kotlin.Boolean
import kotlin.Double
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class Struct(
  @ProtoNumber(number = 1)
  public val fields: Map<String, Value?> = emptyMap(),
)

@Serializable
public data class Value(
  @ProtoNumber(number = 1)
  public val nullValue: NullValue = testgen.google.protobuf.NullValue.NULL_VALUE,
  @ProtoNumber(number = 2)
  public val numberValue: Double = 0.0,
  @ProtoNumber(number = 3)
  public val stringValue: String = "",
  @ProtoNumber(number = 4)
  public val boolValue: Boolean = false,
  @ProtoNumber(number = 5)
  public val structValue: Struct? = null,
  @ProtoNumber(number = 6)
  public val listValue: ListValue? = null,
)

@Serializable
public data class ListValue(
  @ProtoNumber(number = 1)
  public val values: List<Value?> = emptyList(),
)

@Serializable
public enum class NullValue {
  @ProtoNumber(number = 0)
  NULL_VALUE,
}
