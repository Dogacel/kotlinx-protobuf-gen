package testgen.repeateds

import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlin.collections.List
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoIntegerType.FIXED
import kotlinx.serialization.protobuf.ProtoIntegerType.SIGNED
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked
import kotlinx.serialization.protobuf.ProtoType

@Serializable
public data class RepeatedsMessage(
  @ProtoNumber(number = 1)
  @ProtoPacked
  public val repeatedInt32: List<Int> = emptyList(),
  @ProtoNumber(number = 2)
  @ProtoPacked
  public val repeatedInt64: List<Long> = emptyList(),
  @ProtoNumber(number = 3)
  @ProtoPacked
  public val repeatedUint32: List<UInt> = emptyList(),
  @ProtoNumber(number = 4)
  @ProtoPacked
  public val repeatedUint64: List<ULong> = emptyList(),
  @ProtoNumber(number = 5)
  @ProtoPacked
  @ProtoType(type = SIGNED)
  public val repeatedSint32: List<Int> = emptyList(),
  @ProtoNumber(number = 6)
  @ProtoPacked
  @ProtoType(type = SIGNED)
  public val repeatedSint64: List<Long> = emptyList(),
  @ProtoNumber(number = 7)
  @ProtoPacked
  @ProtoType(type = FIXED)
  public val repeatedFixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 8)
  @ProtoPacked
  @ProtoType(type = FIXED)
  public val repeatedFixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 9)
  @ProtoPacked
  @ProtoType(type = FIXED)
  public val repeatedSfixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 10)
  @ProtoPacked
  @ProtoType(type = FIXED)
  public val repeatedSfixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 11)
  @ProtoPacked
  public val repeatedFloat: List<Float> = emptyList(),
  @ProtoNumber(number = 12)
  @ProtoPacked
  public val repeatedDouble: List<Double> = emptyList(),
  @ProtoNumber(number = 13)
  @ProtoPacked
  public val repeatedBool: List<Boolean> = emptyList(),
  @ProtoNumber(number = 14)
  public val repeatedString: List<String> = emptyList(),
  @ProtoNumber(number = 15)
  public val repeatedBytes: List<ByteArray> = emptyList(),
  @ProtoNumber(number = 16)
  public val repeatedNestedMessage: List<NestedMessage?> = emptyList(),
  @ProtoNumber(number = 17)
  public val repeatedForeignMessage: List<ForeignMessage?> = emptyList(),
  @ProtoNumber(number = 18)
  @ProtoPacked
  public val repeatedNestedEnum: List<NestedEnum> = emptyList(),
  @ProtoNumber(number = 19)
  @ProtoPacked
  public val repeatedForeignEnum: List<ForeignEnum> = emptyList(),
) {
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val a: Int = 0,
    @ProtoNumber(number = 2)
    public val corecursive: RepeatedsMessage? = null,
  )

  @Serializable
  public enum class NestedEnum {
    @ProtoNumber(number = 0)
    FOO,
    @ProtoNumber(number = 1)
    BAR,
    @ProtoNumber(number = 2)
    BAZ,
    @ProtoNumber(number = -1)
    NEG,
  }
}

@Serializable
public data class ForeignMessage(
  @ProtoNumber(number = 1)
  public val c: Int = 0,
)

@Serializable
public enum class ForeignEnum {
  @ProtoNumber(number = 0)
  FOREIGN_FOO,
  @ProtoNumber(number = 1)
  FOREIGN_BAR,
  @ProtoNumber(number = 2)
  FOREIGN_BAZ,
}
