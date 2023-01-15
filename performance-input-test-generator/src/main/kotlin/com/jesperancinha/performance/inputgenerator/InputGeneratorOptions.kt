package com.jesperancinha.performance.inputgenerator

import org.kohsuke.args4j.Option
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "Input Generator", mixinStandardHelpOptions = true, version = ["0.0.0"],
    description = ["This is an input generator command"]
)
class InputGeneratorOptions :Callable<Int> {
    @Option(name = "-n", aliases = ["--number-of-elements"], required = true)
    var numberOfElements = 0

    @Option(name = "-d", aliases = ["--directory"], required = false)
    var folder = "/tmp"

    @Option(name = "-f", aliases = ["--filename"], required = true)
    var filename: String? = null

    @Option(name = "-i", aliases = ["--increments"], required = false)
    var increments = 5
    override fun call(): Int {
        TODO("Not yet implemented")
    }
}