package formatter

import model.Car
import model.ParkingSpot
import org.junit.Assert
import org.junit.Test

import java.util.Arrays

class DefaultOutputFormatterTest {

    private val formatter = OutputFormatter()

    @Test
    fun shouldFormatParkCommandSuccessfulCase() {
        val expectedOutput = "Allocated slot number: 15"

        Assert.assertEquals(expectedOutput, this.formatter.formatParkCommandOutput(true, 15))
    }

    @Test
    fun shouldFormatParkCommandFailedCase() {
        val expectedOutput = "Sorry, parking lot is full"

        Assert.assertEquals(expectedOutput, formatter.formatParkCommandOutput(false, -123))
    }

    @Test
    fun shouldFormatLeaveCommandSuccessfulCase() {
        val expectedOutput = "Slot number 1 is free"

        Assert.assertEquals(expectedOutput, formatter.formatLeaveCommandOutput(true, 1))
    }

    @Test
    fun shouldFormatLeaveCommandFailureCase() {
        val expectedOutput = "No car on this spot"

        Assert.assertEquals(expectedOutput, formatter.formatLeaveCommandOutput(false, 119))
    }

    @Test
    fun shouldFormatRegistrationNumbersByColorSuccessfulCase() {
        val registrationNumbers = Arrays.asList("KA-01-HH-1234", "BRA-17-DL-1760", "IO-15-NO-9988")
        val expectedOutput = "KA-01-HH-1234, BRA-17-DL-1760, IO-15-NO-9988"

        Assert.assertEquals(expectedOutput, formatter.formatRegistrationNumbersByColor(registrationNumbers))
    }

    @Test
    fun shouldFormatRegistrationNumbersByColorFailureCase() {
        val registrationNumbers = emptyList<String>()
        val expectedOutput = "Not found"

        Assert.assertEquals(expectedOutput, formatter.formatRegistrationNumbersByColor(registrationNumbers))
    }

    @Test
    fun shouldFormatSpotNumbersByColorSuccessfulCase() {
        val registrationNumbers = Arrays.asList(1, 2, 3, 6)
        val expectedOutput = "1, 2, 3, 6"

        Assert.assertEquals(expectedOutput,formatter.formatSpotNumbersByColor(registrationNumbers))
    }

    @Test
    fun shouldFormatSpotNumbersByColorFailureCase() {
        val registrationNumbers = emptyList<Int>()
        val expectedOutput = "Not found"

        Assert.assertEquals(expectedOutput, formatter.formatSpotNumbersByColor(registrationNumbers))
    }

    @Test
    fun shouldFormatSpotNumbersByRegistrationNumberSuccessfulCase() {
        Assert.assertEquals("19", formatter.formatSpotNumberByRegistrationNumber(true, 19))
    }

    @Test
    fun shouldFormatSpotNumbersByRegistrationNumberFailureCase() {
        Assert.assertEquals("Not found", formatter.formatSpotNumberByRegistrationNumber(false, 1))
    }

    @Test
    fun shouldFormatStatusForEmptyParking() {
        val expectedParkingSpots = emptyList<ParkingSpot>()

        val expectedOutput = "No cars on the parking"

        Assert.assertEquals(expectedOutput, formatter.formatStatusCommand(expectedParkingSpots))
    }

    private fun generateParkingSpots(): List<ParkingSpot> {
        val firstCar = Car("KA-01-HH-1234", "White")
        val secondCar = Car("KA-01-HH-9999", "White")
        val thirdCar = Car("KA-01-HH-9999", "Black")

        val firstSpot = ParkingSpot(1)
        val secondSpot = ParkingSpot(2)
        val thirdSpot = ParkingSpot(3)

        firstSpot.setCar(firstCar)
        secondSpot.setCar(secondCar)
        thirdSpot.setCar(thirdCar)

        return Arrays.asList<ParkingSpot>(firstSpot, secondSpot, thirdSpot)
    }

}