package org.jesperancinha.ktd

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class DSLContractsSmartCasting {
    fun showContract(id: Long?): Long? {
        if (isValidIDE(id)) {
            doWithId(id)
        }
        return id
    }

    fun doWithId(id: Long): Long {
        println("Made contract with id $id")
        return id
    }

    @OptIn(ExperimentalContracts::class)
    fun isValidIDE(id: Long?): Boolean {
        contract { returns(true) implies (id != null) }
        return id != null && id != 0L
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println("${DSLContractsSmartCasting().showContract(123)}")
            println("${DSLContractsSmartCasting().showContract(null)}")
        }
    }
}
class ContractsExtension {
    fun showContract(id: Long?): Long? {
        if (id.isValidID()) {
            doWithId(id!!)
        }
        return id
    }

    fun doWithId(id: Long): Long {
        println("Made contract with id $id")
        return id
    }

    @OptIn(ExperimentalContracts::class)
    fun Long?.isValidID(): Boolean {
        contract { returns(true) implies (this@isValidID != null) }
        return this != null && this != 0L
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            DSLContractsSmartCasting.main()
            println("${DSLContractsSmartCasting().showContract(123)}")
            println("${DSLContractsSmartCasting().showContract(null)}")
        }
    }
}