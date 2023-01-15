package com.jesperancinha.performance.inputgenerator

import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import java.util.logging.Logger

object InputGenerator {
    private val logger = Logger.getLogger(InputGenerator::class.java.name)
    private val random = Random()
    @JvmStatic
	@Throws(CmdLineException::class, IOException::class)
    fun main(args: Array<String>) {
        val options = InputGeneratorOptions()
        val parser = CmdLineParser(options)
        parser.parseArgument(*args)
        options.filename?.let {
            createGenerationFile(
                options.numberOfElements, options.folder, it,
                options.increments
            )
        }
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
            String.format(
                "Completed successfully the generation of %s test element file in %s/%s",
                numberOfElements, folder, fileName
            )
        )
    }

    private fun getFormattedNumber(currentNumber: Int)= toBytes(", $currentNumber")

    private fun toBytes(string: String): ByteArray = string.toByteArray(Charset.defaultCharset())

    private fun getNextNumber(current: Int, increments: Int): Int = current + random.nextInt(increments)
}