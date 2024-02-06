package org.jesperancinha.ktd.crums3.visibility.paper4

import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility.PAPER_HEADLINES

open class Paper4 {
    @SuppressWarnings("unused")
    private val privateReads = PAPER_HEADLINES
    @SuppressWarnings("unused")
    protected open fun protectedReads() = PAPER_HEADLINES
    fun publicReads() = PAPER_HEADLINES
    internal val internalReads = PAPER_HEADLINES

    fun fetchPrivateReads() = privateReads
}


class Paper4A : Paper4() {
    public override fun protectedReads() = super.protectedReads()
}

fun runPaper4() {
    val paper4 = Paper4()
    paper4.publicReads()
    paper4.internalReads
    paper4.fetchPrivateReads()
    Paper4A().protectedReads()
}
