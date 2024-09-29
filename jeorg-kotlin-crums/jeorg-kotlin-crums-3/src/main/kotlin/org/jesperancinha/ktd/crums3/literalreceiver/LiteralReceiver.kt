package org.jesperancinha.ktd.crums3.literalreceiver

class LiteralReceiver {

    companion object {
        val stringTest: String.() -> String = {
            "$this wow!"
        }


        @JvmStatic
        fun main(array: Array<String> = emptyArray()) {
            val result = "Cat".stringTest()
            println(result)
        }

    }
}