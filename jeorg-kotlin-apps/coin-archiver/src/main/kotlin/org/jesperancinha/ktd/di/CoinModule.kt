package org.jesperancinha.ktd.di

import org.jesperancinha.ktd.model.Coin
import org.jesperancinha.ktd.repo.CoinRepository
import org.jesperancinha.ktd.service.CoinService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coinModule = module {
    // Repositories and services
    single { CoinRepository() }
    factory { CoinService(repo = get()) }
    
    // Constants
    single(qualifier = named("country")) { "Portugal" }
    
    // Coin factories
    factory { (year: Int) ->
        Coin(
            year = year,
            country = get(qualifier = named("country")),
            denomination = "1 Euro"
        )
    }
    factory(named("rare")) { (year: Int) -> Coin(year, "Greece", "Olympic Coin") }
}