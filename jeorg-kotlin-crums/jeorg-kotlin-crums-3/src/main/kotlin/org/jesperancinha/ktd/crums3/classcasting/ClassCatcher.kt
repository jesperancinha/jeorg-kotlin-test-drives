package org.jesperancinha.ktd.crums3.classcasting

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer


val i: Int = 10
val j: Long = 15L
val k = "Klavertje Vier"

class ClassCatcher {
    companion object {

        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().cyan(logText)
            fun info2(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>? = emptyArray()) {
            logger.infoTitle("Crum 8 - Class casting")

            check(i,j,k)
            check(k,j,i)
        }

        private fun check(a: Any, b:Any, c:Any){
            logger.info("When a class fails ${a as? String}")
            logger.info("When a class fails ${b as? String}")
            logger.info("When a class fails ${c as? Long}")

            logger.info2("When a class may not fail ${a as? Int}")
            logger.info2("When a class may not fail ${b as? Long}")
            logger.info2("When a class may not fail ${c as? String}")
        }
    }
}
