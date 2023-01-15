package com.jesperancinha.performance.inputgenerator

import org.slf4j.LoggerFactory
import picocli.CommandLine
import java.util.Random
import kotlin.system.exitProcess

object InputGeneratorLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        InputGeneratorOptions().filename?.let {
           exitProcess(CommandLine(InputGeneratorOptions()).execute(*args))
        }
    }

}