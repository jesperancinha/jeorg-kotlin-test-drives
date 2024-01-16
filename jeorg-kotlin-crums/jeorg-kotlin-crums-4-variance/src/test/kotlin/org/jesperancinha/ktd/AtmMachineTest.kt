package org.jesperancinha.ktd

import kotlin.test.Test

class AtmMachineTest {
    private val oldAtmMachine by lazy { OldAtmMachine() }

    @Test
    fun `should show compatibilities`() {
        oldAtmMachine.cashIn(null)
        oldAtmMachine.cashInJakartaAnnotation(null)
        oldAtmMachine.cashInNotNullJakartaValidation(null)
        oldAtmMachine.cashInNotNullEclipse(10)
        oldAtmMachine.cashInNotNullJetBrains(10)
    }
}