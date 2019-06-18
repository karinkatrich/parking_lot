package strategy

import exception.IncorrectArgumentException
import formatter.OutputFormatter
import service.ParkingLotService
import validator.ArgumentsValidator

abstract class AbstractStrategy(
     var service: ParkingLotService,
     var formatter: OutputFormatter,
     var validator: ArgumentsValidator
) : Strategy {

    @Throws(IncorrectArgumentException::class)
    fun validate(vararg arguments: String) {
        if (!validator.validate(*arguments)) {
            throw IncorrectArgumentException(String.format(INVALID_ARGUMENTS_MESSAGE, arguments as Any))
        }
    }

    @Throws(IncorrectArgumentException::class)
    abstract override fun process(vararg arguments: String): String

    companion object {

        private val INVALID_ARGUMENTS_MESSAGE = "Invalid arguments [%s] for command"
    }


}