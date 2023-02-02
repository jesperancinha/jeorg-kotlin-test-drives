package org.jesperancinha.ktd.crums3.crum9

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class InstanceScope {

    val number by lazy { (Math.random() * 100).toInt() }

    companion object {

        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().orange(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>? = emptyArray()) {
            logger.infoTitle("Crum 9 - Instance scope")

            check { test -> 9 + test }.let { logger.info("The result is $it") }
            check { test ->
                logger.info("The number we get from the instance is ${this.number}")
                this.number + 9 + test
            }.let { logger.info("The result with the scope is $it") }
        }

        private fun check(f: InstanceScope.(test: Int) -> Int) = InstanceScope()
            .let {
                logger.info("Creating this instance with number ${it.number}")
                f.invoke(it, 10)
            }
    }
}
