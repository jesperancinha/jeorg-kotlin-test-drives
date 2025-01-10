package org.jesperancinha.ktd.overloads

class Greet {
    @JvmOverloads
    fun greet(name: String, message: String = "Hello"): String {
        return "$message, $name!"
    }
}