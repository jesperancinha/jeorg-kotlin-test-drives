package com.jesperancinha.performance.inputgenerator

import picocli.CommandLine

object InputGeneratorLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        CommandLine(InputGeneratorOptions()).execute(*args)
    }
}