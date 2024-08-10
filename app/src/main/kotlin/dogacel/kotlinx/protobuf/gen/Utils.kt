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
}
