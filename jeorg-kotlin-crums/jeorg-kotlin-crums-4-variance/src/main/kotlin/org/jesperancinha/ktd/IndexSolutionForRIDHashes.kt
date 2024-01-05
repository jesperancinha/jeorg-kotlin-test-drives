package org.jesperancinha.ktd

import java.util.concurrent.ConcurrentHashMap


class R {

    class ID {
        val telegram1: String = "telegram1"
        val telegram2: String = "telegram2"
    }

    companion object {
        val id:  ID = ID()
    }
}

class IndexSolutionForRIDHashes {

    companion object{
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val map = mutableMapOf(
                "R.id.telegram1" to R.id.telegram1,
                "R.id.telegram2" to R.id.telegram2,
                )

            for (i in 1..2) {
                println(map["R.id.chat$i"])
            }
        }
    }
}