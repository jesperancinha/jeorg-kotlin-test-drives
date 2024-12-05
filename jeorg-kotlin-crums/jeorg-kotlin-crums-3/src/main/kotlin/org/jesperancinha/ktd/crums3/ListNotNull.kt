package org.jesperancinha.ktd.crums3

class ListNotNull {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val listOfNotNull = listOfNotNull("Apex Twin", null, "Ballroom")
            println(listOfNotNull.javaClass)
            println(listOfNotNull)
        }
    }
}