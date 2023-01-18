package org.jesperancinha.coffee.system

import jakarta.xml.bind.JAXBException
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import java.io.FileNotFoundException

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
object CoffeeParadigmsMain {
    @Throws(CmdLineException::class, FileNotFoundException::class, JAXBException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val options = CoffeParadigmsOptions()
        val parser = CmdLineParser(options)
        parser.parseArgument(*args)
        options.run()
    }
}