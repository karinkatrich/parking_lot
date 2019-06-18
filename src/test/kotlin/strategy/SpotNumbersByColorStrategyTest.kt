package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator
import org.junit.Before
import org.junit.Test

import java.util.Arrays

import org.mockito.Mockito.*

@Suppress("DEPRECATION")
class SpotNumbersByColorStrategyTest(var service: ParkingLotService, var formatter: OutputFormatter, var validator: ArgumentsValidator) {

    private var strategy: SpotNumbersByColorStrategy? = null

    @Before
    fun initMocks() {
        strategy = SpotNumbersByColorStrategy(service, formatter, validator)
        `when`(validator!!.validate(anyVararg())).thenReturn(true)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallService() {
        strategy!!.process("White")

        verify(service, times(1)).findSpotsByColor("White")
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatter() {
        val expectedSpots = Arrays.asList(1, 2, 4)
        `when`(service!!.findSpotsByColor("Green")).thenReturn(expectedSpots)

        strategy!!.process("Green")

        verify(formatter, times(1))?.formatSpotNumbersByColor(expectedSpots)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallValidator() {
        strategy!!.process("Green")
        verify(validator, times(1))?.validate("Green")
    }

    @Test(expected = IncorrectArgumentException::class)
    @Throws(IncorrectArgumentException::class)
    fun shouldThrowExceptionForIncorrectInput() {
        `when`(validator!!.validate(anyVararg())).thenReturn(false)
        strategy!!.process()
    }

}