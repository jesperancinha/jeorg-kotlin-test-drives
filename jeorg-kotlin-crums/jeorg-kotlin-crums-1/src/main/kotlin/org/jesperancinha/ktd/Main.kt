package org.jesperancinha.ktd

import org.jesperancinha.ktd.crum1.CrumOne
import org.jesperancinha.ktd.crum10.CrumTen
import org.jesperancinha.ktd.crum2.CrumTwo
import org.jesperancinha.ktd.crum3.CrumThree
import org.jesperancinha.ktd.crum4.CrumFour
import org.jesperancinha.ktd.crum5.CrumFive
import org.jesperancinha.ktd.crum6.CrumSix
import org.jesperancinha.ktd.crum7.CrumSeven
import org.jesperancinha.ktd.crum8.CrumEight
import org.jesperancinha.ktd.crum9.CrumNine

fun main(args: Array<String>) {
    CrumOne.main(args)
    CrumTwo.main(args)
    CrumThree.main()
    CrumFour.main()
    CrumFive.main()
    CrumSix.main()
    CrumSeven.main()
    CrumEight.main()
    CrumNine.main()
    CrumTen.main()
}

fun String?.isPlant(): Boolean {
    return this != null && this.contains("plant")
}

fun List<Int>.allEven() = all { it % 2 == 0 }
fun List<Int>.noneOdd() = none { it % 2 != 0 }
fun List<Int>.notOneOdd() = !any { it % 2 != 0 }

fun List<Int>.containsEven() = any { it % 2 == 0 }
fun List<Int>.notAllOdd() = !all { it % 2 != 0 }
fun List<Int>.notNoneEven() = !none { it % 2 == 0 }

infix fun <T> T.eq(other: T): Boolean {
    if (this == other) return true
    else throw AssertionError()
}
