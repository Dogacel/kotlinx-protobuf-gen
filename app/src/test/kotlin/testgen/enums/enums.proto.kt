package testgen.enums

import kotlin.Int
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class MessageWithEnum(
  @ProtoNumber(number = 1)
  public val id: Int = 0,
  @ProtoNumber(number = 2)
  public val test_enum: TestEnum = testgen.enums.TestEnum.FOO,
  @ProtoNumber(number = 3)
  public val aliased_enum: AliasedEnum = testgen.enums.AliasedEnum.ALIAS_FOO,
)

public enum class TestEnum {
  @ProtoNumber(number = 0)
  FOO,
  @ProtoNumber(number = 1)
  BAR,
  @ProtoNumber(number = 2)
  BAZ,
  @ProtoNumber(number = 100)
  TOP,
}

public enum class AliasedEnum {
  @ProtoNumber(number = 0)
  ALIAS_FOO,
  @ProtoNumber(number = 1)
  ALIAS_BAR,
  @ProtoNumber(number = 2)
  ALIAS_BAZ,
  @ProtoNumber(number = 2)
  QUX,
  @ProtoNumber(number = 2)
  qux,
  @ProtoNumber(number = 2)
  bAz,
}
