package org.jesperancinha.ktd

class DaemonThread {
     companion object {
         @JvmStatic
         fun main(args: Array<String> = emptyArray()) {
                 val daemonThread = Thread {
                     repeat(5) {
                         println("Daemon thread is running... $it")
                         Thread.sleep(500)
                     }
                 }

                 daemonThread.isDaemon = true
                 daemonThread.start()

                 println("Main thread is ending.")
         }
     }
}