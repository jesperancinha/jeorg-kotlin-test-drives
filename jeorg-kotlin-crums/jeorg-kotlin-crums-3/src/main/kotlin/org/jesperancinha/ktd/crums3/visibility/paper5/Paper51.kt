package org.jesperancinha.ktd.crums3.visibility.paper5

import org.jesperancinha.ktd.crums3.visibility.NewspaperLauncherVisibility.PAPER_HEADLINES

private const val privateReads = PAPER_HEADLINES
fun publicReads5() {}
internal const val internalReads = PAPER_HEADLINES
fun fetchPrivateReads() = privateReads
