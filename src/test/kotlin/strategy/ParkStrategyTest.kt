package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import model.Car
import service.ParkingLotService
import validator.ArgumentsValidator
import org.junit.Before
import org.junit.Test

import org.mockito.Matchers.any
import org.mockito.Mockito.*

class ParkStrategyTest(var service: ParkingLotService, var formatter: OutputFormatter, var validator: ArgumentsValidator) {

    private var strategy: ParkStrategy? = null

    @Before
    fun initMocks() {
        strategy = ParkStrategy(service, formatter, validator)
        `when`(validator!!.validate(anyVararg())).thenReturn(true)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallService() {
        val car = Car("KA-01-HH-1234", "White")

        strategy!!.process(REGISTRATION_NUMBER, COLOR)
        verify(service, times(1))?.parkCar(car)

    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatterWithSuccessfulResult() {
        val expectedCar = Car("KA-01-HH-1234", "White")

        `when`(service!!.parkCar(expectedCar)).thenReturn(1)
        strategy!!.process(REGISTRATION_NUMBER, COLOR)
        verify(formatter, times(1))?.formatParkCommandOutput(true, 1)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatterWithFailureResult() {
        `when`(service!!.parkCar(any(Car::class.java))).thenReturn(-1)
        strategy!!.process(REGISTRATION_NUMBER, COLOR)
        verify(formatter, times(1))?.formatParkCommandOutput(false, -1)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallValidator() {
        val expectedCar = Car("KA-01-HH-1234", "White")

        `when`(service!!.parkCar(expectedCar)).thenReturn(1)
        strategy!!.process(REGISTRATION_NUMBER, COLOR)
        verify(formatter, times(1))?.formatParkCommandOutput(true, 1)
    }

    @Test(expected = IncorrectArgumentException::class)
    @Throws(IncorrectArgumentException::class)
    fun shouldThrowExceptionForIncorrectInput() {
        `when`(validator!!.validate(anyVararg())).thenReturn(false)

        strategy!!.process("123abc")
    }

    companion object {
        private val REGISTRATION_NUMBER = "KA-01-HH-1234"
        private val COLOR = "White"
    }

}