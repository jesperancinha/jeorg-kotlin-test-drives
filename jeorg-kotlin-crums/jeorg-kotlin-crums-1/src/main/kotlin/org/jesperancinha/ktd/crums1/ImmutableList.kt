package org.jesperancinha.ktd.crums1

class ImmutableList {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val listA = listOf("A", "B", "C")
//            listA += "D"
            val listB = mutableListOf("A", "B", "C")
            listB += "D"
            println(listA)
            println(listB)
        }
    }
}