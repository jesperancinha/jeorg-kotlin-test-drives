package org.jesperancinha.ktd.arrow.optics.crums1

import kotlinx.coroutines.runBlocking
import org.jesperancinha.ktd.arrow.optics.crums1.crum1.MailLauncher
import org.jesperancinha.ktd.arrow.optics.crums1.crum2.XmasEffects

fun main() = runBlocking{
    MailLauncher.main()
    XmasEffects.main()
}