package org.jesperancinha.ktd.crums2

import org.jesperancinha.console.consolerizer.console.Consolerizer

class AnnotationRead {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            Consolerizer.printRainbow(this::class.annotations.joinToString())
        }
    }
}