package org.jesperancinha.ktd.crums1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class FunctionNames {
    companion object {

        fun doNothing () = Unit

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace().green(::doNothing)
        }
    }
}