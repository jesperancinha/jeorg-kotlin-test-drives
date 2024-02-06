package org.jesperancinha.ktd.crums3.visibility.paper6

import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility.PAPER_HEADLINES

open class PaperClass6 {
    private val privateReads = PAPER_HEADLINES
    protected open fun protectedReads() = PAPER_HEADLINES
    fun publicReads() = PAPER_HEADLINES
    internal val internalReads = PAPER_HEADLINES

    fun runPaper6() {
        privateReads
        protectedReads()
        internalReads
        publicReads()
        Volume6().protectedReads()

    }
}

class Volume6: PaperClass6() {
    public override fun protectedReads() = super.protectedReads()

}

