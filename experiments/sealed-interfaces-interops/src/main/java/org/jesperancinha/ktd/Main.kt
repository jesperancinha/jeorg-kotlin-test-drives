package org.jesperancinha.ktd;

import java.lang.RuntimeException

sealed interface CrustaceansK : Crustaceans

class LobsterK (val lobster: Lobster) : CrustaceansK

class ShrimpK (val shrimp: Shrimp) : CrustaceansK

class CrabK (val crab: Crab) : CrustaceansK

fun Crustaceans.toK() = when(this) {
    is Lobster -> LobsterK(this)
    is Shrimp -> ShrimpK(this)
    is Crab -> CrabK(this)
    else -> throw RuntimeException("Crustacean Not Found")
}

fun main() {
    val lobster = Lobster()
    doWhatever(lobster)
}

fun doWhatever(i: Crustaceans) = when (i.toK()){
    is LobsterK -> 1
    is ShrimpK -> 2
    is CrabK -> 3
}
