package validator

import java.util.regex.Pattern

class SpotNumberValidator(private val parkingMaximumCapacity: Int) : ArgumentsValidator {

    override fun validate(vararg arguments: String): Boolean {
        if (arguments == null || arguments.size != 1) {
            return false
        }
        val spotNumberArgument = arguments[0]
        val spotNumberMatcher = pattern.matcher(spotNumberArgument)
        return spotNumberMatcher.matches() && Integer.parseInt(spotNumberArgument) <= parkingMaximumCapacity
    }

    companion object {

        private val SPOT_NUMBER_EXPRESSION = "^[0-9]{1,6}$"
        private val pattern = Pattern.compile(SPOT_NUMBER_EXPRESSION)
    }
}