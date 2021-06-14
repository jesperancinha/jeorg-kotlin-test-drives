package org.jesperancinha.ktd

import org.jesperancinha.ktd.crum1.CrumOne
import org.jesperancinha.ktd.crum2.CrumTwo
import org.jesperancinha.ktd.crum3.CrumThree
import org.jesperancinha.ktd.crum4.CrumFour
import org.jesperancinha.ktd.crum5.CrumFive

fun main(args: Array<String>){
    CrumOne.main(args)
    CrumTwo.main(args)
    CrumThree.main()
    CrumFour.main()
    CrumFive.main()
}

fun String?.isPlant(): Boolean {
    return this != null && this.contains("plant")
}
