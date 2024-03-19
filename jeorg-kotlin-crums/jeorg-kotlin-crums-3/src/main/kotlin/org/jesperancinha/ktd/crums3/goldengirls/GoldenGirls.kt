package org.jesperancinha.ktd.crums3.goldengirls

import org.jesperancinha.ktd.crums3.goldengirls.javaversion.*

data class GoldenGirls(
    var goldenGirl1: String = "Dorothy Zbornak",
    private val goldenGirl2: String = "Rose Nylund",
    private val goldenGirl3: String = "Blanche Devereaux",
    private val goldenGirl4: String = "Sophia Petrillo"
)

class GoldenGirlsLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val goldenGirls = GoldenGirls()
            val goldenGirls2 = GoldenGirls(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
            )
            val goldenGirlsJava = GoldenGirlsJava()
            val goldenGirlsLombok = GoldenGirlsLombok()
            val goldenGirlsLombok2 = GoldenGirlsLombok(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
            )
            val goldenGirlsRecord = GoldenGirlsRecord()
            val goldenGirlsRecord2 = GoldenGirlsRecord(                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo")
            println(goldenGirls)
            println(goldenGirls2)
            println(goldenGirlsJava)
            println(goldenGirlsLombok)
            println(goldenGirlsLombok2)
            println(goldenGirlsRecord)
            println(goldenGirlsRecord2)

            println(goldenGirls.goldenGirl1)
            println(goldenGirlsJava.goldenGirl1)
            println(goldenGirlsLombok.goldenGirl1)
            println(goldenGirlsRecord.goldenGirl1)

            GoldenGirlsJavaRunner.main(emptyArray())
            GoldenGirlsLombokRunner.main(emptyArray())
        }
    }
}