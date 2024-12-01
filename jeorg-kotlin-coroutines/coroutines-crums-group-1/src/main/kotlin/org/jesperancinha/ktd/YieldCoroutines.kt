package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

class YieldCoroutines {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking(Dispatchers.Default) {

            fun fibonacci() = sequence {
                var terms = Pair(0, 1)

                while (true) {
                    yield(terms.first)
                    terms = Pair(terms.second, terms.first + terms.second)
                }
            }

            println(fibonacci().take(10).toList())

            val job1 = launch {
                val result1 = downloadFile("file1.txt", 2000)
                println(result1)
            }

            val job2 = launch {
                val result2 = downloadFile("file2.txt", 1000)
                println(result2)
            }

            yield()

            job1.join()
            job2.join()
        }

        private fun CoroutineScope.downloadFile(string: String, i: Int) {
            println("File $string has downloaded $i")
        }
    }
}