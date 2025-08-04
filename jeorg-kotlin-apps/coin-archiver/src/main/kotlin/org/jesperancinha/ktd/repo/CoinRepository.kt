package org.jesperancinha.ktd.repo

import org.jesperancinha.ktd.model.Coin
import org.koin.core.annotation.Single

interface ICoinRepository {
    fun add(coin: Coin): Boolean
    fun getAll(): List<Coin>
}

class CoinRepository : ICoinRepository {
    private val coins = mutableListOf<Coin>()
    override fun add(coin: Coin) = coins.add(coin)
    override fun getAll(): List<Coin> = coins.toList()
}

@Single
class CoinRepositoryInjected : ICoinRepository {
    private val coins = mutableListOf<Coin>()
    override fun add(coin: Coin) = coins.add(coin)
    override fun getAll(): List<Coin> = coins.toList()
}