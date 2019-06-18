package validator

interface ArgumentsValidator {
    fun validate(vararg arguments: String): Boolean
}