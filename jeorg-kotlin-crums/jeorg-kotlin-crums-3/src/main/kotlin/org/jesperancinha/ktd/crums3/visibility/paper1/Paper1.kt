package org.jesperancinha.ktd.crums3.visibility.paper1

import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility.PAPER_HEADLINES

private const val privateReads = PAPER_HEADLINES
fun publicReads() = PAPER_HEADLINES
internal const val internalReads = PAPER_HEADLINES

fun runPaper1() {
    println(privateReads)
    println(internalReads)
    println(publicReads())
}