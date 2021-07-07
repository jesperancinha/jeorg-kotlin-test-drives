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
                .cyan(ConsolerizerComposer.title("Crum 4 - Coroutine with continuation"))
                .reset()

            runBlocking {
                val text1: String = showMeTheTicket(true,
                    "Just give me the ticket!")
                ConsolerizerComposer.outSpace()
                    .magenta(text1)
                    .reset()
                val text2: String = showMeTheTicket(false,
                    "Just give me the ticket!")
                ConsolerizerComposer.outSpace()
                    .magenta(text2)
                    .reset()
                try {
                    showMeTheTicket(false, true)
                } catch (ex: Exception) {
                    ConsolerizerComposer.outSpace()
                        .red("Text 3 is not reachable")
                        .reset()
                }
                try {
                    showMeTheTicket(true, true)
                } catch (ex: Exception) {
                    ConsolerizerComposer.outSpace()
                        .red("Text 3 is notreach able")
                        .reset()
                }
            }
        }
    }
}

suspend fun <T> showMeTheTicket(switch: Boolean, value: T): T =
    suspendCoroutine {
        try {
            when (value) {
                is Boolean ->
                    if (value)
                        throw RuntimeException()
                else -> {
                }
            }
            ConsolerizerComposer.outSpace()
                .blue("I can do anything before I actually continue")
                .reset()
            if (switch) {
                ConsolerizerComposer.outSpace()
                    .orange("If the continuing works, we will return T")
                it.resume(value)
            } else {
                ConsolerizerComposer.outSpace()
                    .orange("If the continuing works, we will return T, but by using the result type")
                it.resumeWith(Result.success(value))
            }
        } catch (e: Exception) {
            if (switch) {
                it.resumeWithException(e)
            } else {
                it.resumeWith(Result.failure(e))
            }
        }
    }


