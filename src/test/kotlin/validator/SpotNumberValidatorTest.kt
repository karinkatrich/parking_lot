package validator

import org.junit.Test
import org.junit.Assert

public class SpotNumberValidatorTest {

    private val PARKING_MAXIMUM_CAPACITY = 15
    private val validator = SpotNumberValidator(PARKING_MAXIMUM_CAPACITY)

    @Test
    fun shouldSucceedForCorrectInput() {
        Assert.assertTrue(validator.validate("1"))
        Assert.assertTrue(validator.validate("15"))
        Assert.assertTrue(validator.validate("8"))
    }

    @Test
    fun shouldFailWhenNumberBiggerThanMaximumCapacity() {
        Assert.assertFalse(validator.validate("16"))
    }

    @Test
    fun shouldFailOnTooBigNumber() {
        Assert.assertFalse(validator.validate("1234567"))
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