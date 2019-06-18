package controller

import service.ParkingLotService
import exception.IncorrectArgumentException
import formatter.OutputFormatter
import model.Command
import model.ParkingSpot
import strategy.*
import validator.*
import java.util.*
import java.util.Comparator.comparingInt as comparingInt1

class CommandController(private val parkingSize: Int) {

    private val ARGUMENT_DELIMITER = " "
    private val INVALID_COMMAND_MESSAGE = "Command %s is not supported"
    private val INPUT_ERROR = "Invalid input, parking not initialized"

    private val strategies: MutableMap<Command, Strategy>
    private val formatter: OutputFormatter
    private val service: ParkingLotService

    init {
        strategies = HashMap<Command, Strategy>()
        formatter = OutputFormatter()
        service = ParkingLotService(initializeParkingSpots(parkingSize))
        initializeStrategies()
    }

    fun evaluate(line: String): String? {
        val arguments = line.split(ARGUMENT_DELIMITER.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val commandString = arguments[0]
        try {
            val command = Command.valueOf(commandString.toUpperCase())
            val argumentsWithoutCommand = Arrays.copyOfRange(arguments, 1, arguments.size)
            return strategies[command]?.process(*argumentsWithoutCommand)
        } catch (e: IllegalArgumentException) {
            return String.format(INVALID_COMMAND_MESSAGE, commandString)
        } catch (e: IncorrectArgumentException) {
            return e.message.toString()
        }

    }

    private fun initializeStrategies() {
        initializeParkStrategy()
        initializeLeaveStrategy()
        initializeStatusStrategy()
        initializeRegistrationsByColorStrategy()
        initializeSpotsByColorStrategy()
        initializeSpotByRegistrationStrategy()
    }

    private fun initializeParkStrategy() {
        val validator = CompositeRegistrationAndColorValidator()
        strategies[Command.PARK] = ParkStrategy(service, formatter, validator)
    }

    private fun initializeLeaveStrategy() {
        val validator = SpotNumberValidator(parkingSize)
        strategies[Command.LEAVE] = LeaveStrategy(service, formatter, validator)
    }

    private fun initializeStatusStrategy() {
        val validator = NoArgumentsValidator()
        strategies[Command.STATUS] = StatusStrategy(service, formatter, validator)
    }

    private fun initializeRegistrationsByColorStrategy() {
        val validator = ColorValidator()
        strategies[Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR] =
            RegistrationsByColorStrategy(service, formatter, validator)
    }

    private fun initializeSpotsByColorStrategy() {
        val validator = ColorValidator()
        strategies[Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOR] = SpotNumbersByColorStrategy(service, formatter, validator)
    }

    private fun initializeSpotByRegistrationStrategy() {
        val validator = RegistrationNumberValidator()
        strategies[Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER] = SpotByRegistrationStrategy(service, formatter, validator)
    }

    private fun initializeParkingSpots(lotCapacity: Int): TreeSet<ParkingSpot> {
        val parkingSpots = TreeSet<ParkingSpot>(comparingInt1 { parkingSpot -> parkingSpot.number })
        for (i in 1..lotCapacity) {
            parkingSpots.add(ParkingSpot(i))
        }
        return parkingSpots
    }
}