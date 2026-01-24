package testgen.google.protobuf

import kotlin.Boolean
import kotlin.Double
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

/**
 * `Struct` represents a structured data value, consisting of fields
 * which map to dynamically typed values. In some languages, `Struct`
 * might be supported by a native representation. For example, in
 * scripting languages like JS a struct is represented as an
 * object. The details of that representation are described together
 * with the proto support for the language.
 *
 * The JSON representation for `Struct` is JSON object.
 *
 * @param fields Unordered map of dynamically typed values.
 */
@Serializable
public data class Struct(
  @ProtoNumber(number = 1)
  public val fields: Map<String, Value?> = emptyMap(),
)

/**
 * `Value` represents a dynamically typed value which can be either
 * null, a number, a string, a boolean, a recursive struct value, or a
 * list of values. A producer of value is expected to set one of these
 * variants. Absence of any variant indicates an error.
 *
 * The JSON representation for `Value` is JSON value.
 *
 * @param nullValue Represents a null value.
 * @param numberValue Represents a double value.
 * @param stringValue Represents a string value.
 * @param boolValue Represents a boolean value.
 * @param structValue Represents a structured value.
 * @param listValue Represents a repeated `Value`.
 */
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

/**
 * `ListValue` is a wrapper around a repeated field of values.
 *
 * The JSON representation for `ListValue` is JSON array.
 *
 * @param values Repeated field of dynamically typed values.
 */
@Serializable
public data class ListValue(
  @ProtoNumber(number = 1)
  public val values: List<Value?> = emptyList(),
)

/**
 * `NullValue` is a singleton enumeration to represent the null value for the
 * `Value` type union.
 *
 * The JSON representation for `NullValue` is JSON `null`.
 */
@Serializable
public enum class NullValue {
  /**
   * Null value.
   */
  @ProtoNumber(number = 0)
  NULL_VALUE,
}
