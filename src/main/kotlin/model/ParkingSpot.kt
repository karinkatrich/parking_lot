package model

import java.util.*

 class ParkingSpot(var number: Int) {
     private lateinit var car: Car

     fun setParkingSpot(number: Int) {
        this.number = number
    }

    fun setCar(car: Car) {
        this.car = car
    }

    fun getCar(): Car? {
        return car
    }

    override fun toString(): String {
        return "ParkingSpot{" +
                "number=" + number +
                ", car=" + car +
                '}'.toString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ParkingSpot?
        return number == that!!.number && car == that.car
    }

    override fun hashCode(): Int {

        return Objects.hash(number, car)
    }

    fun removeCar() {
        this.car == null
    }
}

