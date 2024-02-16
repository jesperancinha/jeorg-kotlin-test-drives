package org.jesperancinha.ktd.crums3.goldengirls

import org.jesperancinha.ktd.crums3.goldengirls.javaversion.GoldenGirlsJava
import org.jesperancinha.ktd.crums3.goldengirls.javaversion.GoldenGirlsLombok
import org.jesperancinha.ktd.crums3.goldengirls.javaversion.GoldenGirlsRecord

data class GoldenGirls(
    val goldenGirl1: String = "Dorothy Zbornak",
    val goldenGirl2: String = "Rose Nylund",
    val goldenGirl3: String = "Blanche Devereaux",
    val goldenGirl4: String = "Sophia Petrillo"
)

class GoldenGirlsLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val goldenGirls = GoldenGirls()
            val goldenGirlsJava = GoldenGirlsJava()
            val goldenGirlsLombok = GoldenGirlsLombok()
            val goldenGirlsRecord = GoldenGirlsRecord()
            println(goldenGirls)
            println(goldenGirlsJava)
            println(goldenGirlsLombok)
            println(goldenGirlsRecord)

            println(goldenGirls.goldenGirl1)
            println(goldenGirlsJava.goldenGirl1)
            println(goldenGirlsLombok.goldenGirl1)
            println(goldenGirlsRecord.goldenGirl1)
        }
    }
}