package org.jesperancinha.ktd

class AtmMachine {

    val oldAtmMachine by lazy { OldAtmMachine() }

    fun getAllTheMoney(){
        oldAtmMachine.cashIn(null)
        oldAtmMachine.cashInJakartaAnnotation(null)
        oldAtmMachine.cashInNotNullJakartaValidation(null)
//        oldAtmMachine.cashInNotNullEclipse(null)
//        oldAtmMachine.cashInNotNullJetBrains(null)
        oldAtmMachine.cashInNotNullEclipse(10)
        oldAtmMachine.cashInNotNullJetBrains(10)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            AtmMachine().getAllTheMoney()
        }
    }

}