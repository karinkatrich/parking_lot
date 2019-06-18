package validator

import org.junit.Assert
import org.junit.Test

class ColorValidatorTest {

    private val validator = ColorValidator()

    @Test
    fun shouldSucceedForCorrectInput() {
        Assert.assertTrue(validator.validate("White"))
        Assert.assertTrue(validator.validate("Black"))
        Assert.assertTrue(validator.validate("Orange"))
    }

    @Test
    fun shouldFailWhenColorIsToShort() {
        Assert.assertFalse(validator.validate("ab"))
    }

    @Test
    fun shouldFailWhenColorIsToLong() {
        Assert.assertFalse(validator.validate("thisIsClearlyToLongNameForAColor"))
    }

    @Test
    fun shouldFailWhenColorContainsNumbers() {
        Assert.assertFalse(validator.validate("bl0ck"))
    }

    @Test
    fun shouldFailWhenStringIsEmpty() {
        Assert.assertFalse(validator.validate(""))
    }

    @Test
    fun shouldFailWhenNoArgumentsGiven() {
        Assert.assertFalse(validator.validate())
    }

}