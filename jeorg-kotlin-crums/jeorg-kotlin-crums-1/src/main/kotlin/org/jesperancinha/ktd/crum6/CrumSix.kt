package org.jesperancinha.ktd.crum6

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.*

/**
 * Created by jofisaes on 14/06/2021
 */
class CrumSix {
    companion object {
        @JvmStatic
        fun main() {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 5 - String Extension Function"))
                .reset()
            val list1 = listOf(1, 2, 3)
            val list2 = listOf(0, 1, 2)
            ConsolerizerComposer.outSpace()
                .magenta(list1.allNonZero())
                .magenta(list1.allNonZero1())
                .magenta(list1.allNonZero2())
                .magenta(list1.containsZero())
                .magenta(list1.containsZero1())
                .magenta(list1.containsZero2())
                .magenta(list2.allNonZero())
                .magenta(list2.allNonZero1())
                .magenta(list2.allNonZero2())
                .magenta(list2.containsZero())
                .magenta(list2.containsZero1())
                .magenta(list2.containsZero2())
                .reset()
        }

    }
}