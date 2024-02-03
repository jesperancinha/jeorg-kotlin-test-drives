package org.jesperancinha.ktd.crums1.records

class VinylLauncher {
    companion object{
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println(VinylJava("The mountain -> Why does a record need an empty body?", 1923))
            println(Vinyl("The mountain -> Data classes don't need an empty body and they do the same thing!", 1923))
        }
    }
}