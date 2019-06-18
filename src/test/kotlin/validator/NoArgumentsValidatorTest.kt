package validator

import org.junit.Assert
import org.junit.Test

class NoArgumentsValidatorTest {

    private val validator = NoArgumentsValidator()

    @Test
    fun shouldReturnFalseWhenSomeArgumentsGiven() {
        Assert.assertFalse(validator.validate("1"))
    }

    @Test
    fun shouldReturnTrueWhenNoArgumentsGiven() {
        Assert.assertTrue(validator.validate())
    }

}