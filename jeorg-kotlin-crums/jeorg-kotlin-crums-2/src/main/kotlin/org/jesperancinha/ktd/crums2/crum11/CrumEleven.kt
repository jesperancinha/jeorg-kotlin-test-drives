package org.jesperancinha.ktd.crums2.crum11

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.lang.Thread.sleep
import java.time.LocalDateTime

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumEleven {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 11 - while loops"))
                .reset()

            val scheduledDateTime = LocalDateTime.now().plusNanos(5000000)

            var counter = 0
            while (LocalDateTime.now().isBefore(scheduledDateTime)) {
                ConsolerizerComposer.outSpace().magenta("One beat")
                    .reset()
                counter++
                if (counter == 3) {
                    ConsolerizerComposer.outSpace()
                        .black()
                        .bgGreen("We've broken the loop!")
                        .reset()
                    break
                }
                sleep(1)
            }

            ConsolerizerComposer.outSpace().none()
                .green("We have counted $counter ❤️ beats")
                .newLine()
                .reset()
        }
    }
}