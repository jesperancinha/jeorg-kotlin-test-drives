package org.jesperancinha.ktd

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Ingredients {
    val list: List<String>
}

interface Part {
    val name: String
}

open class Cake(
    val ingredients: Ingredients,
) {
    val cakeName: String?
        get() = if (ingredients.list.contains("flower") && ingredients.list.contains("egg")) {
            "Pão de Ló"
        } else {
            null
        }
}

class PaoDeLo(ingredients: Ingredients) : Cake(ingredients)

@Suppress("UNCHECKED_CAST")
class CakeShop<out T : Cake, in U : Ingredients, V : Part> {
    fun makeCake(ingredients: U,
                 createCakeFunction: (@UnsafeVariance U) -> @UnsafeVariance T)
    = createCakeFunction(ingredients)

// This would never work
//    fun makeIngredients(cake: T) = object :Ingredients {
//        override val list: List<String>
//            get() = listOf("whatever")
//
//    } as U

    fun repairDoor(door: V): V = logger.info("Door has been repaired!").let { door }

    companion object {
        val logger = LoggerFactory.getLogger(CakeShop::class.java)
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val shop = CakeShop<PaoDeLo, Ingredients, Part>()
        val cake = shop.makeCake(object : Ingredients {
            override val list: List<String>
                get() = listOf("flower", "egg")
        }){
            ingredients -> PaoDeLo(ingredients)
        }
        logger.info("Cake ${cake.cakeName} has been made!")
        shop.repairDoor(object : Part {
            override val name: String
                get() = "Sliding Door"

        })
    }

    val logger: Logger = LoggerFactory.getLogger(Main::class.java)
}