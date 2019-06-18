package validator

class CompositeRegistrationAndColorValidator : ArgumentsValidator {

    override fun validate(vararg arguments: String): Boolean {
        return if (arguments == null || arguments.size != 2) {
            false
        } else registrationNumberValidator.validate(arguments[COLOR_ARG_POSITION]) && colorValidator.validate(
            arguments[REG_NUMBER_ARGUMENT_POSITION]
        )
    }

    companion object {
        private val COLOR_ARG_POSITION = 0
        private val REG_NUMBER_ARGUMENT_POSITION = 1

        private val registrationNumberValidator = RegistrationNumberValidator()
        private val colorValidator = ColorValidator()
    }
}