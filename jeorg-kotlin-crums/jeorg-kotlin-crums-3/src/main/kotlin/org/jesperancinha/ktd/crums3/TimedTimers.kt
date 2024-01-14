package org.jesperancinha.ktd.crums3

import java.lang.Thread.sleep
import java.time.LocalDateTime
import java.util.*


class SleepingTimerTask : TimerTask() {

    /**
     * Explicitly blocking to simulate task
     * This tasks takes 1 second to complete
     */
    override fun run() {
        println("Task started at ${LocalDateTime.now()}")
        sleep(1000)
        println("Task ended at ${LocalDateTime.now()}")
    }

    companion object {

        /**
         * Each task will run for 1 second and will start every 2 seconds
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val timerTask: TimerTask = SleepingTimerTask()
            val timer = Timer(true)
            timer.scheduleAtFixedRate(timerTask, 0, 2_000)
            println("TimerTask has started and now we wait 10 seconds")
            sleep(10_000)
            timer.cancel()
            println("TimerTask has been cancelled")
        }
    }
}
