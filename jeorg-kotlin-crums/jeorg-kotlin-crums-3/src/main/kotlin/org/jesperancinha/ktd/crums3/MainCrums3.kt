package org.jesperancinha.ktd.crums3

import org.jesperancinha.ktd.crums3.crum1.CrumOne
import org.jesperancinha.ktd.crums3.crum10.CrossInline
import org.jesperancinha.ktd.crums3.crum11.ChunkParallelization
import org.jesperancinha.ktd.crums3.crum2.CrumTwo
import org.jesperancinha.ktd.crums3.crum3.CrumThree
import org.jesperancinha.ktd.crums3.crum4.CrumFour
import org.jesperancinha.ktd.crums3.crum5.CrumFive
import org.jesperancinha.ktd.crums3.crum6.CrumSix
import org.jesperancinha.ktd.crums3.crum7.CrumSeven
import org.jesperancinha.ktd.crums3.crum8.ClassCatcher
import org.jesperancinha.ktd.crums3.crum9.InstanceScope

suspend fun main(args: Array<String>) {
    CrumOne.main(args)
    CrumTwo.main(args)
    CrumThree.main(args)
    CrumFour.main(args)
    CrumFive.main(args)
    CrumSix.main(args)
    CrumSeven.main(args)
    ClassCatcher.main()
    InstanceScope.main()
    CrossInline.main()
    ChunkParallelization.main()
}

