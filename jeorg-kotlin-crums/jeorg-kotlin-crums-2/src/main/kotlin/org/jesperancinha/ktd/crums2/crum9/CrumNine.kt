package org.jesperancinha.ktd.crums2.crum9

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 13/07/2021
 */
class CrumNine {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 9 - Apply to Apply"))
                .reset()

            apply {
                val testNumber = Math.random()
                ConsolerizerComposer.out()
                    .green("This is the first test number $testNumber")
                    .reset()
            }
            apply {
                val testNumber = Math.random()
                ConsolerizerComposer.out()
                    .green("This is the second test number $testNumber")
                    .reset()
            }
            apply {
                val testNumber = Math.random()
                ConsolerizerComposer.out()
                    .green("This is the third test number $testNumber")
                    .reset()
            }
        }
    }
}