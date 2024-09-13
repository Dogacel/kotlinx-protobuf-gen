package testgen.maps

import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.UInt
import kotlin.ULong
import kotlin.collections.Map
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class MapsMessage(
  @ProtoNumber(number = 1)
  public val mapInt32Int32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 2)
  public val mapInt64Int64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 3)
  public val mapUint32Uint32: Map<UInt?, UInt?> = emptyMap(),
  @ProtoNumber(number = 4)
  public val mapUint64Uint64: Map<ULong?, ULong?> = emptyMap(),
  @ProtoNumber(number = 5)
  public val mapSint32Sint32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 6)
  public val mapSint64Sint64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 7)
  public val mapFixed32Fixed32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 8)
  public val mapFixed64Fixed64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 9)
  public val mapSfixed32Sfixed32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 10)
  public val mapSfixed64Sfixed64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 11)
  public val mapInt32Float: Map<Int?, Float?> = emptyMap(),
  @ProtoNumber(number = 12)
  public val mapInt32Double: Map<Int?, Double?> = emptyMap(),
  @ProtoNumber(number = 13)
  public val mapBoolBool: Map<Boolean?, Boolean?> = emptyMap(),
  @ProtoNumber(number = 14)
  public val mapStringString: Map<String?, String?> = emptyMap(),
  @ProtoNumber(number = 15)
  public val mapStringBytes: Map<String?, ByteArray?> = emptyMap(),
  @ProtoNumber(number = 16)
  public val mapStringNestedMessage: Map<String?, NestedMessage?> = emptyMap(),
  @ProtoNumber(number = 17)
  public val mapStringForeignMessage: Map<String?, ForeignMessage?> = emptyMap(),
  @ProtoNumber(number = 18)
  public val mapStringNestedEnum: Map<String?, NestedEnum?> = emptyMap(),
  @ProtoNumber(number = 19)
  public val mapStringForeignEnum: Map<String?, ForeignEnum?> = emptyMap(),
) {
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val a: Int? = 0,
    @ProtoNumber(number = 2)
    public val corecursive: MapsMessage? = null,
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
  public val c: Int? = 0,
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
