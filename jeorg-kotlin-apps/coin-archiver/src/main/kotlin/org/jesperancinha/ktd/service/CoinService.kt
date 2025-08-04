package org.jesperancinha.ktd.service

import org.jesperancinha.ktd.model.Coin
import org.jesperancinha.ktd.repo.CoinRepository
import org.jesperancinha.ktd.repo.CoinRepositoryInjected
import org.koin.core.annotation.Factory

interface CoinServiceInterface {
    fun register(coin: Coin)
    fun listAll(): List<Coin>
}

class CoinService(private val repo: CoinRepository) : CoinServiceInterface {
    override fun register(coin: Coin) {
        println("Registering coin: $coin")
        repo.add(coin)
    }
    
    override fun listAll(): List<Coin> = repo.getAll()
}

@Factory
class CoinServiceInjected(private val repo: CoinRepositoryInjected) : CoinServiceInterface {
    override fun register(coin: Coin) {
        println("Registering coin: $coin")
        repo.add(coin)
    }
    
    override fun listAll(): List<Coin> = repo.getAll()
}