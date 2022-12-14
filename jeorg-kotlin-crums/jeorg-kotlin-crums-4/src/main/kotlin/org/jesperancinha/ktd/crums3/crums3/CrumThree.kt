package org.jesperancinha.ktd.crums3.crums3

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.crums3.crum2.CrumTwo

class CrumThree {

    companion object{
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().orange(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightMagenta(ConsolerizerComposer.title(logText))
        }
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 3 - Prisms with code generation")

        }
    }
}