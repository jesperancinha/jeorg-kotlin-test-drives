package org.jesperancinha.ktd.crums3.visibility.paper3

import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility.PAPER_HEADLINES

open class Bird {
    private val privateReads = PAPER_HEADLINES
    protected fun protectedReads() = PAPER_HEADLINES
    fun publicReads() = PAPER_HEADLINES
    internal val internalReads = PAPER_HEADLINES
    fun fetchPrivateReads() = privateReads
}

class Parrot: Bird() {
    fun runPaper3() {
        protectedReads()
        internalReads
        publicReads()
        fetchPrivateReads()
    }
}
