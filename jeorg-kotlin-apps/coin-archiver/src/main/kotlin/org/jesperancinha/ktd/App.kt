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
    
    KoinPlatformTools.defaultContext().get().let { koin ->
        val service: CoinService = koin.get()
        
        // Register coins
        listOf(
            koin.get<Coin> { parametersOf(2024) },
            koin.get<Coin> { parametersOf(1990) }
        ).forEach(service::register)
        
        // Get rare coin
        val rareCoin = koin.get<Coin>(named("rare")) { parametersOf(2004) }
        
        // Print results
        println("All coins:")
        service.listAll().forEach(::println)
        println("Rare coin: $rareCoin")
        
        // Compare services
        val service2: CoinService = koin.get()
        println("Service1: $service")
        println("Service2: $service2")
        
        // Using annotation-based injected service
        val service3: CoinServiceInjected = koin.get()
        println("All coins via service3 (Injected): $service3:")
        service3.listAll().forEach(::println)
        println("Rare coin: $rareCoin")
    }
}