package org.jesperancinha.ktd.crums3.multiple

import org.jesperancinha.ktd.crums3.multiple.cat1.CatTalker as BobCat
import org.jesperancinha.ktd.crums3.multiple.cat2.CatTalker as TimCat
import org.jesperancinha.ktd.crums3.multiple.cat3.CatTalker as Geoffrey

class MultipleSameTypeCats {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println(BobCat().getSound())
            println(TimCat().getSound())
            println(Geoffrey().getSound())
        }
    }
}