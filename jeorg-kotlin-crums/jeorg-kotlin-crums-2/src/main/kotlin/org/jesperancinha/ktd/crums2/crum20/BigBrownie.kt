package org.jesperancinha.ktd.crums2.crum20

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 15/07/2021
 *
 * Nothing in this class can be overridden
 * This is because this class isn't open.
 * By default it is a final class
 */
class BigBrownie : Brownie() {

    /**
     * Since our class is not open, our overriding methods and properties become final also.
     */
    public override val price: Int = 100

    /**
     * Since our class is not open, our overriding methods and properties become final also.
     */
    override fun eatCake() {
        ConsolerizerComposer.outSpace()
            .yellow("Cake has been eaten, but it took longer, because it' so big!")
    }
}