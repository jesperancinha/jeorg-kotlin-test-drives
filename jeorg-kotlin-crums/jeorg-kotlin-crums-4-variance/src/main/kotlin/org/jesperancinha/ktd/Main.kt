package org.jesperancinha.ktd

import org.jesperancinha.ktd.crum5.Patisserie

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
        }
    }
}