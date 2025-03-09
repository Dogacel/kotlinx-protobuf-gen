package testgen.protobuf_test_messages.editions

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
import kotlin.collections.Map
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoIntegerType.FIXED
import kotlinx.serialization.protobuf.ProtoIntegerType.SIGNED
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked
import kotlinx.serialization.protobuf.ProtoType

@Serializable
public data class ComplexMessage(
  @ProtoNumber(number = 1)
  public val d: Int? = null,
)

@Serializable
public data class TestAllTypesEdition2023(
  @ProtoNumber(number = 1)
  public val optionalInt32: Int? = null,
  @ProtoNumber(number = 2)
  public val optionalInt64: Long? = null,
  @ProtoNumber(number = 3)
  public val optionalUint32: UInt? = null,
  @ProtoNumber(number = 4)
  public val optionalUint64: ULong? = null,
  @ProtoNumber(number = 5)
  @ProtoType(type = SIGNED)
  public val optionalSint32: Int? = null,
  @ProtoNumber(number = 6)
  @ProtoType(type = SIGNED)
  public val optionalSint64: Long? = null,
  @ProtoNumber(number = 7)
  @ProtoType(type = FIXED)
  public val optionalFixed32: Int? = null,
  @ProtoNumber(number = 8)
  @ProtoType(type = FIXED)
  public val optionalFixed64: Long? = null,
  @ProtoNumber(number = 9)
  @ProtoType(type = FIXED)
  public val optionalSfixed32: Int? = null,
  @ProtoNumber(number = 10)
  @ProtoType(type = FIXED)
  public val optionalSfixed64: Long? = null,
  @ProtoNumber(number = 11)
  public val optionalFloat: Float? = null,
  @ProtoNumber(number = 12)
  public val optionalDouble: Double? = null,
  @ProtoNumber(number = 13)
  public val optionalBool: Boolean? = null,
  @ProtoNumber(number = 14)
  public val optionalString: String? = null,
  @ProtoNumber(number = 15)
  public val optionalBytes: ByteArray? = null,
  @ProtoNumber(number = 18)
  public val optionalNestedMessage: NestedMessage? = null,
  @ProtoNumber(number = 19)
  public val optionalForeignMessage: ForeignMessageEdition2023? = null,
  @ProtoNumber(number = 21)
  public val optionalNestedEnum: NestedEnum? = null,
  @ProtoNumber(number = 22)
  public val optionalForeignEnum: ForeignEnumEdition2023? = null,
  @ProtoNumber(number = 24)
  public val optionalStringPiece: String? = null,
  @ProtoNumber(number = 25)
  public val optionalCord: String? = null,
  @ProtoNumber(number = 27)
  public val recursiveMessage: TestAllTypesEdition2023? = null,
  @ProtoNumber(number = 31)
  @ProtoPacked
  public val repeatedInt32: List<Int> = emptyList(),
  @ProtoNumber(number = 32)
  @ProtoPacked
  public val repeatedInt64: List<Long> = emptyList(),
  @ProtoNumber(number = 33)
  @ProtoPacked
  public val repeatedUint32: List<UInt> = emptyList(),
  @ProtoNumber(number = 34)
  @ProtoPacked
  public val repeatedUint64: List<ULong> = emptyList(),
  @ProtoNumber(number = 35)
  @ProtoPacked
  public val repeatedSint32: List<Int> = emptyList(),
  @ProtoNumber(number = 36)
  @ProtoPacked
  public val repeatedSint64: List<Long> = emptyList(),
  @ProtoNumber(number = 37)
  @ProtoPacked
  public val repeatedFixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 38)
  @ProtoPacked
  public val repeatedFixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 39)
  @ProtoPacked
  public val repeatedSfixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 40)
  @ProtoPacked
  public val repeatedSfixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 41)
  @ProtoPacked
  public val repeatedFloat: List<Float> = emptyList(),
  @ProtoNumber(number = 42)
  @ProtoPacked
  public val repeatedDouble: List<Double> = emptyList(),
  @ProtoNumber(number = 43)
  @ProtoPacked
  public val repeatedBool: List<Boolean> = emptyList(),
  @ProtoNumber(number = 44)
  public val repeatedString: List<String> = emptyList(),
  @ProtoNumber(number = 45)
  public val repeatedBytes: List<ByteArray> = emptyList(),
  @ProtoNumber(number = 48)
  public val repeatedNestedMessage: List<NestedMessage?> = emptyList(),
  @ProtoNumber(number = 49)
  public val repeatedForeignMessage: List<ForeignMessageEdition2023?> = emptyList(),
  @ProtoNumber(number = 51)
  @ProtoPacked
  public val repeatedNestedEnum: List<NestedEnum> = emptyList(),
  @ProtoNumber(number = 52)
  @ProtoPacked
  public val repeatedForeignEnum: List<ForeignEnumEdition2023> = emptyList(),
  @ProtoNumber(number = 54)
  public val repeatedStringPiece: List<String> = emptyList(),
  @ProtoNumber(number = 55)
  public val repeatedCord: List<String> = emptyList(),
  @ProtoNumber(number = 75)
  @ProtoPacked
  public val packedInt32: List<Int> = emptyList(),
  @ProtoNumber(number = 76)
  @ProtoPacked
  public val packedInt64: List<Long> = emptyList(),
  @ProtoNumber(number = 77)
  @ProtoPacked
  public val packedUint32: List<UInt> = emptyList(),
  @ProtoNumber(number = 78)
  @ProtoPacked
  public val packedUint64: List<ULong> = emptyList(),
  @ProtoNumber(number = 79)
  @ProtoPacked
  public val packedSint32: List<Int> = emptyList(),
  @ProtoNumber(number = 80)
  @ProtoPacked
  public val packedSint64: List<Long> = emptyList(),
  @ProtoNumber(number = 81)
  @ProtoPacked
  public val packedFixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 82)
  @ProtoPacked
  public val packedFixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 83)
  @ProtoPacked
  public val packedSfixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 84)
  @ProtoPacked
  public val packedSfixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 85)
  @ProtoPacked
  public val packedFloat: List<Float> = emptyList(),
  @ProtoNumber(number = 86)
  @ProtoPacked
  public val packedDouble: List<Double> = emptyList(),
  @ProtoNumber(number = 87)
  @ProtoPacked
  public val packedBool: List<Boolean> = emptyList(),
  @ProtoNumber(number = 88)
  @ProtoPacked
  public val packedNestedEnum: List<NestedEnum> = emptyList(),
  @ProtoNumber(number = 89)
  public val unpackedInt32: List<Int> = emptyList(),
  @ProtoNumber(number = 90)
  public val unpackedInt64: List<Long> = emptyList(),
  @ProtoNumber(number = 91)
  public val unpackedUint32: List<UInt> = emptyList(),
  @ProtoNumber(number = 92)
  public val unpackedUint64: List<ULong> = emptyList(),
  @ProtoNumber(number = 93)
  public val unpackedSint32: List<Int> = emptyList(),
  @ProtoNumber(number = 94)
  public val unpackedSint64: List<Long> = emptyList(),
  @ProtoNumber(number = 95)
  public val unpackedFixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 96)
  public val unpackedFixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 97)
  public val unpackedSfixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 98)
  public val unpackedSfixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 99)
  public val unpackedFloat: List<Float> = emptyList(),
  @ProtoNumber(number = 100)
  public val unpackedDouble: List<Double> = emptyList(),
  @ProtoNumber(number = 101)
  public val unpackedBool: List<Boolean> = emptyList(),
  @ProtoNumber(number = 102)
  public val unpackedNestedEnum: List<NestedEnum> = emptyList(),
  @ProtoNumber(number = 56)
  public val mapInt32Int32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 57)
  public val mapInt64Int64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 58)
  public val mapUint32Uint32: Map<UInt?, UInt?> = emptyMap(),
  @ProtoNumber(number = 59)
  public val mapUint64Uint64: Map<ULong?, ULong?> = emptyMap(),
  @ProtoNumber(number = 60)
  public val mapSint32Sint32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 61)
  public val mapSint64Sint64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 62)
  public val mapFixed32Fixed32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 63)
  public val mapFixed64Fixed64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 64)
  public val mapSfixed32Sfixed32: Map<Int?, Int?> = emptyMap(),
  @ProtoNumber(number = 65)
  public val mapSfixed64Sfixed64: Map<Long?, Long?> = emptyMap(),
  @ProtoNumber(number = 66)
  public val mapInt32Float: Map<Int?, Float?> = emptyMap(),
  @ProtoNumber(number = 67)
  public val mapInt32Double: Map<Int?, Double?> = emptyMap(),
  @ProtoNumber(number = 68)
  public val mapBoolBool: Map<Boolean?, Boolean?> = emptyMap(),
  @ProtoNumber(number = 69)
  public val mapStringString: Map<String?, String?> = emptyMap(),
  @ProtoNumber(number = 70)
  public val mapStringBytes: Map<String?, ByteArray?> = emptyMap(),
  @ProtoNumber(number = 71)
  public val mapStringNestedMessage: Map<String?, NestedMessage?> = emptyMap(),
  @ProtoNumber(number = 72)
  public val mapStringForeignMessage: Map<String?, ForeignMessageEdition2023?> = emptyMap(),
  @ProtoNumber(number = 73)
  public val mapStringNestedEnum: Map<String?, NestedEnum?> = emptyMap(),
  @ProtoNumber(number = 74)
  public val mapStringForeignEnum: Map<String?, ForeignEnumEdition2023?> = emptyMap(),
  @ProtoNumber(number = 111)
  public val oneofUint32: UInt? = null,
  @ProtoNumber(number = 112)
  public val oneofNestedMessage: NestedMessage? = null,
  @ProtoNumber(number = 113)
  public val oneofString: String? = null,
  @ProtoNumber(number = 114)
  public val oneofBytes: ByteArray? = null,
  @ProtoNumber(number = 115)
  public val oneofBool: Boolean? = null,
  @ProtoNumber(number = 116)
  public val oneofUint64: ULong? = null,
  @ProtoNumber(number = 117)
  public val oneofFloat: Float? = null,
  @ProtoNumber(number = 118)
  public val oneofDouble: Double? = null,
  @ProtoNumber(number = 119)
  public val oneofEnum: NestedEnum? = null,
  @ProtoNumber(number = 201)
  public val groupliketype: GroupLikeType? = null,
  @ProtoNumber(number = 202)
  public val delimitedField: GroupLikeType? = null,
) {
  init {
    require(
      listOfNotNull(
        oneofUint32,
        oneofNestedMessage,
        oneofString,
        oneofBytes,
        oneofBool,
        oneofUint64,
        oneofFloat,
        oneofDouble,
        oneofEnum,
      ).size <= 1
    ) { "Should only contain one of oneof_field." } 
  }
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val a: Int? = null,
    @ProtoNumber(number = 2)
    public val corecursive: TestAllTypesEdition2023? = null,
  )

  @Serializable
  public data class GroupLikeType(
    @ProtoNumber(number = 202)
    public val groupInt32: Int? = null,
    @ProtoNumber(number = 203)
    public val groupUint32: UInt? = null,
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
public data class ForeignMessageEdition2023(
  @ProtoNumber(number = 1)
  public val c: Int? = null,
)

@Serializable
public data class GroupLikeType(
  @ProtoNumber(number = 1)
  public val c: Int? = null,
)

@Serializable
public enum class ForeignEnumEdition2023 {
  @ProtoNumber(number = 0)
  FOREIGN_FOO,
  @ProtoNumber(number = 1)
  FOREIGN_BAR,
  @ProtoNumber(number = 2)
  FOREIGN_BAZ,
}
