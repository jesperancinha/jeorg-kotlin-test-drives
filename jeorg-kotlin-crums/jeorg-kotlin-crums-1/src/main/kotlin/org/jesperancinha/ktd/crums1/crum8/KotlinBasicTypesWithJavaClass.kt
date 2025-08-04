package org.jesperancinha.ktd.crums1.crum8

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 15/06/2021
 */
class KotlinBasicTypesWithJavaClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 8 - Kotlin basic types using javaClass"))
                .reset()
            val typeInt = 5
            println(typeInt.javaClass)
            ConsolerizerComposer.outSpace()
                .none()
                .green("Type of %d", typeInt).blue("is %s", typeInt.javaClass)
                .newLine()
                .green("The minimum value of %s", typeInt.javaClass).blue("is %s", Int.MIN_VALUE)
                .newLine()
                .green("And the maximum value of %s", typeInt.javaClass).blue("is %s", Int.MAX_VALUE)
                .newLine()
                .reset()
        }
    }
}