package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator

class LeaveStrategy(service: ParkingLotService, formatter: OutputFormatter, validator: ArgumentsValidator) :
    AbstractStrategy(service, formatter, validator) {

    @Throws(IncorrectArgumentException::class)
    override fun process(vararg arguments: String): String {
        validate(*arguments)
        val spotNumber = Integer.parseInt(arguments[0])
        return formatter.formatLeaveCommandOutput(service.removeCar(spotNumber), spotNumber)
    }
}