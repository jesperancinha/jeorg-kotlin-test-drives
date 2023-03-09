package org.jesperancinha.ktd

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

object Logger {
    fun info(logText: Any?): ConsolerizerComposer = ConsolerizerComposer.out().yellow(logText)
    fun infoBefore(logText: Any?): ConsolerizerComposer = synchronized(this) {
        ConsolerizerComposer.out().green(
            "$logText on Thread-${Thread.currentThread().name} with id ${
                Thread.currentThread().threadId()
            }"
        )
    }

    fun infoAfter(logText: Any?): ConsolerizerComposer = synchronized(this) {
        ConsolerizerComposer.out().red(
            "$logText on Thread-${Thread.currentThread().name} with id ${
                Thread.currentThread().threadId()
            }"
        )
    }

    fun infoTitle(logText: String): ConsolerizerComposer = ConsolerizerComposer.outSpace()
        .cyan(ConsolerizerComposer.title(logText))
    fun infoTitleMeasurement(logText: String): ConsolerizerComposer = ConsolerizerComposer.outSpace()
        .black()
        .bgCyan(ConsolerizerComposer.title(logText))
}