package org.jesperancinha.ktd

import kotlin.reflect.jvm.jvmName
import kotlin.reflect.typeOf


class Knife : Utensil {
    init {
        println("A Knife has been created!")
    }
}

class Spoon : Utensil {
    init {
        println("A Spoon has been created!")
    }
}

interface Utensil
class Pot<in T : Utensil> {
    init {
        println("A Pot has been created!")
    }
}


interface Soup
class TomatoSoup : Soup
class AspergerSoup : Soup

class Serving<out T : Soup> {
    init {
        println("A Soup serving has been created!")
    }
}


class SoupEating {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            var potA = Pot<Knife>()
            println(potA::class)
            var potB = Pot<Spoon>()
            println(potB::class)
            var potC = Pot<Utensil>()
            println(potC::class)

            potA = potC
            println(potA::class)
            potB = potC
            println(potB::class)
            potB = potA
            println(potB::class)
            potB = potB
            println(potB::class)

            var aspergerSoupServing = Serving<AspergerSoup>()
            println(aspergerSoupServing::class)
            var tomatoSoupServing = Serving<TomatoSoup>()
            println(tomatoSoupServing::class)
            var soupServing = Serving<Soup>()
            println(soupServing::class)

            soupServing = aspergerSoupServing
            println(soupServing::class)
            soupServing = tomatoSoupServing
            println(soupServing::class)
        }
    }
}