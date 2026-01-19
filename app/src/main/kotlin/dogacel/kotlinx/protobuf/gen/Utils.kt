package dogacel.kotlinx.protobuf.gen

import java.util.Locale

/**
 * A generic set of utils usually that do not belong to a specific class.
 */
object Utils {
    /**
     * Convert the string from snake case to lower camel case if the given condition is `true`.
     *
     * Example
     * - `foo_bar` -> `fooBar`
     * - `fooBar` -> `fooBar`
     * - `foo_bar_baz` -> `fooBarBaz`
     *
     * @param apply Whether to apply the transformation
     * @return The string in lower camel case if the condition is `true`, otherwise the original string.
     */
    fun String.toLowerCamelCaseIf(apply: Boolean = true): String {
        if (!apply) return this

        val snakeRegex = "_[a-zA-Z]".toRegex()
        return snakeRegex.replace(this) {
            it.value.replace("_", "").uppercase(Locale.ENGLISH)
        }
    }

    /**
     * Convert the first character of a string to lower.
     *
     * Example
     * - `Foo` -> `foo`
     * - `foo` -> `foo`
     *
     * @return The string starting with a lowercase character.
     */
    fun String.toFirstLower(): String {
        if (this.length <= 1) {
            return this.lowercase()
        }
        return this[0].lowercaseChar() + this.substring(1)
    }

    /**
     * Convert the string from PascalCase/camelCase to SCREAMING_SNAKE_CASE.
     *
     * Example:
     * - `ForeignEnum` -> `FOREIGN_ENUM`
     * - `State` -> `STATE`
     * - `MyEnumValue` -> `MY_ENUM_VALUE`
     *
     * @return The string in SCREAMING_SNAKE_CASE.
     */
    fun String.toScreamingSnakeCase(): String {
        if (isEmpty()) return this
        val result = StringBuilder()
        for ((index, char) in withIndex()) {
            if (char.isUpperCase() && index > 0 && this[index - 1].isLowerCase()) {
                result.append('_')
            }
            result.append(char.uppercaseChar())
        }
        return result.toString()
    }

    /**
     * Remove the enum name prefix from an enum value name if it matches the convention.
     *
     * Example:
     * - `removeEnumPrefixFromValue("State", "STATE_ACTIVE")` -> `ACTIVE`
     * - `removeEnumPrefixFromValue("State", "STATE_UNKNOWN")` -> `UNKNOWN`
     * - `removeEnumPrefixFromValue("State", "INVALID")` -> `INVALID` (no match)
     *
     * @param enumName The name of the enum (e.g., "State", "ForeignEnum")
     * @param valueName The name of the enum value (e.g., "STATE_ACTIVE", "FOREIGN_ENUM_FOO")
     * @return The value name with the prefix removed if it matches, otherwise the original value name.
     */
    fun removeEnumPrefixFromValue(
        enumName: String,
        valueName: String,
    ): String {
        val prefix = enumName.toScreamingSnakeCase() + "_"
        return if (valueName.startsWith(prefix) && valueName.length > prefix.length) {
            valueName.removePrefix(prefix)
        } else {
            valueName
        }
    }
}
