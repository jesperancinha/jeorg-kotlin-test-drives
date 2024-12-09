package org.jesperancinha.ktd.crums2

import kotlinx.coroutines.runBlocking
import org.jesperancinha.ktd.crums2.crum1.CrumOne
import org.jesperancinha.ktd.crums2.crum10.CrumTen
import org.jesperancinha.ktd.crums2.crum11.CrumEleven
import org.jesperancinha.ktd.crums2.crum12.CrumTwelve
import org.jesperancinha.ktd.crums2.crum13.CrumThirteen
import org.jesperancinha.ktd.crums2.crum14.CrumFourteen
import org.jesperancinha.ktd.crums2.crum15.CrumFifteen
import org.jesperancinha.ktd.crums2.crum16.CrumSixteen
import org.jesperancinha.ktd.crums2.crum17.CrumSeventeen
import org.jesperancinha.ktd.crums2.crum18.CrumEighteen
import org.jesperancinha.ktd.crums2.crum19.CrumNineteen
import org.jesperancinha.ktd.crums2.crum2.CrumTwo
import org.jesperancinha.ktd.crums2.crum20.CrumTwenty
import org.jesperancinha.ktd.crums2.crum3.CrumThree
import org.jesperancinha.ktd.crums2.crum4.CrumFour
import org.jesperancinha.ktd.crums2.crum5.CrumFive
import org.jesperancinha.ktd.crums2.crum6.CrumSix
import org.jesperancinha.ktd.crums2.crum7.CrumSeven
import org.jesperancinha.ktd.crums2.crum8.CrumEight
import org.jesperancinha.ktd.crums2.crum9.CrumNine
import org.jesperancinha.ktd.crums2.invoking.TheInvoker

fun main(args: Array<String>) {
    CrumOne()
    CrumTwo()
    CrumThree()
    CrumFour()
    CrumFive()
    CrumSix()
    CrumSeven()
    CrumEight()
    CrumNine()
    CrumTen()
    CrumEleven()
    CrumTwelve()
    CrumThirteen()
    CrumFourteen()
    CrumFifteen()
    CrumSixteen()
    CrumSeventeen()
    CrumEighteen()
    CrumNineteen()
    CrumTwenty()

    CrumOne.main(args)
    CrumTwo.main(args)
    CrumThree.main(args)
    CrumFour.main(args)
    CrumFive.main(args)
    runBlocking {
        CrumSix.main(args)
    }
    CrumSeven.main(args)
    CrumEight.main(args)
    CrumNine.main(args)
    CrumTen.main(args)
    CrumEleven.main(args)
    CrumTwelve.main(args)
    CrumThirteen.main(args)
    CrumFourteen.main(args)
    CrumFifteen.main(args)
    CrumSixteen.main(args)
    CrumSeventeen.main(args)
    CrumEighteen.main(args)
    CrumNineteen.main(args)
    CrumTwenty.main(args)

    AnnotationRead.main(args)
    TheInvoker()
}

