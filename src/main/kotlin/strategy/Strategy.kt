package strategy

import exception.IncorrectArgumentException
import kotlin.String as String1

interface Strategy {
    @Throws(IncorrectArgumentException::class)
    fun process(vararg arguments: String1): String1
}