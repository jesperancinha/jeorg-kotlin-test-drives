/**
 *
 */
package org.jesperancinha.test.drive.books

/**
 * @author joao
 */
fun main(args: Array<String>) {
    val worker = XmlTestRunnerWorker(args)
    if (!worker.isNoop) {
        worker.runTests()
    }
}