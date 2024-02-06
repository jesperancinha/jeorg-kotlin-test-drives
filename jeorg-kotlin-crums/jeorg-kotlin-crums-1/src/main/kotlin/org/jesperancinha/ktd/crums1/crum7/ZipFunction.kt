package org.jesperancinha.ktd.crums1.crum7

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/06/2021
 */
class ZipFunction {
    companion object {

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 7 - Zip Function"))
                .reset()
            val first = "This is a great idea"
            val second = "This is a better idea"
            ConsolerizerComposer.out().yellow(first.zip(second))
        }
    }
}