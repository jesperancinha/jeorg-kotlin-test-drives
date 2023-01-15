package com.jesperancinha.performance.inputgenerator

import com.google.common.collect.ImmutableList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.kohsuke.args4j.CmdLineException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.logging.Logger

class InputGeneratorTest {
    @Test
    @Throws(IOException::class, CmdLineException::class)
    fun testInputGenerator() {
        val testFilename = "/testInputGenerator.txt"
        InputGenerator.main(arrayOf("-n", "100", "-f", testFilename))
        val f = File("/tmp", testFilename)
        BufferedReader(FileReader(f)).use { bf ->
            val test: String = bf.readLine()
            val splitTable = test.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            // Tests if the first element is an integer
            splitTable[0].trim { it <= ' ' }.toInt()
            // Tests if the last element is an integer
            splitTable[splitTable.size - 1].trim { it <= ' ' }.toInt()
            val resultTestNumber = splitTable.size
            assertEquals(100, resultTestNumber)
        }
    }

    @Test
    @Throws(IOException::class, CmdLineException::class)
    fun main() {
        InputGenerator.main(ImmutableList.of("-n", "100", "-f", "/testInputGenerator.txt").toTypedArray())
    }

    companion object {
        private val logger = Logger.getLogger(InputGeneratorTest::class.java.name)
    }
}