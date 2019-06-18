package formatter

import model.ParkingSpot
import java.util.*

class OutputFormatter {
    private val PARKING_SUCCESSFUL = "Allocated slot number: %d"
    private val PARKING_FAILURE = "Sorry, parking lot is full"

    private val LEFT_SUCCESSFUL = "Slot number %d is free"
    private val LEAVE_FAILURE = "No car on this spot"

    private val NOT_FOUND = "Not found"

    private val STATUS_HEADER = "Slot No. Registration No. Color"
    private val STATUS_ROW = System.lineSeparator() + "%d\t%s\t%s"
    private val STATUS_EMPTY = "No cars on the parking"

    fun formatParkCommandOutput(parkedSuccessfully: Boolean, spotNumber: Int): String {
        return if (parkedSuccessfully) String.format(PARKING_SUCCESSFUL, spotNumber) else PARKING_FAILURE
    }

    fun formatLeaveCommandOutput(leftSuccessfully: Boolean, spotNumber: Int): String {
        return if (leftSuccessfully) String.format(LEFT_SUCCESSFUL, spotNumber) else LEAVE_FAILURE
    }

    fun formatRegistrationNumbersByColor(registrationNumbers: List<String>): String {
        return if (registrationNumbers.isEmpty()) NOT_FOUND else formatList(registrationNumbers)
    }

    fun formatSpotNumbersByColor(spotNumbers: List<Int>): String {
        return if (spotNumbers.isEmpty()) NOT_FOUND else formatList(spotNumbers)
    }

    fun formatSpotNumberByRegistrationNumber(found: Boolean, spotNumber: Int): String {
        return if (found) spotNumber.toString() else NOT_FOUND
    }

    fun formatStatusCommand(occupiedSpots: List<ParkingSpot>): String {
        if (!occupiedSpots.isEmpty()) {
            val builder = StringBuilder()
            builder.append(STATUS_HEADER)
            for (occupiedSpot in occupiedSpots) {
                builder.append(formatStatusRow(occupiedSpot))
            }
            return builder.toString()
        }
        return STATUS_EMPTY
    }

    private fun formatStatusRow(occupiedSpot: ParkingSpot): String {
        return String.format(
            STATUS_ROW,
            occupiedSpot.number,
            occupiedSpot.getCar()?.registrationNumber,
            occupiedSpot.getCar()?.color
        )
    }

    private fun <T> formatList(registrationNumbers: List<T>): String {
        val joiner = StringJoiner(", ")
        for (t in registrationNumbers) {
            joiner.add(t.toString())
        }
        return joiner.toString()
    }


}