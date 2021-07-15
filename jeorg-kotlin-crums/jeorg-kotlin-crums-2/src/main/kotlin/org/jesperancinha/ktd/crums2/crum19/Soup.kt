package org.jesperancinha.ktd.crums2.crum19

class Soup(_mainIngredient: String, _secondIngredient: String) {

    val secondIngredient = _secondIngredient

    /**
     *  Variable initialization follow declaration order
     *  This means that if we declare a variable after its initialization, the Kotlin compiler will have no knowledge of the variable
     *
     */
    val info: String

    var mainIngredient: String

    init {
        info = "This is a class to make soups"
        mainIngredient = _mainIngredient
    }

//    var ingredient2:String
//    val info:String

    val ingredientResult = getInfoAndIngredients()

    fun getInfoAndIngredients() = listOf(info, mainIngredient, secondIngredient)
}