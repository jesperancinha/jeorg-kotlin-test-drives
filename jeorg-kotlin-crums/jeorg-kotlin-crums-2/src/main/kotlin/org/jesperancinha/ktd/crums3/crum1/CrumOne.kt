package org.jesperancinha.ktd.crums3.crum1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class CrumOne {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 1 - Manipulating bits"))

            val number = 989898
            ConsolerizerComposer.outSpace()
                .green("We can manipulate bits and bytes")
                .magenta("Our number is: $number")
                .black().black()
                .bgBlue("Its binary representation is: ${Integer.toBinaryString(number)}")
                .reset().black()
                .blue("Its binary representation is: ${Integer.toBinaryString(number)}").black()
                .bgMagenta("We can xor it up ${number.xor(42)}. Yes, it's 42")
                .reset().black()
                .orange("having binary as ${Integer.toBinaryString(number.xor(42))}").black()
                .reset().black()
                .bgCyan("We can also and it up ${number.and(42)}. And of course... 42")
                .reset().black()
                .cyan("having binary as ${Integer.toBinaryString(number.and(42))}").black()
                .bgGreen("We can shift one bit to the left ${number.shl(1)}")
                .reset().black()
                .green("having binary as ${Integer.toBinaryString(number.shl(1))}").black()
                .bgGreen("or we can shift one bit to the right ${number.shr(1)}")
                .reset().black()
                .green("having binary as ${Integer.toBinaryString(number.shr(1))}").black()
                .bgOrange("We can also invert the bits ${number.inv()}")
                .reset().black()
                .orange("having binary as ${Integer.toBinaryString(number.inv())}").black()
                .reset().black()

        }
    }
}