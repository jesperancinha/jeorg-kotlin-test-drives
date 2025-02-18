import kotlinx.coroutines.*
import java.lang.Thread.*

object CancelOnGlobalScope {
    @JvmStatic
    fun main(args: Array<String> = emptyArray()) {
        println("The context of the global scope is ${GlobalScope.coroutineContext}")
        GlobalScope.launch {
            coroutineScope {
                launch {
                    println("Current Thread is ${currentThread()}, and context is ${coroutineContext}")
                    delay(1000)
                    println("This will be cancelled")
                }
                println("Current Thread is ${currentThread()}, and context is ${coroutineContext}")
                throw RuntimeException("Cancelling everything?")
            }
            println("Current Parent Thread is ${currentThread()}, and context is ${coroutineContext}")

        }
        println("Current Parent Thread is ${currentThread()}")
        sleep(2000)
        println("Still running")
    }
}

object CancelOnRunBlocking {
    @JvmStatic
    fun main(args: Array<String> = emptyArray())  {
        runBlocking(Dispatchers.IO) {
            println("The context of the global scope is ${GlobalScope.coroutineContext}")
            coroutineScope {
                launch {
                    println("Current Thread is ${currentThread()}, and context is ${coroutineContext}")
                    delay(1000)
                    println("This will be cancelled")
                }
                println("Current Thread is ${currentThread()}, and context is ${coroutineContext}")
                throw RuntimeException("Cancelling everything")
            }
            println("Current Parent Thread is ${currentThread()}, and context is ${coroutineContext}")

        }
        println("Current Parent Thread is ${currentThread()}")
        sleep(2000)
        println("Still running")
    }
}

object CancelOnExample {
    @JvmStatic
    fun main(args: Array<String> = emptyArray()) {
        CancelOnGlobalScope.main()
        runCatching {
            CancelOnRunBlocking.main()
        }.onFailure {
            it.printStackTrace()
        }
    }
}