package org.jesperancinha.ktd.arrow.optics.crums1

import kotlinx.coroutines.runBlocking
import org.jesperancinha.aktd.crums1.crum2.Effects
import org.jesperancinha.ktd.arrow.optics.crums1.crum1.MailLauncher
import org.jesperancinha.ktd.arrow.optics.crums1.crum2.XmasEffects
import org.jesperancinha.ktd.arrow.optics.crums1.crum3.Optics
import org.jesperancinha.ktd.arrow.optics.crums1.crum5.ArrowAnalysis

fun main() = runBlocking{
    MailLauncher.main()
    XmasEffects.main()
    Optics.main()
    Effects.main()
    ArrowAnalysis.main()
}