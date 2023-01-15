package com.jesperancinha.performance.inputgenerator

import picocli.CommandLine
import kotlin.system.exitProcess

object InputGeneratorLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        InputGeneratorOptions().filename?.let {
           exitProcess(CommandLine(InputGeneratorOptions()).execute(*args))
        }
    }

}