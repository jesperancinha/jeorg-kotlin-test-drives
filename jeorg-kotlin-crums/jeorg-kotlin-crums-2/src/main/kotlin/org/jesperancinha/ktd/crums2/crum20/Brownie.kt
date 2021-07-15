package org.jesperancinha.ktd.crums2.crum20

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * This class is open and so this means that all open and overriding methods will be themselves overridable.
 * The only way to prevent this is by removing open from the normal methods and add the final keyword to the overriding methods
 * This is the use of the keyword final in Kotlin
 */
open class Brownie : Cake() {
    protected open val price = 20

    open fun eatCake() {
        ConsolerizerComposer.outSpace()
            .yellow("Cake has been eaten")
    }

    @JvmName("getActualPrice")
    fun getPrice(): Int {
        return price
    }
    /**
     * We use final to prevent this method to be overridden
     */
    final override fun symbol(): String {
        return "🍩"
    }
}