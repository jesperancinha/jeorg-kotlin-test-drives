package org.jesperancinha.ktd.crums2


class PrimitiveWrapping {
    companion object {
        @JvmInline
        value class Temperature(val celsius: Int)

        fun printTemperature(temp: Temperature) {
            println("Temperature: ${temp.celsius}°C")
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val temp = Temperature(25)
            printTemperature(temp)
        }
    }
}