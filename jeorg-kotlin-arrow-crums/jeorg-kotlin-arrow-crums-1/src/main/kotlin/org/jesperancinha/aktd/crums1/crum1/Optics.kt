package org.jesperancinha.aktd.crums1.crum1

import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

@optics
data class Bytes(val byteNumber: Long) {
    companion object
}

@optics
sealed class Box {

    @optics
    data class DataBox(val items: Long, val bytes: Bytes = Bytes(10)) : Box() {
        companion object
    }
    @optics
    data class SnowDataBox(val items: Long, val bytes: Bytes = Bytes(100000)) : Box(){
        companion object
    }
    companion object
}

class Optics {
    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().red(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 1 - Basic Optics")

            val bytesLens = Box.dataBox.bytes
            val liftByteSetterPrism = bytesLens.lift { it.copy(byteNumber = 1) }

            val dataBox = Box.DataBox(10)

            val dataBox2 = liftByteSetterPrism(dataBox)

            logger.info("Databox 1 $dataBox")
            logger.info("Databox 2 $dataBox2")
        }
    }
}