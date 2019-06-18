package validator

import org.junit.Assert
import org.junit.Test

class RegistrationNumberValidatorTest {

    private val validator = RegistrationNumberValidator()

    @Test
    fun shouldSucceedForCorrectInput() {
        Assert.assertTrue(validator.validate("KA-01-HH-1234"))
        Assert.assertTrue(validator.validate("BRA-17-DL-1760"))
        Assert.assertTrue(validator.validate("IO-15-NO-9988"))
    }

    @Test
    fun shouldFailWhenRegistrationNumberPatternIsIncorrect() {
        Assert.assertFalse(validator.validate("1234Green"))
    }

    @Test
    fun shouldFailWhenStringIsEmpty() {
        Assert.assertFalse(validator.validate(""))
    }

    @Test
    fun shouldFailWhenNoArgumentsGiven() {
        Assert.assertFalse(validator.validate())
    }

    @Test
    fun shouldFailWhenStringIsNull() {
        Assert.assertFalse(validator.validate(null.toString()))
    }

}