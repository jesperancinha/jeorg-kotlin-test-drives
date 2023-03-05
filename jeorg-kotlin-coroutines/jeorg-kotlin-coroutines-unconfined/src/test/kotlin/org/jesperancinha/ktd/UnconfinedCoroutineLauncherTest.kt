package org.jesperancinha.ktd

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class UnconfinedCoroutineLauncherTest {

    private val logger = object {
        fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
        fun infoThread(logText: Any?) = ConsolerizerComposer.out().yellow(logText)
        fun infoTs(logText: Any?) = ConsolerizerComposer.out().red(logText)
        fun infoText(logText: Any?) = ConsolerizerComposer.out().green(logText)
        fun infoTextSeparator(logText: Any?) = ConsolerizerComposer.out().orange(ConsolerizerComposer.title(logText))
        fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title(logText))
        fun infoSubTitle(logText: String) = ConsolerizerComposer.outSpace()
            .black()
            .bgGreen(ConsolerizerComposer.title(logText))
    }

    @Test
    fun main() {
    }
}