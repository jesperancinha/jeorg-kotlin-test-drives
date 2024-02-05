package org.jesperancinha.ktd.crums1.crum17

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class NumberConversions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 17 - Number conversions"))
                .none()
                .blue("123").red("is").blue("123".toInt()).red("as an integer. 32 bits, 4 bytes").newLine()
                .blue("123.123").red("is").blue("123.123".toFloat()).red("as a float. 32 bits, 4 bytes").newLine()
                .blue("8").red("is").blue("8".toByte()).red("as a byte. 8 bits, 1 byte").newLine()
                .blue("4234234.234234").red("is").blue("4234234.234234".toDouble()).red("as double, 64 bits, 8 bytes")
                .newLine()
                .blue("100").red("is").blue("100".toShort()).red("as short. 16 bits bits, 2 bytes")
                .newLine()
                .reset()
            ConsolerizerComposer.outSpace()
                .green("We can also make them fail and return null as default:")
                .none()
                .blue("200.777").red("is").blue("200.777".toIntOrNull()).red("as an integer. 32 bits, 4 bytes")
                .newLine()
                .blue("123a.123").red("is").blue("123a.123".toFloatOrNull()).red("as a float. 32 bits, 4 bytes")
                .newLine()
                .blue("200").red("is").blue("200".toByteOrNull()).red("as a byte. 8 bits, 1 byte").newLine()
                .blue("4234234a.234234").red("is").blue("4234234a.234234".toDoubleOrNull())
                .red("as double, 64 bits, 8 bytes").newLine()
                .blue("600000").red("is").blue("600000".toShortOrNull()).red("as short. 16 bits bits, 2 bytes")
                .newLine()
                .reset()
            ConsolerizerComposer.outSpace()
                .green("If we overflow them, we'll get infinity for the floating values:")
                .none()
                .blue("22739437942949324797979").red("is").blue("22739437942949324797979".toIntOrNull())
                .red("as an integer. 32 bits, 4 bytes").newLine()
                .blue("24823732790403207234079432790234079234907234907234907234907234079").red("is")
                .blue("24823732790403207234079432790234079234907234907234907234907234079".toFloatOrNull())
                .red("as a float. 32 bits, 4 bytes").newLine()
                .blue("129").red("is").blue("129".toByteOrNull()).red("as a byte. 8 bits, 1 byte").newLine()
                .blue("2.7976931348623157E308").red("is").blue("2.7976931348623157E308".toDoubleOrNull())
                .red("as double, 64 bits, 8 bytes").newLine()
                .blue("600000").red("is").blue("600000".toShortOrNull()).red("as short. 16 bits bits, 2 bytes")
                .newLine()
                .reset()

        }
    }
}