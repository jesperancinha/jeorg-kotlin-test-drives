package org.jesperancinha.ktd

import org.jesperancinha.ktd.di.coinModule
import org.jesperancinha.ktd.model.Coin
import org.jesperancinha.ktd.repo.CoinRepositoryInjected
import org.jesperancinha.ktd.service.CoinService
import org.jesperancinha.ktd.service.CoinServiceInjected
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.mp.KoinPlatformTools
import org.koin.core.logger.Level

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(coinModule)
    }
}

fun main() {
    initKoin {
        printLogger(Level.INFO)
    }
    val koin = KoinPlatformTools.defaultContext().get()
    val service = koin.get<CoinService>()

    val coin2024 = koin.get<Coin> { parametersOf(2024) }
    val coin1990 = koin.get<Coin> { parametersOf(1990) }
    service.register(coin2024)
    service.register(coin1990)

    val rareCoin = koin.get<Coin>(named("rare")) { parametersOf(2004) }

    println("All coins:")
    service.listAll().forEach(::println)
    println("Rare coin: $rareCoin")

    val service2 = koin.get<CoinService>()
    println("Service1: $service")
    println("Service2: $service2")

//    val service3: CoinServiceInjected = koin.get()
//    println("All coins via service3 (Injected): $service3:")
//    service3.listAll().forEach(::println)
//    println("Rare coin: $rareCoin")

}