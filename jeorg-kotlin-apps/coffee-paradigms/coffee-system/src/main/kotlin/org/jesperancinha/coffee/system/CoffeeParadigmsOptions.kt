package org.jesperancinha.coffee.system

import jakarta.xml.bind.JAXBException
import org.jesperancinha.coffee.system.manager.GeneralProcessor
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.FileNotFoundException
import java.util.concurrent.Callable

/**
 * Created by joao on 29-4-16.
 */
@Command(
    name = "coffee-paradigms", mixinStandardHelpOptions = true, version = ["0.0.0"],
    description = ["Coffee environment simulator"]
)
class CoffeeParadigmsOptions : Callable<Int> {
    @Option(
        names = ["-it", "--iterations"],
        description = ["Define the number of iterations you want to make"],
        required = true
    )
    private var nIterations = 1

    @Option(
        names = ["-ud", "--userdefinitions"],
        description = ["Defines where the user definition file is"],
        required = true
    )
    private var userDefinitionFile: String? = null

    @Option(
        names = ["-md", "--machinedefinitions"],
        description = ["Defines where the machine definition file is"],
        required = true
    )
    private var machineDefinitionFile: String? = null

    @Option(
        names = ["-pre", "--pre-actions-size"],
        description = ["Defines how many can stand on the pre-actions queue"],
        required = true
    )
    private var nPreActions: Int? = null

    @Option(
        names = ["-post", "--post-actions-size"],
        description = ["Defines how many can stand on the post-actions queue"],
        required = true
    )
    private var nPostActions: Int? = null

    @Throws(FileNotFoundException::class, JAXBException::class)
    fun run() {
        val context: ApplicationContext =
            SpringApplication.run(CoffeeParadigmsMain::class.java, *emptyArray())
        val generalProcessor = context.getBean(GeneralProcessor::class.java)
        generalProcessor.nIterations = nIterations
        generalProcessor.sourceXmlEmployeesFile =
            userDefinitionFile ?: throw RuntimeException("Please specify a --userdefinitions <file>")
        generalProcessor.sourceXmlMachinesFile =
            machineDefinitionFile ?: throw RuntimeException("Please specify a --machinedefinition <file>")
        generalProcessor.preRowSize = nPreActions ?: 0
        generalProcessor.postRowSize = nPostActions ?: 0
        generalProcessor.initSimulationProcess()
        generalProcessor.start()
    }

    override fun call(): Int = run().let { 0 }
}