package org.jesperancinha.ktd.crums3.multicatch

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class NumberOneException : Exception()
class NumberTwoException : Exception()
class NumberThreeException : Exception()
class NumberFourException : Exception()

class Multicatch {

    companion object {
        private val logger = object {
            fun info(logText: String) = ConsolerizerComposer.out().blue(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 3 - Multicatch statements(Not the official: https://youtrack.jetbrains.com/issue/KT-7128)")
            logger.info(methodCall(1))
            logger.info(methodCall(2))
            logger.info(methodCall(3))
            logger.info(methodCall(4))
        }

        private fun methodCall(i: Int): String = runCatching {
            when (i) {
                1 -> throw NumberOneException()
                2 -> throw NumberTwoException()
                3 -> throw NumberThreeException()
                4 -> throw NumberFourException()
                else -> throw RuntimeException()
            }
        }.let { if (it.isSuccess) "OK" else it.exceptionOrNull()?.javaClass?.name ?: "--- Unknown ---" }
    }
}