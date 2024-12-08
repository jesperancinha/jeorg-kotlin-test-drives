package org.jesperancinha.ktd.crums1.interop

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class Archiver {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val archive = Archive()
            runCatching {
                val name: String = archive.name
                ConsolerizerComposer.outSpace().magenta(name)
            }.onFailure {
                ConsolerizerComposer.outSpace().red(it)
            }
            val safeName: String? = archive.name
            ConsolerizerComposer.outSpace().orange(safeName)
        }
    }
}