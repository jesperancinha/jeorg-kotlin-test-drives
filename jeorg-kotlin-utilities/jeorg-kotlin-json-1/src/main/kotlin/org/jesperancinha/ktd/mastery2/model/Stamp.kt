package org.jesperancinha.ktd.mastery2.model

/**
 * Created by jofisaes on 03/07/2021
 */
data class Stamp(
    val id: Long,
    val description: String,
    val year: Long,
    val value: Long,
    val currency: Currency,
    /**
     * Height in millimeters
     */
    val heightMM: Long,
    /**
     * Width in millimeters
     */
    val widthMM: Long
)