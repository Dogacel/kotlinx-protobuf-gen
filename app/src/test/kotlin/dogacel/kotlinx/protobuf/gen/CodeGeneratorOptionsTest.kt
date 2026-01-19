package dogacel.kotlinx.protobuf.gen

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CodeGeneratorOptionsTest {
    @Test
    fun parse_removeEnumPrefix_enabled() {
        val options = CodeGeneratorOptions.parse("remove_enum_prefix")
        assertTrue(options.removeEnumPrefix)
    }

    @Test
    fun parse_removeEnumPrefix_disabled_byDefault() {
        val options = CodeGeneratorOptions.parse("")
        assertFalse(options.removeEnumPrefix)
    }

    @Test
    fun parse_removeEnumPrefix_withOtherFlags() {
        val options = CodeGeneratorOptions.parse("remove_enum_prefix,use_snake_case")
        assertTrue(options.removeEnumPrefix)
        assertFalse(options.useCamelCase)
    }

    @Test
    fun parse_removeEnumPrefix_withPackagePrefix() {
        val options = CodeGeneratorOptions.parse("package_prefix=custom.pkg,remove_enum_prefix")
        assertTrue(options.removeEnumPrefix)
        assertEquals("custom.pkg", options.packagePrefix)
    }

    @Test
    fun defaultOptions_removeEnumPrefix_isFalse() {
        val options = CodeGeneratorOptions()
        assertFalse(options.removeEnumPrefix)
    }
}
