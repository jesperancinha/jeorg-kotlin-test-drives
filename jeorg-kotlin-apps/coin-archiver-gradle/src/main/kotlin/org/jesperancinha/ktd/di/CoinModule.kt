package org.jesperancinha.ktd.di

import org.jesperancinha.ktd.model.Coin
import org.jesperancinha.ktd.repo.CoinRepository
import org.jesperancinha.ktd.service.CoinService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coinModule = module {
    single { CoinRepository() }
    factory {
        CoinService(repo = get())
    }
    single(
        qualifier = named(name = "country")
    ) { "Portugal" }
    factory { (year: Int) ->
        Coin(
            year = year,
            country = get(
                qualifier =
                    named("country")
            ),
            denomination = "1 Euro"
        )
    }
    factory(named("rare")) { (year: Int) -> Coin(year, "Greece", "Olympic Coin") }
}

@Module
@ComponentScan("org.jesperancinha.ktd")
class AppModule
