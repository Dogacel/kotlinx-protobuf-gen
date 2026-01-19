package testgen.enums_prefix.proto3

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class StatusMessage(
  @ProtoNumber(number = 1)
  public val state: State = testgen.enums_prefix.proto3.State.STATE_UNKNOWN,
  @ProtoNumber(number = 2)
  public val foreignEnum:
      ForeignEnum = testgen.enums_prefix.proto3.ForeignEnum.FOREIGN_ENUM_UNSPECIFIED,
)

@Serializable
public enum class State {
  @ProtoNumber(number = 0)
  STATE_UNKNOWN,
  @ProtoNumber(number = 1)
  STATE_ACTIVE,
  @ProtoNumber(number = 2)
  STATE_INACTIVE,
}

@Serializable
public enum class ForeignEnum {
  @ProtoNumber(number = 0)
  FOREIGN_ENUM_UNSPECIFIED,
  @ProtoNumber(number = 1)
  FOREIGN_ENUM_FOO,
  @ProtoNumber(number = 2)
  FOREIGN_ENUM_BAR,
}
