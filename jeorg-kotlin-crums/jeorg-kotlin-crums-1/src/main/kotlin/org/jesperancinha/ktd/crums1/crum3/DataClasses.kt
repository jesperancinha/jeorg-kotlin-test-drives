package org.jesperancinha.ktd.crums1.crum3

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class DataClasses {
    companion object {
        fun main(args: Array<String> = emptyArray()) {

            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 3 - Data Classes, equals, hash code and toString"))
                .reset()

            val carJava1 = Car("Fiat", "Panda")
            val carJava2 = Car("Fiat", "Panda")
            val oldCarJava1 = OldCar("Calhambeque", "Bip Bip")
            val oldCarJava2 = OldCar("Calhambeque", "Bip Bip")
            val kCarJava1 = KCar("Fiat", "Panda")
            val kCarJava2 = KCar("Fiat", "Panda")
            val kOldCarJava1 = KOldCar("Calhambeque", "Bip Bip")
            val kOldCarJava2 = KOldCar("Calhambeque", "Bip Bip")

            print("Car Java 1 $carJava1\n")
            print("Car Java 2 $carJava2\n")
            print("Old Car Java 2 $oldCarJava1\n")
            print("Old Car Java 2 $oldCarJava2\n")
            print("KCar Kotlin 1 $kCarJava1\n")
            print("KCar Kotlin 2 $kCarJava2\n")
            print("Old KCar Kotlin 2 $kOldCarJava1\n")
            print("Old KCar Kotlin 2 $kOldCarJava2\n")

        }
    }
}
