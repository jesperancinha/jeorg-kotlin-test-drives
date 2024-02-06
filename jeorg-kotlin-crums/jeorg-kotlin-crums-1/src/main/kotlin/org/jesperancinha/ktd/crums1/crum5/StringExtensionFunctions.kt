package org.jesperancinha.ktd.crums1.crum5

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class StringExtensionFunctions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 5 - String Extension Function"))
                .reset()
            val s1: String? = null
            val s2: String? = ""
            val s3: String? = " "
            val s4 = "yucca"
            val s5 = "yucca plant"
            ConsolerizerComposer.outSpace()
                .magenta(s1?.isPlant())
                .magenta(s2?.isPlant())
                .magenta(s3.isPlant())
                .magenta(s4.isPlant())
                .magenta(s5.isPlant())
                .reset()
        }
    }
}

fun String?.isPlant(): Boolean {
    return this != null && this.contains("plant")
}