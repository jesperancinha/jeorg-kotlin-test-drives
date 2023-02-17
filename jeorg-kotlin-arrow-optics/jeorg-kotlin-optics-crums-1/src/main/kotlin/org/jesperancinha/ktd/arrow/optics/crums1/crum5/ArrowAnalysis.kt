package org.jesperancinha.ktd.arrow.optics.crums1.crum5

import arrow.analysis.pre
import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class ArrowAnalysis {
    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().magenta(logText)
            fun info2(logText: Any) = ConsolerizerComposer.out().brightGreen(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightBlue(ConsolerizerComposer.title(logText))

            fun error(logText: Any?) = ConsolerizerComposer.out().brightRed(logText)
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            fun increment(x: Int): Int {
                pre(x > 0) { "value must be positive" }
                return x + 1
            }
            logger.infoTitle("Crum 5 - Arrow Analysis from https://arrow-kt.io/docs/analysis/conditions/")
            run {
                val result = try {
                    increment(0)
                } catch (ex: IllegalArgumentException) {
                    logger.error(ex)
                    -1
                }
                result shouldBe -1
                logger.info("We made a call with 0 and if an exception occurs we get a result of $result")
            }
            run {
                val result = increment(2)
                result shouldBe 3
                logger.info2("We made a call with 2 and because its a valid result we get $result")
            }
        }
    }
}