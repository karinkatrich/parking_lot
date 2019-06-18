package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import model.Car
import service.ParkingLotService
import validator.ArgumentsValidator

class ParkStrategy(service: ParkingLotService, formatter: OutputFormatter, validator: ArgumentsValidator) :
    AbstractStrategy(service, formatter, validator) {

    @Throws(IncorrectArgumentException::class)
    override fun process(vararg parkCommandArguments: String): String {
        validate(parkCommandArguments.toString())
        val car = Car(parkCommandArguments[0], parkCommandArguments[1])
        val spotNumber = service.parkCar(car)

        return formatter.formatParkCommandOutput(spotNumber > 0, spotNumber)
    }
}