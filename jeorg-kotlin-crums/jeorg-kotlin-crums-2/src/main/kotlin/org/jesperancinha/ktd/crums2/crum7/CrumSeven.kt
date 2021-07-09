package org.jesperancinha.ktd.crums2.crum7

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 09/07/2021
 */
class CrumSeven {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val something = Something()
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 7 - Type Safe Builders"))
                .reset()

            ConsolerizerComposer.outSpace()
                .green(something)
                .reset()
            doSomethingWithSomething(something) {
                this.something = "Whatever"
            }
            ConsolerizerComposer.outSpace()
                .green(something)
                .reset()
        }
    }
}

fun doSomethingWithSomething(something: Something, function: Something.() -> Unit) {
    something.function()
}

class Something {
    var something = "Not yet!!"

    override fun toString(): String {
        return something
    }

}
