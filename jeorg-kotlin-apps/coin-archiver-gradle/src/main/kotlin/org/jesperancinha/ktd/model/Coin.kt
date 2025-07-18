package org.jesperancinha.ktd.model

import org.koin.core.qualifier.Qualifier

data class Coin(
    val year: Int,
    val country: String,
    val denomination: String
)
