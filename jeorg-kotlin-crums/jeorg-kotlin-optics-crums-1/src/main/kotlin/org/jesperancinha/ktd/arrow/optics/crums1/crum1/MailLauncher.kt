package org.jesperancinha.ktd.arrow.optics.crums1.crum1

import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

data class ClientInfo(
    val name: String,
    val address: String
) {
    companion object
}

@optics
sealed class Mail {
    companion object;

    @optics
    data class Parcel(val clientInfo: ClientInfo) : Mail() {
        companion object
    }
}

class MailLauncher {
    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().green(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Optics Crum 1 - Basic Optics and Prisms")
            val clientInfoLens = Mail.parcel.clientInfo

            val mailPrism = clientInfoLens.lift { it.copy(name = "Lucy Dalia") }

            val parcelToTheWrongDestination =
                Mail.Parcel(
                    ClientInfo(
                        name = "Lindsey Boulevard",
                        address = "The best house in the world! AABBCCDD123123 Mansions by the Ocean"
                    )
                )
            logger.info(parcelToTheWrongDestination)

            val parcelToTheRightDestination = mailPrism(parcelToTheWrongDestination)
            logger.info(parcelToTheRightDestination)
        }
    }
}