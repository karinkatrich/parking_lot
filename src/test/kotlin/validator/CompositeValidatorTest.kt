package validator

import org.junit.Assert
import org.junit.Test

class CompositeValidatorTest {

    private val validator = CompositeRegistrationAndColorValidator()

    @Test
    fun shouldSucceedForCorrectInput() {
        Assert.assertTrue(validator.validate("KA-01-HH-1234", "White"))
        Assert.assertTrue(validator.validate("BRA-17-DL-1760", "Green"))
        Assert.assertTrue(validator.validate("IO-15-NO-9988", "Blue"))
    }

    @Test
    fun shouldFailWhenNoColor() {
        Assert.assertFalse(validator.validate("KA-01-HH-1234"))
    }

    @Test
    fun shouldFailWhenNoRegistrationNumber() {
        Assert.assertFalse(validator.validate("White"))
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