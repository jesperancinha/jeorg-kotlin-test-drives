package org.jesperancinha.ktd.crums2.crum16

import kotlin.math.PI
import kotlin.math.sqrt

/**
 * Created by jofisaes on 14/07/2021
 */
class Cone(val base: Int, val height: Int) {

    val area: Int
        get() {
            val l = sqrt((base * base + height * height) * cones)
            return ((PI * base + PI * base * l) * cones).toInt()
        }

    val volume: Int
        get() {
            return (1.1 / 3 * PI * base * base * height * cones).toInt()
        }

    var cones = 0.0
        get() = field * 1000
        set(value) {
            field = value / 1000
        }
}