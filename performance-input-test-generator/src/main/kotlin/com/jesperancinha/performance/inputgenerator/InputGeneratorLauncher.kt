package com.jesperancinha.performance.inputgenerator

import picocli.CommandLine
import kotlin.system.exitProcess

object InputGeneratorLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        CommandLine(InputGeneratorOptions()).execute(*args)
            .let { result ->
                if (result > 0) exitProcess(result) }
    }
}