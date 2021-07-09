package org.jesperancinha.ktd.crums2.crum5

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 07/07/2021
 */
class CrumFive {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 5 - Function as a receiver"))
                .reset()

            showMeTheTicket("This is Ma message") { "This is Ma Function" }
        }

        private fun <T> showMeTheTicket(test: String, showFunction: () -> T): T {
            ConsolerizerComposer.outSpace()
                .magenta("Hey!")
                .magenta("Your test:")
                .magenta(test)
                .magenta("This is your message:")
                .magenta(showFunction())
            return showFunction()
        }
    }
}


