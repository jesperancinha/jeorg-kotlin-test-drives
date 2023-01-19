package org.jesperancinha.coffee.system

import jakarta.xml.bind.JAXBException
import org.springframework.boot.autoconfigure.SpringBootApplication
import picocli.CommandLine
import java.io.FileNotFoundException
import kotlin.system.exitProcess

@SpringBootApplication
open class CoffeeParadigmsMain
/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Throws(FileNotFoundException::class, JAXBException::class)
fun main(args: Array<String>) {
    val options = CoffeeParadigmsOptions()
    val executionResultCode = CommandLine(options).execute(*args)
    exitProcess(executionResultCode)
}

//fun initAll() {
//    preProcessor.initExecutors()
//    coffeeProcessor.initExecutors()
//    paymentProcessor.initExecutors()
//    postProcessor.initExecutors()
//}