package testgen.messages.proto3

import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public class MessageNoFields() {
  override fun toString(): String = "MessageNoFields"

  override fun hashCode(): Int = 70_682_849

  override fun equals(other: Any?): Boolean = other is MessageNoFields

  @Serializable
  public data class SubMessageNoFields(
    @ProtoNumber(number = 1)
    public val subHello: SubMessageNoFields? = null,
  ) {
    init {
      require(
        listOfNotNull(
          subHello,
        ).size <= 1
      ) { "Should only contain one of _subHello." } 
    }
    @Serializable
    public data class SubMessageNoFieldsExtend(
      @ProtoNumber(number = 1)
      public val type: Type = testgen.messages.proto3.MessageNoFields.Type.UNKNOWN,
    )
  }

  @Serializable
  public data class SubMessageOneofFields(
    @ProtoNumber(number = 1)
    public val someValue: Int? = null,
  ) {
    init {
      require(
        listOfNotNull(
          someValue,
        ).size <= 1
      ) { "Should only contain one of some_oneof." } 
    }
  }

  @Serializable
  public enum class Type {
    @ProtoNumber(number = 0)
    UNKNOWN,
    @ProtoNumber(number = 1)
    KNOWN,
  }
}
