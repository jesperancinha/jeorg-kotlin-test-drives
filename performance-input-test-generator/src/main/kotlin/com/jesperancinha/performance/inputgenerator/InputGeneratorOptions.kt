package com.jesperancinha.performance.inputgenerator

import org.kohsuke.args4j.Option

class InputGeneratorOptions {
    @Option(name = "-n", aliases = ["--number-of-elements"], required = true)
    var numberOfElements = 0

    @Option(name = "-d", aliases = ["--directory"], required = false)
    var folder = "/tmp"

    @Option(name = "-f", aliases = ["--filename"], required = true)
    var filename: String? = null

    @Option(name = "-i", aliases = ["--increments"], required = false)
    var increments = 5
}