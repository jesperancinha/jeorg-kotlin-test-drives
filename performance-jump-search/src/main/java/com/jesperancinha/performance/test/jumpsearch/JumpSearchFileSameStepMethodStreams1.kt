package com.jesperancinha.performance.test.jumpsearch

import com.jesperancinha.performance.test.jumpsearch.interfaces.JumpSearchFileStreams
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * For this implementation I decided to try a stream approach. All files have
 * been generated and so what we have to do is to provide a running algorithm
 * that, instead of retrieving and placing everything in memory. It will just
 * use the element it needs, and throw everything else away while doing so.
 *
 * With this exercise I intend to check the differences in memory occupation and
 * in
 *
 * @author JOAO
 */
class JumpSearchFileSameStepMethodStreams1 : JumpSearchFileStreams {
    override fun getNumberIndexFromArray(number: Int, completeList: InputStream): Int {
        completeList.mark(Int.MAX_VALUE)
        try {
            Scanner(completeList).use { scannerCounter ->
                scannerCounter.useDelimiter("\\,\\ ")
                var length = 0
                while (scannerCounter.hasNextLine()) {
                    scannerCounter.nextInt()
                    length++
                }
                completeList.reset()
                Scanner(completeList).use { scanner ->
                    val sb = StringBuilder()
                    val step = Math.sqrt(length.toDouble()).toInt()
                    sb.append("[0-9]{1,}\\,\\ ".repeat(Math.max(0, step - 1)))
                    sb.append("[0-9]{1,}")
                    val pattern = sb.toString()
                    var currentStep = 0
                    while (scanner.hasNextLine()) {
                        var values = scanner.findInLine(pattern)
                        if (values == null) {
                            values = scanner.nextLine()
                        }
                        val segment = Arrays.stream(values!!.split(", ".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()).mapToInt { s: String -> s.toInt() }
                            .toArray()
                        currentStep += segment.size
                        if (segment[segment.size - 1] >= number) {
                            currentStep -= step
                            for (i in 0 until step) {
                                if (segment[i] == number) {
                                    return currentStep
                                }
                                currentStep++
                            }
                        }
                    }
                }
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return -1
    }
}