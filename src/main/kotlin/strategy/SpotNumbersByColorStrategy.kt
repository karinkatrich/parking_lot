package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator

class SpotNumbersByColorStrategy(
    service: ParkingLotService,
    formatter: OutputFormatter,
    validator: ArgumentsValidator
) : AbstractStrategy(service, formatter, validator) {

    @Throws(IncorrectArgumentException::class)
    override fun process(vararg arguments: String): String {
        validate(*arguments)
        return formatter.formatSpotNumbersByColor(service.findSpotsByColor(arguments[0]))
    }
}