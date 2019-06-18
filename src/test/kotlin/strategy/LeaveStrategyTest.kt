package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import org.mockito.Mockito.*

  @Suppress("DEPRECATION")
  class LeaveStrategyTest(var service: ParkingLotService, var formatter: OutputFormatter, var validator: ArgumentsValidator) {

    private var strategy: LeaveStrategy? = null

    @Before
    fun initMocks() {
        strategy = LeaveStrategy(service, formatter, validator)
        `when`(validator?.validate(ArgumentMatchers.anyVararg())).thenReturn(true)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallService() {
        val spotToLeave = 3

        strategy!!.process(spotToLeave.toString())
        verify(service, times(1))?.removeCar(spotToLeave)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatter() {
        val spotToLeave = 3

        `when`(service!!.removeCar(spotToLeave)).thenReturn(true)

        strategy!!.process(spotToLeave.toString())
        verify(formatter, times(1))?.formatLeaveCommandOutput(true, spotToLeave)
    }


    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallValidator() {
        val spotToLeave = "3"

        strategy!!.process(spotToLeave)
        verify(validator, times(1))?.validate(spotToLeave)
    }

    @Test(expected = IncorrectArgumentException::class)
    @Throws(IncorrectArgumentException::class)
    fun shouldThrowExceptionForIncorrectInput() {
        val spotToLeave = "a"
        `when`(validator!!.validate(spotToLeave)).thenReturn(false)

        strategy!!.process(spotToLeave)
        verify(validator, times(1))?.validate(spotToLeave)
    }

}