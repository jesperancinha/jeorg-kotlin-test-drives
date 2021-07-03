package org.jesperancinha.ktd.crum15

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 03/07/2021
 */
class CrumFifteen {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 15 - Sequences in Kotlin"))
                .magenta("Sequences in kotlin are a mechanism that allow us to create and indefinite amount of elements in a list according to some rules")
            val sequence1 = generateSequence(1) {it + 2}
            val elements10  = sequence1.take(10).toList()

            ConsolerizerComposer.outSpace()
                .blue("We just made this list from the sequence")
                .orange(elements10)
                .reset()
        }
    }
}