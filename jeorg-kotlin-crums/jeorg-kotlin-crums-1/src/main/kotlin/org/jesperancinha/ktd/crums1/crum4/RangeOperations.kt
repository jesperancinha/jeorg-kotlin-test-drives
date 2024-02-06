package org.jesperancinha.ktd.crums1.crum4

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class RangeOperations {
    companion object {
        private fun isValidArtifactId(s: String): Boolean {
            ConsolerizerComposer.outSpace()
                .magenta(s)
                .reset()
            if (s != "") {
                if (s in "a".."z") {
                    for (ch in s)
                        if (ch != '-' && ch != '_' && ch != '.' && !ch.isLetterOrDigit()) {
                            return false
                        }
                    return true
                }
            }
            return false
        }

        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 4 - Basic String comparison and range operations"))
                .blue("We check the artifactId's")
                .reset()
            println(isValidArtifactId("@@*&^sdkfnsdfsho"))
            println(isValidArtifactId("-i-do-something-not-good"))
            println(isValidArtifactId("good-module-1234_scala12"))
            println(isValidArtifactId("12312_-3343"))
            println(isValidArtifactId("dddff1234"))
            println(isValidArtifactId(".34234"))
        }

    }
}