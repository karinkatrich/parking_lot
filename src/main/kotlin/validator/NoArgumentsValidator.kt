package validator

class NoArgumentsValidator : ArgumentsValidator {
    override fun validate(vararg arguments: String): Boolean {
        return arguments.size == 0
    }
}