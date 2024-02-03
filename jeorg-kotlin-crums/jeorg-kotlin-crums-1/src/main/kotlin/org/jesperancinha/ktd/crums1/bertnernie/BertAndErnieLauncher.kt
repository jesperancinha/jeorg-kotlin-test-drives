package org.jesperancinha.ktd.crums1.bertnernie

class BertAndErnieLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(ImmutableBertAndErnieJava().helloBert())
            println(ImmutableBertAndErnieJava().helloBertClean())
            println(ImmutableBertAndErnie().helloBert())
            println(ImmutableBertAndErnie().helloBertClean())
        }
    }
}