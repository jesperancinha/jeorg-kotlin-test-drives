package org.jesperancinha.ktd.crums1

import java.math.RoundingMode
import java.text.DecimalFormat

private const val FORMAT_TEST_ONE = "#,###.##############"

class NumbersConversion {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val decimalFormat = DecimalFormat(FORMAT_TEST_ONE)
            decimalFormat.setRoundingMode(RoundingMode.CEILING)
            println(decimalFormat.format("987654321".toDouble()))
        }
    }
}