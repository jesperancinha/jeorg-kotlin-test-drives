/**
 *
 */
package org.jesperancinha.test.drive.books

import picocli.CommandLine

/**
 * @author joao
 */
fun main(args: Array<String>) {
    CommandLine(XmlTestRunner()).execute(*args)
}