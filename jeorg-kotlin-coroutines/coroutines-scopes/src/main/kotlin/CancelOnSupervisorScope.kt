import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

object CancelOnSupervisorScope {
    @JvmStatic
    fun main(args: Array<String>):Unit = runBlocking(Dispatchers.Default) {
        supervisorScope {
            launch {
                delay(1.seconds)
                println("Coroutine 1 finished!")
                launch {
                    delay(2.seconds)
                    println("Coroutine 1.1 finished!")
                }
            }
            launch {
                delay(1.seconds)
                println("Coroutine 2 finished!")
                launch {
                    delay(1.seconds)
                    println("Coroutine 2.1 finished!")
                }
            }
        }
        println("All coroutines finished")
    }
}