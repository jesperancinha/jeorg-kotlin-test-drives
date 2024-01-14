package org.jesperancinha.ktd.crums3

import arrow.atomic.AtomicInt
import java.lang.Thread.sleep
import java.time.LocalDateTime
import java.util.*


class SleepingTimerTask : TimerTask() {

    val counter = AtomicInt(0)

    /**
     * Explicitly blocking to simulate task
     * This tasks takes 1 second to complete
     */
    override fun run() {
        println("Task started at ${LocalDateTime.now()}")
        sleep(1000)
        counter.addAndGet(1)
        callLongProcess()
        println("Task ended at ${LocalDateTime.now()}")
    }

    private fun callLongProcess() {
        sleep(5000)
    }

    companion object {

        /**
         * Each task will run for 1 second and will start every 2 seconds
         */
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val timerTask = SleepingTimerTask()
            val timer = Timer(true)
            timer.scheduleAtFixedRate(timerTask, 0, 2_000)
            println("TimerTask has started and now we wait 10 seconds")
            println("Normally it should count 5 calls, but maybe not now, because of the long call")
            sleep(10_000)
            timer.cancel()
            println("TimerTask has been cancelled")
            println("${timerTask.counter} calls have been made!")
        }
    }
}
