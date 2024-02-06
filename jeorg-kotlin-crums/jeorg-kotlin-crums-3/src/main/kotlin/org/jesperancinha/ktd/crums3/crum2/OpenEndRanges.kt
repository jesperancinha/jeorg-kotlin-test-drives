package org.jesperancinha.ktd.crums3.crum2

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title


object OpenEndRanges {
    private val logger = object {
        fun info(logText: String) = ConsolerizerComposer.out().blue(logText)

        fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
            .cyan(title(logText))
    }

    @OptIn(ExperimentalStdlibApi::class)
    @JvmStatic
    fun main(args: Array<String> = emptyArray()) {
        logger.infoTitle("Crum 2 - Open End Ranges")
        val myRange = 0.0..<10.0
        val number1 = 0.0
        val number2 = 9.9999
        val number3 = 9.999999999999998
        val number4 = 10.0
        logger.info("Current range is $myRange")
        listOf(number1, number2, number3, number4).forEach {
            logger.info("Is $it is range? ${it in myRange}")
        }
    }
}
