package org.jesperancinha.ktd.mastery2.model

data class Coin(
    val id: Long,
    val description: String,
    val year: Long,
    val value: Long,
    val currency: Currency,
    /**
     * Diameter in millimeters
     */
    val diameterMM: Long,
    /**
     * Internal Diameter in millimeters (aka hole in a coin)
     */
    val internalDiameterMM: Long = 0
)
