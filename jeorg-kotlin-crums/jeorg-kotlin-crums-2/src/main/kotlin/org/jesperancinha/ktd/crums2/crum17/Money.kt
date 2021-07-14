package org.jesperancinha.ktd.crums2.crum17

/**
 * Created by jofisaes on 14/07/2021
 */
data class Money(
    /**
     * Receives the value in EUROS
     */
    val value: Double) {

    /**
     * Converts the value to another coin type
     */
    val convert: Double
        get() = value * factor

    var coin: Coin = Coin.EUR

    private val factor: Double
        get()= coin.value
}