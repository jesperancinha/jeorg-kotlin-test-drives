package org.jesperancinha.ktd


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

    fun takeABit(utensil: T) = println(utensil)
}


interface Soup
class TomatoSoup : Soup
class AspergerSoup : Soup

class Serving<out T : Soup>(
    val soup:T
) {
    init {
        println("A Soup serving has been created!")
    }

    fun serving() = soup
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

//            potA = potB
//            Only knife works for Pot A at this point
//            potA.takeABit(Spoon())
            potA.takeABit(Knife())
//            potA.takeABit(object : Utensil {
//            })
            potA = potC
            println(potA::class)
            potB = potC
            println(potB::class)
            potB = potA
            println(potB::class)
            potA = potB
            println(potB::class)
            potC = potA
            println(potC)
            potA.takeABit(Spoon())
            potA.takeABit(Knife())
            potA.takeABit(object : Utensil {
            })


            val aspergerSoupServing = Serving(AspergerSoup())
            println(aspergerSoupServing::class)
            val tomatoSoupServing = Serving(TomatoSoup())
            println(tomatoSoupServing::class)
            var soupServing = Serving<Soup>(object : Soup {})
            println(soupServing::class)

            soupServing = aspergerSoupServing
            println(soupServing::class)
            soupServing = tomatoSoupServing
            println(soupServing::class)
            soupServing = Serving(object : Soup {})
            println(soupServing::class)
            soupServing.serving()
        }
    }
}