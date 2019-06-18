package validator

import java.util.regex.Pattern

class ColorValidator : ArgumentsValidator {

    override fun validate(vararg arguments: String): Boolean {
        if (arguments == null || arguments.size != 1) {
            return false
        }
        val colorMatcher = pattern.matcher(arguments[0])
        return colorMatcher.matches()
    }

    companion object {
        private val COLOR_EXPRESSION = "^[A-z]{3,10}$"
        private val pattern = Pattern.compile(COLOR_EXPRESSION)
    }
}