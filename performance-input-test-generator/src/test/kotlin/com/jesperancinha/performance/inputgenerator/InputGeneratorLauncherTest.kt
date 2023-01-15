package com.jesperancinha.performance.inputgenerator

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class InputGeneratorLauncherTest {

    @Test
    fun testInputGenerator() {
        val testFilename = "/testInputGenerator.txt"
        InputGeneratorLauncher.main(arrayOf("-n", "100", "-f", "/tmp/$testFilename"))
        val f = File("/tmp", testFilename)
        f.shouldNotBeNull()
        BufferedReader(FileReader(f)).use { bf ->
            val test: String = bf.readLine()
            val splitTable = test.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            // Tests if the first element is an integer
            splitTable[0].trim { it <= ' ' }.toInt()
            // Tests if the last element is an integer
            splitTable[splitTable.size - 1].trim { it <= ' ' }.toInt()
            val resultTestNumber = splitTable.size
            resultTestNumber shouldBe 100
        }
    }
}