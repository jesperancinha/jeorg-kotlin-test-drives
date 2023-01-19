package org.jesperancinha.coffee.system

import jakarta.xml.bind.JAXBException
import picocli.CommandLine
import java.io.FileNotFoundException
import kotlin.system.exitProcess

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Throws(FileNotFoundException::class, JAXBException::class)
fun main(args: Array<String>) {
    val options = CoffeeParadigmsOptions()
    val executionResultCode = CommandLine(options).execute(*args)
    exitProcess(executionResultCode)
}