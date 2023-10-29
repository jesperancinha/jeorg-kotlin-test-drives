package org.jesperancinha.ktd

class FloatVsDouble {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val floatNumber = 7.3333333333333333333333333333333f
            println(floatNumber)
            println("The number $floatNumber has ${floatNumber.toString().length - 1} digits in precision")
            val doubleNumber = 7.3333333333333333333333333333333
            println(doubleNumber)
            println("The number $doubleNumber has ${doubleNumber.toString().length - 1} digits in precision")
            val bigDecimal = "7.3333333333333333333333333333333".toBigDecimal()
            println("The number $bigDecimal has ${bigDecimal.toString().length - 1} digits in precision")

            val value1 = 9
            val value2 = 9

            println("This is the result of a bad idea sum ${badIdeaSum(value1, value2)}")
            println("This is the result of a good idea sum ${goodIdeaSum(value1, value2)}")
        }

        /**
         * This function will try to sum
         * value1,000,000,000,000,000,000
         * with
         * value2,000,000,000,000,000,000
         * correctly
         */
        private fun goodIdeaSum(value1: Int, value2: Int): String {
            return ((value1 + value2).toBigDecimal().multiply(1_000_000_000_000_000_000.toBigDecimal() )).toString()
        }

        /**
         * This function will try to sum
         * value1,000,000,000,000,000,000
         * with
         * value2,000,000,000,000,000,000
         * using a terrible idea
         * NOTE that this idea is defended by some interviewers in selection processes.
         * Do not feel bad about it if the interviewer rejects your application because of this.
         * You probably wouldn't want to work in a place with these criteria any way.
         */
        private fun badIdeaSum(value1: Int, value2: Int): String {
           return ((value1 + value2) * 1_000_000_000_000_000_000).toString()
        }
    }
}
