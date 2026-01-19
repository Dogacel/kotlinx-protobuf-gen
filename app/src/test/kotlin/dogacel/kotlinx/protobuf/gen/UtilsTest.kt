package dogacel.kotlinx.protobuf.gen

import dogacel.kotlinx.protobuf.gen.Utils.removeEnumPrefixFromValue
import dogacel.kotlinx.protobuf.gen.Utils.toScreamingSnakeCase
import kotlin.test.Test
import kotlin.test.assertEquals

class UtilsTest {
    @Test
    fun toScreamingSnakeCase_simpleCase() {
        assertEquals("STATE", "State".toScreamingSnakeCase())
    }

    @Test
    fun toScreamingSnakeCase_multiWord() {
        assertEquals("FOREIGN_ENUM", "ForeignEnum".toScreamingSnakeCase())
    }

    @Test
    fun toScreamingSnakeCase_multipleUpperCase() {
        assertEquals("MY_ENUM_VALUE", "MyEnumValue".toScreamingSnakeCase())
    }

    @Test
    fun toScreamingSnakeCase_alreadyUpperCase() {
        assertEquals("STATE", "STATE".toScreamingSnakeCase())
    }

    @Test
    fun toScreamingSnakeCase_emptyString() {
        assertEquals("", "".toScreamingSnakeCase())
    }

    @Test
    fun toScreamingSnakeCase_singleChar() {
        assertEquals("A", "a".toScreamingSnakeCase())
        assertEquals("A", "A".toScreamingSnakeCase())
    }

    @Test
    fun toScreamingSnakeCase_camelCase() {
        assertEquals("CAMEL_CASE", "camelCase".toScreamingSnakeCase())
    }

    @Test
    fun removeEnumPrefixFromValue_matchingPrefix() {
        assertEquals("ACTIVE", removeEnumPrefixFromValue("State", "STATE_ACTIVE"))
        assertEquals("UNKNOWN", removeEnumPrefixFromValue("State", "STATE_UNKNOWN"))
    }

    @Test
    fun removeEnumPrefixFromValue_multiWordEnumName() {
        assertEquals("FOO", removeEnumPrefixFromValue("ForeignEnum", "FOREIGN_ENUM_FOO"))
        assertEquals("BAR_BAZ", removeEnumPrefixFromValue("ForeignEnum", "FOREIGN_ENUM_BAR_BAZ"))
    }

    @Test
    fun removeEnumPrefixFromValue_noMatch() {
        assertEquals("INVALID", removeEnumPrefixFromValue("State", "INVALID"))
        assertEquals("OTHER_VALUE", removeEnumPrefixFromValue("State", "OTHER_VALUE"))
    }

    @Test
    fun removeEnumPrefixFromValue_partialMatch() {
        assertEquals("ALIAS_FOO", removeEnumPrefixFromValue("AliasedEnum", "ALIAS_FOO"))
    }

    @Test
    fun removeEnumPrefixFromValue_prefixOnly() {
        assertEquals("STATE_", removeEnumPrefixFromValue("State", "STATE_"))
    }

    @Test
    fun removeEnumPrefixFromValue_exactPrefix() {
        assertEquals("STATE", removeEnumPrefixFromValue("State", "STATE"))
    }

    @Test
    fun removeEnumPrefixFromValue_emptyEnumName() {
        assertEquals("VALUE", removeEnumPrefixFromValue("", "_VALUE"))
        assertEquals("VALUE", removeEnumPrefixFromValue("", "VALUE"))
    }
}
