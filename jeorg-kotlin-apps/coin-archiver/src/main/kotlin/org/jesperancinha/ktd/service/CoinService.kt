package org.jesperancinha.ktd.service

import org.jesperancinha.ktd.model.Coin
import org.jesperancinha.ktd.repo.CoinRepository
import org.jesperancinha.ktd.repo.CoinRepositoryInjected
import org.koin.core.annotation.Factory

class CoinService(private val repo: CoinRepository) {
    fun register(coin: Coin) {
        println("Registering coin: $coin")
        repo.add(coin)
    }
    fun listAll(): List<Coin> = repo.getAll()
}

@Factory
class CoinServiceInjected(private val repo: CoinRepositoryInjected) {
    fun register(coin: Coin) {
        println("Registering coin: $coin")
        repo.add(coin)
    }
    fun listAll(): List<Coin> = repo.getAll()
}