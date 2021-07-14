package org.jesperancinha.ktd.crums2.crum16

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumSixteen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 16 - Getters and Setters"))
                .reset()

            val cone = Cone(2, 10)

            ConsolerizerComposer.outSpace()
                .green("Currently there is no volume and no area because there are no cones within the cone")
                .green(cone.area)
                .green(cone.volume)
                .green(cone.height)
                .green(cone.base)
                .reset()

            cone.cones = 1.0

            ConsolerizerComposer.outSpace()
                .green("Having one cone")
                .green(cone.area)
                .green(cone.volume)
                .green(cone.height)
                .green(cone.base)
                .reset()

            cone.cones = 2.0

            ConsolerizerComposer.outSpace()
                .green("Having one cone")
                .green(cone.area)
                .green(cone.volume)
                .green(cone.height)
                .green(cone.base)
                .reset()
        }
    }
}