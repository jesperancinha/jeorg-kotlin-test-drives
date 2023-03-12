package org.jesperancinha.ktd

import org.jesperancinha.console.consolerizer.common.Composer
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

object Logger {
    fun info(logText: Any?): Composer = ConsolerizerComposer.out().yellow(logText)
    fun infoBefore(logText: Any?): Composer = synchronized(this) {
        ConsolerizerComposer.out().green(
            "$logText on Thread-${Thread.currentThread().name} with id ${
                Thread.currentThread().threadId()
            }"
        )
    }

    fun infoAfter(logText: Any?): Composer = synchronized(this) {
        ConsolerizerComposer.out().red(
            "$logText on Thread-${Thread.currentThread().name} with id ${
                Thread.currentThread().threadId()
            }"
        )
    }

    fun infoTitle(logText: String): Composer = ConsolerizerComposer.outSpace()
        .cyan(ConsolerizerComposer.title(logText))
    fun infoTitleMeasurement(logText: String): Composer = ConsolerizerComposer.outSpace()
        .black()
        .bgCyan(ConsolerizerComposer.title(logText))
}