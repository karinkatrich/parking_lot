package model

 class Car(var color: String, var registrationNumber: String) {

    fun Car(registrationNumber: String, color: String) {
        this.registrationNumber = registrationNumber
        this.color = color
    }

    override fun toString(): String {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\''.toString() +
                ", color='" + color + '\''.toString() +
                '}'.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Car

        if (registrationNumber != other.registrationNumber) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = registrationNumber.hashCode()
        result = 31 * result + color.hashCode()
        return result
    }

}