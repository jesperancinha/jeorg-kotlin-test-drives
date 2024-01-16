package org.jesperancinha.ktd

import org.jesperancinha.ktd.crum5.Patisserie
import org.jesperancinha.ktd.delegates.Account

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            Cakes.main()
            SoupEating.main()
            ImmutableEatingOut.main()
            CupManager.main()
            Patisserie.main()
            WarehouseManager.main()
            TailRec.main()
            FloatVsDouble.main()
            Delegates.main()
            Account.main(emptyArray())
            IndexSolutionForRIDHashes.main()
            Timeouts.main()
            CollectingCollections.main()
        }
    }
}