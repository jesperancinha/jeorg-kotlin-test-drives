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
                .cyan(ConsolerizerComposer.title("Crum 6 - List Extension Function"))
                .reset()
            val list1 = listOf(2, 4, 6, 8, 0, 2, 4, 6, 8)
            val list2 = listOf(1, 2, 4, 2, 9, 4, 5, 3, 5, 9, 2, 1, 0)
            ConsolerizerComposer.outSpace()
                .magenta(list1.allEven())
                .magenta(list1.noneOdd())
                .magenta(list1.notOneOdd())
                .magenta(list1.containsEven())
                .magenta(list1.notAllOdd())
                .magenta(list1.notNoneEven())
                .magenta(list2.allEven())
                .magenta(list2.noneOdd())
                .magenta(list2.notOneOdd())
                .magenta(list2.containsEven())
                .magenta(list2.notAllOdd())
                .magenta(list2.notNoneEven())
                .reset()
        }

    }
}