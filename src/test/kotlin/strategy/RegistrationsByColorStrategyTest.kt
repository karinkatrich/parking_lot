package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import java.util.Arrays

import org.mockito.Mockito.*

@Suppress("DEPRECATION")
class RegistrationsByColorStrategyTest(var service: ParkingLotService, var formatter: OutputFormatter, var validator: ArgumentsValidator) {

    private var strategy: RegistrationsByColorStrategy? = null

    @Before
    fun initMocks() {
        strategy = RegistrationsByColorStrategy(service, formatter, validator)
        `when`(validator!!.validate(ArgumentMatchers.anyVararg())).thenReturn(true)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallService() {
        strategy!!.process("White")

        verify(service, times(1))?.findRegistrationNumbersByColor("White")
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatter() {
        val expectedRegistrationNumbers = Arrays.asList("KA-01-HH-1234", "BRA-17-DL-1760", "IO-15-NO-9988")
        `when`(service!!.findRegistrationNumbersByColor("Green")).thenReturn(expectedRegistrationNumbers)

        strategy!!.process("Green")

        verify(formatter, times(1))?.formatRegistrationNumbersByColor(expectedRegistrationNumbers)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallValidator() {
        strategy!!.process("Green")
        verify(validator, times(1)).run { ("Green") }
    }

    @Test(expected = IncorrectArgumentException::class)
    @Throws(IncorrectArgumentException::class)
    fun shouldThrowExceptionForIncorrectInput() {
        `when`(validator!!.validate(anyVararg())).thenReturn(false)
        strategy!!.process()
    }

}