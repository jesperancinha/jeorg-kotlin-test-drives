package org.jesperancinha.ktd

import org.jesperancinha.ktd.crum1.CrumOne
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
}

fun String?.isPlant(): Boolean {
    return this != null && this.contains("plant")
}

fun List<Int>.allNonZero() = all { it > 0 }
fun List<Int>.allNonZero1() = none { it == 0 }
fun List<Int>.allNonZero2() = !any { it == 0 }

fun List<Int>.containsZero() = any { it == 0 }
fun List<Int>.containsZero1() = !all { it > 0 }
fun List<Int>.containsZero2() = !none { it == 0 }

infix fun <T> T.eq(other: T) {
    if (this == other) println("OK")
    else println("Error: $this != $other")
}
