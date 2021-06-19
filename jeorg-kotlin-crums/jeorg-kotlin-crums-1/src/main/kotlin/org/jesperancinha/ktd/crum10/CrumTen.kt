package org.jesperancinha.ktd.crum10

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.eq

/**
 * Created by jofisaes on 19/06/2021
 */
class CrumTen {
    companion object {

        @JvmStatic
        fun main() {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 10 - Infix Functions"))
                .green("a" eq "a")
                .green("b" eq "b")
                .green("c" eq "c")
                .green("d" eq "d")
                .reset()

            try {
                1 eq 2
            } catch (error: Error) {
                ConsolerizerComposer.outSpace()
                    .blue(error)
                    .reset()
            }
        }
    }
}