package org.jesperancinha.string.paradigm.inputgenerator

import picocli.CommandLine
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    CommandLine(InputGeneratorOptions()).execute(*args)
        .let { result-> if(result>0) exitProcess(result)  }
}
