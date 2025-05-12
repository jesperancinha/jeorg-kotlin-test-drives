package com.jesperancinha.performance.inputgenerator

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.Callable

@Command(
    name = "Input Generator", mixinStandardHelpOptions = true, version = ["0.0.0"],
    description = ["This is an input generator command"]
)
class InputGeneratorOptions : Callable<Int> {
    private val random = Random()

    @Option(names = ["-n", "--number-of-elements"], required = true)
    var numberOfElements = 0

    @Option(names = ["-d", "--directory"], required = false)
    var folder = "/tmp"

    @Option(names = ["-f", "--filename"], required = true)
    var filename: String? = null

    @Option(names = ["-i", "--increments"], required = false)
    var increments = 5
    override fun call(): Int = try {
        filename?.let {
            createGenerationFile(
                numberOfElements, folder, it,
                increments
            )
        }
        0
    } catch (ex: Exception) {
        1
    }


    @Throws(IOException::class)
    private fun createGenerationFile(numberOfElements: Int, folder: String, fileName: String, maxIncrements: Int) {
        val directory = File(folder)
        directory.mkdirs()
        val file = File(directory, fileName)
        logger.info("Directory:$directory")
        logger.info("Filename:$fileName")
        file.createNewFile()
        BufferedOutputStream(FileOutputStream(file)).use { bo ->
            var currentNumber = getNextNumber(0, maxIncrements)
            bo.write(toBytes("" + currentNumber))
            for (i in numberOfElements downTo 2) {
                currentNumber = getNextNumber(currentNumber, maxIncrements)
                bo.write(getFormattedNumber(currentNumber))
            }
        }
        logger.info(
            "Completed successfully the generation of {} test element file in {}/{}",
            numberOfElements, folder, fileName
        )
    }

    private fun getFormattedNumber(currentNumber: Int) = toBytes(", $currentNumber")

    private fun toBytes(string: String): ByteArray = string.toByteArray(Charset.defaultCharset())

    private fun getNextNumber(current: Int, increments: Int): Int =
        current + random.nextInt(increments)

    companion object {
        val logger: Logger = LoggerFactory.getLogger(InputGeneratorOptions::class.java)
    }
}