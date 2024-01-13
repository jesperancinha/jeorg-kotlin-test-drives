package org.jesperancinha.ktd

import kotlinx.coroutines.*

enum class Train {
    A, B
}
class TrainStationManager {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            runTrain(train = Train.A)
            runTrain(train = Train.B)
        }

        private suspend fun runTrain(train: Train) = CoroutineScope(Dispatchers.IO).launch {
            if(train == Train.A) {
                coroutineScope {
                    launch {
                        throw RuntimeException("You are not allowed in here!")
                    }
                }
            }
            if(train == Train.B){
                supervisorScope {
                    launch {
                        throw RuntimeException("You are not allowed in here!")
                    }
                }
            }
            println("After train $train, the train station has resumed its operations")
        }.join()
    }
}