package org.jesperancinha.ktd.crums1.crum15

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class SequencesKotlin {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 15 - Sequences in Kotlin"))
                .magenta("Sequences in kotlin are a mechanism that allow us to create and indefinite amount of elements in a list according to some rules")
            val sequence1 = generateSequence(1) { it + 2 }
            val elements10 = sequence1.take(10).toList()
            val sequence2 = generateSequence(Pair(1, 2)) { Pair(it.second, it.second * it.first) }.map { it.first }
            ConsolerizerComposer.outSpace()
                .blue("We just made this list from the sequence")
                .orange(elements10)
                .reset()
            ConsolerizerComposer.outSpace()
                .blue("We can also fold to achieve and use results from the previous element")
                .orange(sequence2.take(10).toList())
                .orange("Last element is 0 because ${8192} * ${2097152} is: ${(8192) * (2097152)} in Int")
                .orange("Last element is 0 because ${8192} * ${2097152} is: ${(8192).toDouble() * (2097152).toDouble()} in Double")
                .orange("And that is too big for Int")
                .reset()
        }
    }
}