package org.jesperancinha.ktd.crums1.bertnernie

class BertAndErnieLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(ImmutableBertAndErnieJava().helloBertAndErnie())
            println(ImmutableBertAndErnieJava().helloBertAndErnieClean())
            println(ImmutableBertAndErnie().helloBertAndErnie())
            println(ImmutableBertAndErnie().helloBertAndErnieClean())
        }
    }
}