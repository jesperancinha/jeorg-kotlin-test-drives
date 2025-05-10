package org.jesperancinha.xml.adder

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.ParserConfigurationException

object XmlAdderMain {
    @Throws(IOException::class, ParserConfigurationException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val sourceDirectory = args[0]
        val destinationDirectory = args[1]
        val addAttributesFile = args[2]
        val deleteAttributesFile = args[3]
        val rule = args[4]
        val fileSourceDirectory = File(sourceDirectory)
        val fileDestinationDirectory = File(destinationDirectory)
        val fileAddAtributesFile: InputStream = FileInputStream(File(addAttributesFile))
        val fileDeleteAttributesFile: InputStream = FileInputStream(File(deleteAttributesFile))
        val fileRule: InputStream = FileInputStream(File(rule))
        XmlAdderManager(
            fileSourceDirectory,
            fileDestinationDirectory,
            fileAddAtributesFile,
            fileDeleteAttributesFile,
            fileRule
        )
    }
}