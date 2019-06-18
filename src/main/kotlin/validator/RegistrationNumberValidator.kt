package validator

import java.util.regex.Pattern

class RegistrationNumberValidator : ArgumentsValidator {

    override fun validate(vararg arguments: String): Boolean {
        if (arguments == null || arguments.size != 1) {
            return false
        }
        val registrationNumberMatcher = pattern.matcher(arguments[0])
        return registrationNumberMatcher.matches()
    }

    companion object {

        private val REGISTRATION_NUMBER_EXPRESSION = "^[A-Z]{1,3}-[0-9]{2}-[A-Z]{1,2}-[0-9]{1,4}$"
        private val pattern = Pattern.compile(REGISTRATION_NUMBER_EXPRESSION)
    }
}