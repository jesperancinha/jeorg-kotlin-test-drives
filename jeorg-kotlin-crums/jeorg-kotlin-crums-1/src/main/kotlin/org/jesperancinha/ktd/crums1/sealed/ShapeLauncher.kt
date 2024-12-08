package org.jesperancinha.ktd.crums1.sealed

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.crums1.sealed.package1.Circle
import org.jesperancinha.ktd.crums1.sealed.package1.Rectangle
import org.jesperancinha.ktd.crums1.sealed.package2.Cube

class ShapeLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace().magenta(Circle(1.0))
            ConsolerizerComposer.outSpace().magenta(Rectangle(1.0, 1.0))
            ConsolerizerComposer.outSpace().magenta(Cube())
        }

    }
}