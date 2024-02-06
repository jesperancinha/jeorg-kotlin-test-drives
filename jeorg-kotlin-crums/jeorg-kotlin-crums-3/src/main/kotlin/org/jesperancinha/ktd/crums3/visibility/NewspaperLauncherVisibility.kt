package org.jesperancinha.ktd.crums3.visibility

import org.jesperancinha.ktd.crums3.visibility.paper1.runPaper1
import org.jesperancinha.ktd.crums3.visibility.paper2.runPaper2
import org.jesperancinha.ktd.crums3.visibility.paper3.Parrot
import org.jesperancinha.ktd.crums3.visibility.paper4.runPaper4
import org.jesperancinha.ktd.crums3.visibility.paper5.runPaper5
import org.jesperancinha.ktd.crums3.visibility.paper6.PaperClass6

object NewspaperLauncherVisibility {
    @JvmStatic
    fun main(args: Array<String> = emptyArray()) {
        runPaper1()
        runPaper2()
        Parrot().runPaper3()
        runPaper4()
        runPaper5()
        val paperClass6 = PaperClass6()
        paperClass6.publicReads()
        paperClass6.internalReads
        paperClass6.runPaper6()
    }

    const val PAPER_HEADLINES = "Breaking News!"
}