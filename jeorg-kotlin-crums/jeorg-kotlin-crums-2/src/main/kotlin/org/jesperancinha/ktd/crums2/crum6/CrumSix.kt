package org.jesperancinha.ktd.crums2.crum6

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.lang.Math.random
import kotlin.system.measureTimeMillis

/**
 * Created by jofisaes on 09/07/2021
 */
class CrumSix {
    companion object {
        @JvmStatic
        suspend fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 6 - Coroutines"))
                .reset()

            val timing = measureTimeMillis {
                GlobalScope.launch {
                    val innerTiming = measureTimeMillis {
                        val something = getSomething()
                        val something2 = getSomething2()
                        ConsolerizerComposer.outSpace()
                            .yellow("The end result is ${something + something2}")
                    }
                    ConsolerizerComposer.outSpace()
                        .yellow("Calling the methods in the subroutine cost $innerTiming milliseconds")
                        .reset()
                }
            }
            delay(300)
            ConsolerizerComposer.outSpace()
                .yellow("Triggering all subroutines cost $timing milliseconds")
                .reset()
        }
    }
}

suspend fun getSomething(): Double {
    delay(100)
    return random()
}

suspend fun getSomething2(): Double {
    delay(100)
    return random()
}