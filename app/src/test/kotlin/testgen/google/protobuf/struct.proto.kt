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
  public val nullValue: NullValue? = null,
  @ProtoNumber(number = 2)
  public val numberValue: Double? = null,
  @ProtoNumber(number = 3)
  public val stringValue: String? = null,
  @ProtoNumber(number = 4)
  public val boolValue: Boolean? = null,
  @ProtoNumber(number = 5)
  public val structValue: Struct? = null,
  @ProtoNumber(number = 6)
  public val listValue: ListValue? = null,
) {
  init {
    require(
      listOfNotNull(
        nullValue,
        numberValue,
        stringValue,
        boolValue,
        structValue,
        listValue,
      ).size <= 1
    ) { "Should only contain one of kind." } 
  }
}

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
