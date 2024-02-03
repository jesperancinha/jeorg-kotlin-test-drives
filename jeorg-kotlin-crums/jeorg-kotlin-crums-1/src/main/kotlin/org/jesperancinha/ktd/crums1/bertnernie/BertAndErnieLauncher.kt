package org.jesperancinha.ktd.crums1.bertnernie

class BertAndErnieLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(ImmutableBertAndErnie().helloBert())
            println(ImmutableBertAndErnieJava().helloBert())
        }
    }
}