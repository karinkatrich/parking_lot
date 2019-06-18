package service

import model.Car
import model.ParkingSpot
import org.junit.Before
import org.junit.Test
import org.junit.Assert
import java.util.ArrayList
import java.util.Comparator
import java.util.TreeSet


class ParkingLotServiceTest {
    private var service: ParkingLotService? = null

    @Before
    fun setUp() {
        service = ParkingLotService(initializeParkingSpots(DEFAULT_LOT_CAPACITY))
    }

    @Test
    fun shouldParkCarAtFirstSpot() {
        val car = Car("AA1716OH", "Black")
        Assert.assertEquals(1, service!!.parkCar(car))
    }

    @Test
    fun shouldAddCarToParkingSpot() {
        val car = Car("AA1716OH", "Black")
        val spot = ParkingSpot(1)
        spot.setCar(car)
        service!!.parkCar(car)

        Assert.assertTrue(service!!.getParkingStatus().contains(spot))
    }

    @Test
    fun shouldParkMultipleCars() {
        val firstCar = Car("AA123", "Black")
        val secondCar = Car("BB456", "Grey")

        Assert.assertEquals(1, service!!.parkCar(firstCar))
        Assert.assertEquals(2, service!!.parkCar(secondCar))
    }

    @Test
    fun shouldNotParkCarWhenNoFreeSpotsAvailable() {
        service = ParkingLotService(initializeParkingSpots(1))

        val firstCar = Car("AA123", "Black")
        service!!.parkCar(firstCar)

        val secondCar = Car("BB456", "Grey")
        Assert.assertEquals(-1, service!!.parkCar(secondCar))
    }

    @Test
    fun shouldReturnFalseIfCarWasNotRemoved() {
        Assert.assertFalse(service!!.removeCar(255))
    }

    @Test
    fun shouldUseClosestAvailableSpotWhenParking() {
        parkCars(3, "Black")

        service!!.removeCar(2)

        val forthCar = Car("DD963", "Black")
        Assert.assertEquals(2, service!!.parkCar(forthCar))
    }

    @Test
    fun shouldNotFindSlotByRegistrationNumberAfterCarWasRemoved() {
        val car = Car("321", "Grey")
        val slotNumber = service!!.parkCar(car)
        service!!.removeCar(slotNumber)

        Assert.assertEquals(-1, service!!.findSpotByRegistrationNumber("321"))
    }

    private fun parkCars(amountOfCars: Int, color: String): List<Car> {
        val createdCars = ArrayList<Car>()
        for (i in 0 until amountOfCars) {
            val car = Car("KA-01-HH-123$i", color)
            service!!.parkCar(car)
            createdCars.add(car)
        }
        return createdCars
    }

    private fun initializeParkingSpots(lotCapacity: Int): TreeSet<ParkingSpot> {
        val parkingSpots = TreeSet<ParkingSpot>(Comparator.comparingInt { parkingSpot -> parkingSpot.number })
        for (i in 1..lotCapacity) {
            parkingSpots.add(ParkingSpot(i))
        }
        return parkingSpots
    }

    companion object {

        private val DEFAULT_LOT_CAPACITY = 3
    }
}