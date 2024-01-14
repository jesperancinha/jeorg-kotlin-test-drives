package org.jesperancinha.ktd

import java.lang.Thread.sleep
import java.util.*
import kotlin.system.measureTimeMillis


class Timeouts {

    internal class TimeOutTask(f: () -> Unit) : TimerTask() {

        val thread = Thread(f)
        override fun run() {
            thread.start()
        }

        override fun cancel(): Boolean {
            super.cancel()
            thread.interrupt()
            return true
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = measureTimeMillis {
            val timer = Timer()
            val scheduledFunction = { sleep(5000) }
            runFunction(timer = timer, timeout = 1000, f = scheduledFunction)
        }.let {
            println(it)
        }

        private fun runFunction(timer: Timer, timeout:Long, f: () -> Unit) {
            val timeOutTask = TimeOutTask(f)
            timer.schedule(timeOutTask, 0)
            sleep(timeout)
            timeOutTask.cancel()
            timer.cancel()
        }
    }
}