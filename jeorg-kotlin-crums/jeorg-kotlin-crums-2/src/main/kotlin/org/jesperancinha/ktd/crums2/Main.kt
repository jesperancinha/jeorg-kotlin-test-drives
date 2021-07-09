package org.jesperancinha.ktd.crums2

import kotlinx.coroutines.runBlocking
import org.jesperancinha.ktd.crums2.crum1.CrumOne
import org.jesperancinha.ktd.crums2.crum2.CrumTwo
import org.jesperancinha.ktd.crums2.crum3.CrumThree
import org.jesperancinha.ktd.crums2.crum4.CrumFive
import org.jesperancinha.ktd.crums2.crum4.CrumFour
import org.jesperancinha.ktd.crums2.crum6.CrumSix
import org.jesperancinha.ktd.crums2.crum7.CrumSeven
import org.jesperancinha.ktd.crums2.crum8.CrumEight

fun main(args: Array<String>) {
    CrumOne()
    CrumTwo()
    CrumThree()
    CrumFour()
    CrumFive()
    CrumSix()
    CrumSeven()
    CrumEight()

    CrumOne.main(args)
    CrumTwo.main(args)
    CrumThree.main(args)
    CrumFour.main(args)
    runBlocking {
        CrumSix.main(args)
    }
    CrumSeven.main(args)
    CrumEight.main(args)
}

