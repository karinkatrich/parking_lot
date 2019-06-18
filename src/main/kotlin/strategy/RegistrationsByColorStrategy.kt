package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator

class RegistrationsByColorStrategy(
    service: ParkingLotService,
    formatter: OutputFormatter,
    validator: ArgumentsValidator
) : AbstractStrategy(service, formatter, validator) {

    @Throws(IncorrectArgumentException::class)
    override fun process(vararg arguments: String): String {
        validate(*arguments)
        return formatter.formatRegistrationNumbersByColor(service.findRegistrationNumbersByColor(arguments[0]) as List<String>)
    }
}