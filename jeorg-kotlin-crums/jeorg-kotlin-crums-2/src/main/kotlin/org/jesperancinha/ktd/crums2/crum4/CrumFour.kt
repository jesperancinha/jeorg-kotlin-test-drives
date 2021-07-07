package org.jesperancinha.ktd.crums2.crum4

import kotlinx.coroutines.runBlocking
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by jofisaes on 07/07/2021
 */
class CrumFour {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 4 - Function as a receiver"))
                .reset()

            runBlocking {
                val text: String = showMeTheTicket(1) { "Just give me the ticket!" }
                ConsolerizerComposer.outSpace()
                    .magenta(text)
            }


        }

    }

}

suspend fun <T> showMeTheTicket(delatyms: Int, showFunction: () -> T): T =
    suspendCoroutine {
        try {
            it.resume(showFunction())
        } catch (e: Exception) {
            it.resumeWithException(e)
        }
    }

