import controller.CommandController
import java.io.*

class ParkingLotApplication {

    private val INPUT_ERROR = "Invalid input, parking not initialized"
    private val INIT_COMMAND = "create_parking_lot"
    private val CREATED_PARKING_MESSAGE = "Created a parking lot with %d slots"
    private val OUTPUT_FILE_NAME = "output.txt"

    private var writer: PrintWriter? = null
    private var reader: BufferedReader? = null
    private var processor: CommandController? = null


    fun main(args: Array<String>) {
        val parkingLot = ParkingLotApplication()
        try {
            parkingLot.initializeReaderAndWriter(args)
            parkingLot.initializeProcessor()
            parkingLot.processInstructions()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @Throws(IOException::class)
    private fun processInstructions() {
        if (processor != null) {
            val reader = BufferedReader(reader)
            var line: String? = null
            while (! ({line = reader.readLine()} != null && line?.isEmpty() !!)) {
                writer!!.println(line?.let { processor!!.evaluate(it) })
                writer!!.flush()
            }
        }
    }

    @Throws(IOException::class)
    private fun initializeProcessor() {
        val initArguments = reader!!.readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (initArguments.size == 2) {
            val command = initArguments[0]
            val capacity = Integer.parseInt(initArguments[1])
            if (INIT_COMMAND == command) {
                processor = CommandController(capacity)
                writer!!.println(String.format(CREATED_PARKING_MESSAGE, capacity))
                writer!!.flush()
                return
            }
        }
        writer!!.println(INPUT_ERROR)
        writer!!.flush()
    }

    @Throws(IOException::class)
    private fun initializeReaderAndWriter(args: Array<String>) {
        if (args.size == 0) {
            reader = BufferedReader(InputStreamReader(System.`in`))
            writer = PrintWriter(System.out, true)
            writer!!.println("Welcome to parking lot application. Please initialize parking using command 'create_parking_lot N', where N is maximum capacity. In order to terminate - provide empty line.")
        } else {
            val inputFile = File(args[0])
            reader = BufferedReader(FileReader(inputFile))
            writer = PrintWriter(FileWriter(inputFile.parent + System.getProperty("file.separator") + OUTPUT_FILE_NAME))
        }
    }

}