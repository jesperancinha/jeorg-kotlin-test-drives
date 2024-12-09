package org.jesperancinha.ktd.crums2.fields

class Example {
    @JvmField
    val someValue: String = "Hello"
}

class JvmFields {

    companion object {
        @JvmField
        val CONSTANT = "I am constant"

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val clientApplication = ClientApplication()
            clientApplication.runClient()
        }
    }
}