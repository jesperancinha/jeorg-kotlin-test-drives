package org.jesperancinha.ktd.crums1.crum6

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/06/2021
 */
class ExtensionFunctions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
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

fun List<Int>.allEven() = all { it % 2 == 0 }
fun List<Int>.noneOdd() = none { it % 2 != 0 }
fun List<Int>.notOneOdd() = !any { it % 2 != 0 }
fun List<Int>.containsEven() = any { it % 2 == 0 }
fun List<Int>.notAllOdd() = !all { it % 2 != 0 }
fun List<Int>.notNoneEven() = !none { it % 2 == 0 }
