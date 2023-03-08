package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

object IOCoroutineLauncher {
    private val logger = object {
        fun info(logText: Any?) = ConsolerizerComposer.out().yellow(logText)
        fun infoBefore(logText: Any?) = ConsolerizerComposer.out().green(logText)
        fun infoAfter(logText: Any?) = ConsolerizerComposer.out().red(logText)
        fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title(logText))
    }

    @JvmStatic
    fun main(args: Array<String> = emptyArray()) = runBlocking {
        logger.infoTitle(
            "Unconfined Coroutines scope tests / There are currently ${
                Runtime.getRuntime().availableProcessors()
            } CPU's available"
        )
        CoroutineScope(Dispatchers.Unconfined).launch {

        }
        Unit
    }
}