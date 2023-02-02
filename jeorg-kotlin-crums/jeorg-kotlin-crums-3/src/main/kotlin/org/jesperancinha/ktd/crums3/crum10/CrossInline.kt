package org.jesperancinha.ktd.crums3.crum10

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Tests Crossinline
 * Fenestev Moon is a fictional character of my novel spread around different content I make around software development.
 */
class CrossInline {
    companion object {
        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().orange(logText)
            fun infoGreen(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 10 - Crossinline")

            logger.infoGreen("The point of crossinline is to avoid local-returns")
            logger.infoGreen("local returns is just a fancy way to talk about a return statement in the middle of an execution scope")
            logger.infoGreen("crossinline forbids the usage of local returns")

            testWithLocalReturn()
            testWithoutLocalReturn()
        }

        private fun testWithLocalReturn(): Unit = run {
            logger.info("When I talk about Fenestev Moon")
            untrueOpinion {
                logger.info("I say he is a very nice guy")
                return
            }
            logger.info("I say he is a very bad guy")
        }

        private fun testWithoutLocalReturn(): Unit = run {
            logger.info("When I talk about Fenestev Moon")
            trueOpinion {
                logger.info("I say he is a very nice guy")
                return@trueOpinion
            }
            logger.info("but I also say that he is not exactly that")
        }

        private inline fun untrueOpinion(f: () -> Unit) = f()
        private inline fun trueOpinion(crossinline f: () -> Unit) = f()
    }
}