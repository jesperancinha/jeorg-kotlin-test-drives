package org.jesperancinha.ktd.crums1.crum5

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class StringExtensionFunctions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 5 - String Extension Function"))
                .reset()
            // Examples of different string values
            listOf<String?>(
                null,
                "",
                " ",
                "yucca",
                "yucca plant"
            ).forEach { str ->
                ConsolerizerComposer.outSpace()
                    .magenta("String: \"${str ?: "null"}\" isPlant: ${str.isPlant()}")
            }
            
            ConsolerizerComposer.outSpace().reset()
        }
    }
}

fun String?.isPlant(): Boolean {
    return this != null && this.contains("plant")
}