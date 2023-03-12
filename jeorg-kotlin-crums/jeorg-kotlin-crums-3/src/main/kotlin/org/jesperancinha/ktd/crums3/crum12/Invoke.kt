package org.jesperancinha.ktd.crums3.crum12

import kotlinx.coroutines.runBlocking
import org.jesperancinha.console.consolerizer.common.Composer
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.crums3.crum11.ChunkParallelization

class Invoke {

    fun showCats() = logger.info("Cats are here! \uD83D\uDC08\uD83D\uDC08\uD83D\uDC08\uD83D\uDC08")

    companion object {
        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title(logText))
        }
        operator fun invoke(text: String) = logger.info(text).run { Invoke() }
        operator fun invoke(index:Int): Composer = logger.info("No text presented for cats at index $index!")
        operator fun invoke(): Composer = logger.info("No text presented for cats!")
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            logger.infoTitle("Crum 12 - Using invoke")
            val test = Invoke("Texting")
            test.showCats()

            val test2 = Invoke(1)
            logger.info("When we use the keyword operator, we are in a way allowing to call a function by using a constructor like form. The type of test2 is ${test2.javaClass}")

            val test3 = Invoke()
            logger.info("However, if the constructor already exist, then the operator doesn't work. The type of test3 is ${test3.javaClass}")
        }
    }
}