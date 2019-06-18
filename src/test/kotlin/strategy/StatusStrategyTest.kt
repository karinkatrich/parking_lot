package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import model.Car
import model.ParkingSpot
import service.ParkingLotService
import validator.ArgumentsValidator
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import org.mockito.Mockito.*

@Suppress("DEPRECATION")
class StatusStrategyTest(var service: ParkingLotService, var formatter: OutputFormatter, var validator: ArgumentsValidator) {

    private var strategy: StatusStrategy? = null

    @Before
    fun initMocks() {
        strategy = StatusStrategy(
            service, formatter,validator
        )
        `when`(validator!!.validate(anyVararg())).thenReturn(true)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallService() {
        strategy!!.process()

        verify(service, times(1))?.getParkingStatus()
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallFormatter() {
        val parkingSpots = generateParkingSpot()
        `when`(service!!.getParkingStatus()).thenReturn(parkingSpots)

        strategy!!.process()

        verify(formatter, times(1))?.formatStatusCommand(parkingSpots)
    }

    @Test
    @Throws(IncorrectArgumentException::class)
    fun shouldCallValidator() {
        strategy!!.process()
        verify(validator, times(1))?.validate()
    }

    @Test(expected = IncorrectArgumentException::class)
    @Throws(IncorrectArgumentException::class)
    fun shouldThrowExceptionForIncorrectInput() {
        `when`(validator.validate(ArgumentMatchers.anyVararg())).thenReturn(false)
        strategy?.process()
    }

    private fun generateParkingSpot(): List<ParkingSpot> {
        val car = Car("KA-01-HH-1234", "White")
        val spot = ParkingSpot(1)
        spot.setCar(car)
        return listOf<ParkingSpot>(spot)
    }

}