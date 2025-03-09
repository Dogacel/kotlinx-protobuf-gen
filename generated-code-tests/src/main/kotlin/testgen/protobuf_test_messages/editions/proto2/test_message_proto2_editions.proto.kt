package testgen.protobuf_test_messages.editions.proto2

import kotlin.Any
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
public data class TestAllTypesProto2(
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
  public val optionalForeignMessage: ForeignMessageProto2? = null,
  @ProtoNumber(number = 21)
  public val optionalNestedEnum: NestedEnum? = null,
  @ProtoNumber(number = 22)
  public val optionalForeignEnum: ForeignEnumProto2? = null,
  @ProtoNumber(number = 24)
  public val optionalStringPiece: String? = null,
  @ProtoNumber(number = 25)
  public val optionalCord: String? = null,
  @ProtoNumber(number = 27)
  public val recursiveMessage: TestAllTypesProto2? = null,
  @ProtoNumber(number = 31)
  public val repeatedInt32: List<Int> = emptyList(),
  @ProtoNumber(number = 32)
  public val repeatedInt64: List<Long> = emptyList(),
  @ProtoNumber(number = 33)
  public val repeatedUint32: List<UInt> = emptyList(),
  @ProtoNumber(number = 34)
  public val repeatedUint64: List<ULong> = emptyList(),
  @ProtoNumber(number = 35)
  public val repeatedSint32: List<Int> = emptyList(),
  @ProtoNumber(number = 36)
  public val repeatedSint64: List<Long> = emptyList(),
  @ProtoNumber(number = 37)
  public val repeatedFixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 38)
  public val repeatedFixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 39)
  public val repeatedSfixed32: List<Int> = emptyList(),
  @ProtoNumber(number = 40)
  public val repeatedSfixed64: List<Long> = emptyList(),
  @ProtoNumber(number = 41)
  public val repeatedFloat: List<Float> = emptyList(),
  @ProtoNumber(number = 42)
  public val repeatedDouble: List<Double> = emptyList(),
  @ProtoNumber(number = 43)
  public val repeatedBool: List<Boolean> = emptyList(),
  @ProtoNumber(number = 44)
  public val repeatedString: List<String> = emptyList(),
  @ProtoNumber(number = 45)
  public val repeatedBytes: List<ByteArray> = emptyList(),
  @ProtoNumber(number = 48)
  public val repeatedNestedMessage: List<NestedMessage?> = emptyList(),
  @ProtoNumber(number = 49)
  public val repeatedForeignMessage: List<ForeignMessageProto2?> = emptyList(),
  @ProtoNumber(number = 51)
  public val repeatedNestedEnum: List<NestedEnum> = emptyList(),
  @ProtoNumber(number = 52)
  public val repeatedForeignEnum: List<ForeignEnumProto2> = emptyList(),
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
  public val mapStringForeignMessage: Map<String?, ForeignMessageProto2?> = emptyMap(),
  @ProtoNumber(number = 73)
  public val mapStringNestedEnum: Map<String?, NestedEnum?> = emptyMap(),
  @ProtoNumber(number = 74)
  public val mapStringForeignEnum: Map<String?, ForeignEnumProto2?> = emptyMap(),
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
  public val `data`: Data? = null,
  @ProtoNumber(number = 204)
  public val multiwordgroupfield: MultiWordGroupField? = null,
  @ProtoNumber(number = 241)
  public val defaultInt32: Int? = null,
  @ProtoNumber(number = 242)
  public val defaultInt64: Long? = null,
  @ProtoNumber(number = 243)
  public val defaultUint32: UInt? = null,
  @ProtoNumber(number = 244)
  public val defaultUint64: ULong? = null,
  @ProtoNumber(number = 245)
  @ProtoType(type = SIGNED)
  public val defaultSint32: Int? = null,
  @ProtoNumber(number = 246)
  @ProtoType(type = SIGNED)
  public val defaultSint64: Long? = null,
  @ProtoNumber(number = 247)
  @ProtoType(type = FIXED)
  public val defaultFixed32: Int? = null,
  @ProtoNumber(number = 248)
  @ProtoType(type = FIXED)
  public val defaultFixed64: Long? = null,
  @ProtoNumber(number = 249)
  @ProtoType(type = FIXED)
  public val defaultSfixed32: Int? = null,
  @ProtoNumber(number = 250)
  @ProtoType(type = FIXED)
  public val defaultSfixed64: Long? = null,
  @ProtoNumber(number = 251)
  public val defaultFloat: Float? = null,
  @ProtoNumber(number = 252)
  public val defaultDouble: Double? = null,
  @ProtoNumber(number = 253)
  public val defaultBool: Boolean? = null,
  @ProtoNumber(number = 254)
  public val defaultString: String? = null,
  @ProtoNumber(number = 255)
  public val defaultBytes: ByteArray? = null,
  @ProtoNumber(number = 401)
  public val fieldname1: Int? = null,
  @ProtoNumber(number = 402)
  public val fieldName2: Int? = null,
  @ProtoNumber(number = 403)
  public val FieldName3: Int? = null,
  @ProtoNumber(number = 404)
  public val field_Name4_: Int? = null,
  @ProtoNumber(number = 405)
  public val field0name5: Int? = null,
  @ProtoNumber(number = 406)
  public val field_0Name6: Int? = null,
  @ProtoNumber(number = 407)
  public val fieldName7: Int? = null,
  @ProtoNumber(number = 408)
  public val FieldName8: Int? = null,
  @ProtoNumber(number = 409)
  public val fieldName9: Int? = null,
  @ProtoNumber(number = 410)
  public val FieldName10: Int? = null,
  @ProtoNumber(number = 411)
  public val FIELDNAME11: Int? = null,
  @ProtoNumber(number = 412)
  public val FIELDName12: Int? = null,
  @ProtoNumber(number = 413)
  public val _FieldName13: Int? = null,
  @ProtoNumber(number = 414)
  public val _FieldName14: Int? = null,
  @ProtoNumber(number = 415)
  public val field_Name15: Int? = null,
  @ProtoNumber(number = 416)
  public val field_Name16: Int? = null,
  @ProtoNumber(number = 417)
  public val fieldName17__: Int? = null,
  @ProtoNumber(number = 418)
  public val FieldName18__: Int? = null,
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
    public val corecursive: TestAllTypesProto2? = null,
  )

  @Serializable
  public data class Data(
    @ProtoNumber(number = 202)
    public val groupInt32: Int? = null,
    @ProtoNumber(number = 203)
    public val groupUint32: UInt? = null,
  )

  @Serializable
  public data class MultiWordGroupField(
    @ProtoNumber(number = 205)
    public val groupInt32: Int? = null,
    @ProtoNumber(number = 206)
    public val groupUint32: UInt? = null,
  )

  @Serializable
  public class MessageSetCorrect() {
    override fun toString(): String = "MessageSetCorrect"

    override fun hashCode(): Int = -1_389_416_209

    override fun equals(other: Any?): Boolean = other is MessageSetCorrect
  }

  @Serializable
  public data class MessageSetCorrectExtension1(
    @ProtoNumber(number = 25)
    public val str: String? = null,
  )

  @Serializable
  public data class MessageSetCorrectExtension2(
    @ProtoNumber(number = 9)
    public val i: Int? = null,
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
public data class ForeignMessageProto2(
  @ProtoNumber(number = 1)
  public val c: Int? = null,
)

@Serializable
public data class GroupField(
  @ProtoNumber(number = 122)
  public val groupInt32: Int? = null,
  @ProtoNumber(number = 123)
  public val groupUint32: UInt? = null,
)

@Serializable
public data class UnknownToTestAllTypes(
  @ProtoNumber(number = 1_001)
  public val optionalInt32: Int? = null,
  @ProtoNumber(number = 1_002)
  public val optionalString: String? = null,
  @ProtoNumber(number = 1_003)
  public val nestedMessage: ForeignMessageProto2? = null,
  @ProtoNumber(number = 1_004)
  public val optionalgroup: OptionalGroup? = null,
  @ProtoNumber(number = 1_006)
  public val optionalBool: Boolean? = null,
  @ProtoNumber(number = 1_011)
  public val repeatedInt32: List<Int> = emptyList(),
) {
  @Serializable
  public data class OptionalGroup(
    @ProtoNumber(number = 1)
    public val a: Int? = null,
  )
}

@Serializable
public class NullHypothesisProto2() {
  override fun toString(): String = "NullHypothesisProto2"

  override fun hashCode(): Int = -1_881_398_323

  override fun equals(other: Any?): Boolean = other is NullHypothesisProto2
}

@Serializable
public class EnumOnlyProto2() {
  override fun toString(): String = "EnumOnlyProto2"

  override fun hashCode(): Int = -1_048_199_657

  override fun equals(other: Any?): Boolean = other is EnumOnlyProto2

  @Serializable
  public enum class Bool {
    @ProtoNumber(number = 0)
    kFalse,
    @ProtoNumber(number = 1)
    kTrue,
  }
}

@Serializable
public data class OneStringProto2(
  @ProtoNumber(number = 1)
  public val `data`: String? = null,
)

@Serializable
public data class ProtoWithKeywords(
  @ProtoNumber(number = 1)
  public val `inline`: Int? = null,
  @ProtoNumber(number = 2)
  public val concept: String? = null,
  @ProtoNumber(number = 3)
  public val requires: List<String> = emptyList(),
)

@Serializable
public data class TestAllRequiredTypesProto2(
  @ProtoNumber(number = 1)
  public val requiredInt32: Int? = null,
  @ProtoNumber(number = 2)
  public val requiredInt64: Long? = null,
  @ProtoNumber(number = 3)
  public val requiredUint32: UInt? = null,
  @ProtoNumber(number = 4)
  public val requiredUint64: ULong? = null,
  @ProtoNumber(number = 5)
  @ProtoType(type = SIGNED)
  public val requiredSint32: Int? = null,
  @ProtoNumber(number = 6)
  @ProtoType(type = SIGNED)
  public val requiredSint64: Long? = null,
  @ProtoNumber(number = 7)
  @ProtoType(type = FIXED)
  public val requiredFixed32: Int? = null,
  @ProtoNumber(number = 8)
  @ProtoType(type = FIXED)
  public val requiredFixed64: Long? = null,
  @ProtoNumber(number = 9)
  @ProtoType(type = FIXED)
  public val requiredSfixed32: Int? = null,
  @ProtoNumber(number = 10)
  @ProtoType(type = FIXED)
  public val requiredSfixed64: Long? = null,
  @ProtoNumber(number = 11)
  public val requiredFloat: Float? = null,
  @ProtoNumber(number = 12)
  public val requiredDouble: Double? = null,
  @ProtoNumber(number = 13)
  public val requiredBool: Boolean? = null,
  @ProtoNumber(number = 14)
  public val requiredString: String? = null,
  @ProtoNumber(number = 15)
  public val requiredBytes: ByteArray? = null,
  @ProtoNumber(number = 18)
  public val requiredNestedMessage: NestedMessage? = null,
  @ProtoNumber(number = 19)
  public val requiredForeignMessage: ForeignMessageProto2? = null,
  @ProtoNumber(number = 21)
  public val requiredNestedEnum: NestedEnum? = null,
  @ProtoNumber(number = 22)
  public val requiredForeignEnum: ForeignEnumProto2? = null,
  @ProtoNumber(number = 24)
  public val requiredStringPiece: String? = null,
  @ProtoNumber(number = 25)
  public val requiredCord: String? = null,
  @ProtoNumber(number = 27)
  public val recursiveMessage: TestAllRequiredTypesProto2? = null,
  @ProtoNumber(number = 28)
  public val optionalRecursiveMessage: TestAllRequiredTypesProto2? = null,
  @ProtoNumber(number = 201)
  public val `data`: Data? = null,
  @ProtoNumber(number = 241)
  public val defaultInt32: Int? = null,
  @ProtoNumber(number = 242)
  public val defaultInt64: Long? = null,
  @ProtoNumber(number = 243)
  public val defaultUint32: UInt? = null,
  @ProtoNumber(number = 244)
  public val defaultUint64: ULong? = null,
  @ProtoNumber(number = 245)
  @ProtoType(type = SIGNED)
  public val defaultSint32: Int? = null,
  @ProtoNumber(number = 246)
  @ProtoType(type = SIGNED)
  public val defaultSint64: Long? = null,
  @ProtoNumber(number = 247)
  @ProtoType(type = FIXED)
  public val defaultFixed32: Int? = null,
  @ProtoNumber(number = 248)
  @ProtoType(type = FIXED)
  public val defaultFixed64: Long? = null,
  @ProtoNumber(number = 249)
  @ProtoType(type = FIXED)
  public val defaultSfixed32: Int? = null,
  @ProtoNumber(number = 250)
  @ProtoType(type = FIXED)
  public val defaultSfixed64: Long? = null,
  @ProtoNumber(number = 251)
  public val defaultFloat: Float? = null,
  @ProtoNumber(number = 252)
  public val defaultDouble: Double? = null,
  @ProtoNumber(number = 253)
  public val defaultBool: Boolean? = null,
  @ProtoNumber(number = 254)
  public val defaultString: String? = null,
  @ProtoNumber(number = 255)
  public val defaultBytes: ByteArray? = null,
) {
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val a: Int? = null,
    @ProtoNumber(number = 2)
    public val corecursive: TestAllRequiredTypesProto2? = null,
    @ProtoNumber(number = 3)
    public val optionalCorecursive: TestAllRequiredTypesProto2? = null,
  )

  @Serializable
  public data class Data(
    @ProtoNumber(number = 202)
    public val groupInt32: Int? = null,
    @ProtoNumber(number = 203)
    public val groupUint32: UInt? = null,
  )

  @Serializable
  public class MessageSetCorrect() {
    override fun toString(): String = "MessageSetCorrect"

    override fun hashCode(): Int = -1_389_416_209

    override fun equals(other: Any?): Boolean = other is MessageSetCorrect
  }

  @Serializable
  public data class MessageSetCorrectExtension1(
    @ProtoNumber(number = 25)
    public val str: String? = null,
  )

  @Serializable
  public data class MessageSetCorrectExtension2(
    @ProtoNumber(number = 9)
    public val i: Int? = null,
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
public enum class ForeignEnumProto2 {
  @ProtoNumber(number = 0)
  FOREIGN_FOO,
  @ProtoNumber(number = 1)
  FOREIGN_BAR,
  @ProtoNumber(number = 2)
  FOREIGN_BAZ,
}
