package org.jesperancinha.ktd.crums1

interface Greetable {
    fun greet() {
        println("Hello from Greetable!")
    }
}

class Greeter : Greetable

class DefaultInInterface {
     companion object {
         @JvmStatic
         fun main(args: Array<String> = emptyArray()) {
             val greeter = Greeter()
             greeter.greet()
             val greeter2 = TestDefault()
             greeter2.testDefault()
         }
     }
}