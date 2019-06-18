package service

import model.Car
import model.ParkingSpot

import java.util.*
import java.util.stream.Collectors
import java.util.LinkedList as LinkedList1

class ParkingLotService(private val freeSpots: TreeSet<ParkingSpot>) {
    private val spotNumberToSpotMap: HashMap<Int, ParkingSpot>
    private val registrationNumberToSpotMap: HashMap<String, ParkingSpot>

    init {
        spotNumberToSpotMap = HashMap<Int, ParkingSpot>()
        registrationNumberToSpotMap = HashMap<String, ParkingSpot>()
    }

    fun parkCar(car: Car?): Int {
        val freeSpot = freeSpots.pollFirst()
        if (freeSpot == null || car == null) {
            return -1
        }
        freeSpot!!.setCar(car)
        spotNumberToSpotMap[freeSpot!!.number] = freeSpot
        registrationNumberToSpotMap[car!!.registrationNumber] = freeSpot
        return freeSpot!!.number
    }

    fun getParkingStatus(): List<ParkingSpot> {
        return java.util.LinkedList<ParkingSpot>(spotNumberToSpotMap.values)
    }

    fun removeCar(spotNumber: Int): Boolean {
        val spot = spotNumberToSpotMap[spotNumber]
        if (spot != null && spot!!.getCar() != null) {
            registrationNumberToSpotMap.remove(spot!!.getCar()?.registrationNumber)
            spot!!.removeCar()
            spotNumberToSpotMap.remove(spotNumber)
            freeSpots.add(spot)
            return true
        }
        return false
    }

    fun findRegistrationNumbersByColor(color: String): List<String?> {
        return spotNumberToSpotMap.values.stream().filter { parkingSpot -> isSameColor(color, parkingSpot) }
            .map { parkingSpot -> parkingSpot.getCar() }.collect(Collectors.toList())
            .map { car -> car?.registrationNumber }
    }

    fun findSpotsByColor(color: String): List<Int> {
        return spotNumberToSpotMap.values.stream().filter { parkingSpot -> isSameColor(color, parkingSpot) }
            .map {parkingSpot -> parkingSpot.number }.collect(Collectors.toList())
    }

    fun findSpotByRegistrationNumber(registrationNumber: String): Int {
        val spot = registrationNumberToSpotMap[registrationNumber]
        return if (spot != null) spot!!.number else -1
    }

    private fun isSameColor(color: String, parkingSpot: ParkingSpot): Boolean {
        return parkingSpot.getCar() != null && color == parkingSpot.getCar()!!.color
    }
}

