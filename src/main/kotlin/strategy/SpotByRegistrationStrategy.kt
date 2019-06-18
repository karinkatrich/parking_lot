package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator

class SpotByRegistrationStrategy(
    service: ParkingLotService,
    formatter: OutputFormatter,
    validator: ArgumentsValidator
) : AbstractStrategy(service, formatter, validator) {

    @Throws(IncorrectArgumentException::class)
    override fun process(vararg arguments: String): String {
        validate(*arguments)
        val foundSpot = service.findSpotByRegistrationNumber(arguments[0])
        return formatter.formatSpotNumberByRegistrationNumber(foundSpot > 0, foundSpot)
    }
}