package org.jesperancinha.coffee.system

import jakarta.xml.bind.JAXBException
import org.jesperancinha.coffee.system.manager.GeneralProcessorImpl
import org.kohsuke.args4j.Option
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.stereotype.Component
import java.io.FileNotFoundException

/**
 * Created by joao on 29-4-16.
 */
@Component
class CoffeeParadigmsOptions {
    @Option(
        name = "-it",
        usage = "Define the number of iterations you want to make",
        aliases = ["--iterations"],
        required = true
    )
    private val nIterations = 1

    @Option(
        name = "-ud",
        usage = "Defines where the user definition file is",
        aliases = ["--userdefinitions"],
        required = true
    )
    private val userDefinitionFile: String? = null

    @Option(
        name = "-md",
        usage = "Defines where the machine definition file is",
        aliases = ["--machinedefinitions"],
        required = true
    )
    private val machineDefinitionFile: String? = null

    @Option(
        name = "-pre",
        usage = "Defines how many can stand on the pre-actions queue",
        aliases = ["--pre-actions-size"],
        required = true
    )
    private val nPreActions: Int? = null

    @Option(
        name = "-post",
        usage = "Defines how many can stand on the post-actions queue",
        aliases = ["--post-actions-size"],
        required = true
    )
    private val nPostActions: Int? = null

    @Throws(FileNotFoundException::class, JAXBException::class)
    fun run() {
        val context: ApplicationContext = ClassPathXmlApplicationContext("META-INF/config.xml")
        val generalProcessor = context.getBean(GeneralProcessorImpl::class.java)
        generalProcessor.nIterations = nIterations
        generalProcessor.sourceXmlEmployeesFile = userDefinitionFile
        generalProcessor.sourceXmlMachinesFile = machineDefinitionFile
        generalProcessor.preRowSize = nPreActions ?: 0
        generalProcessor.postRowSize = nPostActions ?: 0
        generalProcessor.initSimulationProcess()
        generalProcessor.start()
    }
}