package org.jesperancinha.ktd.crums3.visibility.paper2

import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility.PAPER_HEADLINES

open class PaperClass2 {
    private val privateReads = PAPER_HEADLINES
    protected open fun protectedReads() = PAPER_HEADLINES
    fun publicReads() = PAPER_HEADLINES
    internal val internalReads = PAPER_HEADLINES
    fun fetchPrivateReads() = privateReads
}

class Volume2: PaperClass2()

class Volume2A : PaperClass2() {
    public override fun protectedReads() = super.protectedReads()
}
fun runPaper2() {
    val volume2 = Volume2()
    volume2.internalReads
    volume2.publicReads()
    volume2.fetchPrivateReads()
    Volume2A().protectedReads()
}
