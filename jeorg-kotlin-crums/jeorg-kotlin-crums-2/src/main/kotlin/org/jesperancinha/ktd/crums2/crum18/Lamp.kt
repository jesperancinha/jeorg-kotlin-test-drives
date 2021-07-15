package org.jesperancinha.ktd.crums2.crum18

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.time.LocalDateTime

/**
 * Created by jofisaes on 15/07/2021
 *
 * Note that with a data class, val/var are mandatory for the constructor
 */
class Lamp(
    val theme: String, height: Int,
) {

    lateinit var mainColor: String

    val height by lazy {
        ConsolerizerComposer.outSpace()
            .green("${LocalDateTime.now()} - You've setup the height just now!")
            .reset()
        height
    }

    val base: Int

    constructor(theme: String) : this(theme, 100) {
        ConsolerizerComposer.outSpace()
            .green("${LocalDateTime.now()} - As a standard, base and height have 100. With this constructor we make 100 by default be the value of height just as the mandatory value for base")
            .reset()
    }

    init {
        ConsolerizerComposer.outSpace()
            .green("${LocalDateTime.now()} - We are going to initialize the base")
            .yellow("The variable theme = $theme is already set.")
            .reset()
        base = 100
    }

    fun setupColor(color: String) {
        ConsolerizerComposer.outSpace()
            .green("${LocalDateTime.now()} - We are going to setup the color")
            .reset()
        mainColor = color
    }

    override fun toString(): String {
        return theme + mainColor + height + base
    }


}