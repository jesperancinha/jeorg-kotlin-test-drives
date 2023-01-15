package org.jesperancinha.test.drive.books

import picocli.CommandLine
import picocli.CommandLine.Option


class XmlTestRunnerOptions {
    @Option(name = "--book-test-file", aliases = ["-b"], required = true)
    var bookTestFile: String? = null

    @Option(name = "--number-of-iterations", aliases = ["-n"], required = true)
    var numberOfIterations: Int? = null

    @Option(name = "--noop", required = false)
    var isNoop = false
}