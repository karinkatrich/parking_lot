package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.*

@Suppress("DEPRECATION")
class SpotByRegistrationStrategyTest(var service: ParkingLotService, var formatter: OutputFormatter, var validator: ArgumentsValidator) {

    private var strategy: SpotByRegistrationStrategy? = null

    @Before
    fun initMocks() {
        strategy = SpotByRegistrationStrategy(service, formatter, validator)
        `when`(validator!!.validate(anyVararg())).thenReturn(true)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallService() {
        strategy!!.process("KA-01-HH-1234")

        verify(service, times(1))?.findSpotByRegistrationNumber("KA-01-HH-1234")
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatter() {
        `when`(service!!.findSpotByRegistrationNumber("KA-01-HH-1234")).thenReturn(17)

        strategy!!.process("KA-01-HH-1234")

        verify(formatter, times(1))?.formatSpotNumberByRegistrationNumber(true, 17)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatterWithNegative() {
        `when`(service!!.findSpotByRegistrationNumber("KA-01-HH-1234")).thenReturn(-1)

        strategy!!.process("KA-01-HH-1234")

        verify(formatter, times(1))?.formatSpotNumberByRegistrationNumber(false, -1)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallValidator() {
        strategy!!.process("KA-01-HH-1234")
        verify(validator, times(1))?.validate("KA-01-HH-1234")
    }

    @Test(expected = IncorrectArgumentException::class)
    @Throws(IncorrectArgumentException::class)
    fun shouldThrowExceptionForIncorrectInput() {
        `when`(validator!!.validate(anyVararg())).thenReturn(false)
        strategy!!.process()
    }

}