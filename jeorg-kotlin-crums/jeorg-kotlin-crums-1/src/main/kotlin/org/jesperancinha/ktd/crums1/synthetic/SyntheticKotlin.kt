package org.jesperancinha.ktd.crums1.synthetic

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class SyntheticKotlin {
    @JvmSynthetic
    fun syntheticKotlinFunction() {
        println("This function is only visible in Kotlin")
    }

    fun publicFunction() {
        println("This function is visible in both Kotlin and Java")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val synthetic = SyntheticKotlin()
            synthetic.syntheticKotlinFunction()
            synthetic.publicFunction()

            val justAClassInJava = JustAClassInJava()
            ConsolerizerComposer.outSpace().yellow(justAClassInJava)
        }
    }
}
