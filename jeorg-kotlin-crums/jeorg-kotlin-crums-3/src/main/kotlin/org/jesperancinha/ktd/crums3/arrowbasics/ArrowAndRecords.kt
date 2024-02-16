package org.jesperancinha.ktd.crums3.arrowbasics

import arrow.core.continuations.nullable
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

data class Record(
    val title: String?,
    val band: String,
    val year: Long
)


class ArrowRecord {

    companion object {
        private val logger = object {
            fun info(logText: String) = ConsolerizerComposer.out().yellow(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .green(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 4 - Arrow Basics")

            logger.info("This album is ${createAlbumOfThe90s("Cesaria Evora", "La Diva Aux Pieds Nus", 1989)}")
            logger.info("This album is ${createAlbumOfThe90s("Cesaria Evora", "Live À L'Olympia", 1996)}")
            logger.info("This album doesn't exist but it goes through ${createAlbumOfThe90s("Cesaria Evora", "La Diva Aux Pieds Nus", 1997)}. The true year is 1989")
        }

    }
}

private fun createAlbumOfThe90s(band: String?, title: String?, year: Long) =nullable.eager {
    Record(
        band = band.bind(),
        title = (if(year >= 1990) title else null).bind(),
        year = year
    )
}