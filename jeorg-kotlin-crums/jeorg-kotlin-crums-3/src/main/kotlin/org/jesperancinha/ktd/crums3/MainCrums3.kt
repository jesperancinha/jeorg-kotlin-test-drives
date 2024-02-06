package org.jesperancinha.ktd.crums3

import kotlinx.coroutines.runBlocking
import org.jesperancinha.ktd.crums3.crum1.ConsolerizerPrint
import org.jesperancinha.ktd.crums3.crum10.CrossInline
import org.jesperancinha.ktd.crums3.crum11.ChunkParallelization
import org.jesperancinha.ktd.crums3.crum12.Invoke
import org.jesperancinha.ktd.crums3.crum13.InAndOuts
import org.jesperancinha.ktd.crums3.crum14.MutableFlow
import org.jesperancinha.ktd.crums3.crum2.OpenEndRanges
import org.jesperancinha.ktd.crums3.crum3.CrumThree
import org.jesperancinha.ktd.crums3.crum4.CrumFour
import org.jesperancinha.ktd.crums3.crum5.CrumFive
import org.jesperancinha.ktd.crums3.crum6.CrumSix
import org.jesperancinha.ktd.crums3.crum7.CrumSeven
import org.jesperancinha.ktd.crums3.crum8.ClassCatcher
import org.jesperancinha.ktd.crums3.crum9.InstanceScope
import org.jesperancinha.ktd.crums3.multiple.MultipleSameTypeCats
import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility

fun main(args: Array<String>) = runBlocking {
    ConsolerizerPrint.main(args)
    OpenEndRanges.main()
    CrumThree.main(args)
    CrumFour.main(args)
    CrumFive.main(args)
    CrumSix.main(args)
    CrumSeven.main(args)
    ClassCatcher.main()
    InstanceScope.main()
    CrossInline.main()
    ChunkParallelization.main()
    Invoke.main()
    InAndOuts.main()
    MutableFlow.main()
    SleepingTimerTask.main()
    MultipleSameTypeCats.main()
    NewspaperLauncherVisibility.main()
}

