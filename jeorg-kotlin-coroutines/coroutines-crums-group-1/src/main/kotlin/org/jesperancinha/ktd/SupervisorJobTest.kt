package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SupervisorJobTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking(Dispatchers.IO) {
            val supervisorJob = SupervisorJob()
            val scope = CoroutineScope(Default + supervisorJob)
            scope.launch {
                println("Child 1 is starting...")
                delay(1000)
                println("Child 1 completed successfully.")
            }
            scope.launch {
                println("Child 2 is starting...")
                throw RuntimeException("Child 2 failed!")
            }

            scope.launch {
                println("Child 3 is starting...")
                delay(2000)
                println("Child 3 completed successfully.")
            }
            delay(3000)

            println("Cancelling the supervisor job...")
            scope.cancel()
        }
    }
}