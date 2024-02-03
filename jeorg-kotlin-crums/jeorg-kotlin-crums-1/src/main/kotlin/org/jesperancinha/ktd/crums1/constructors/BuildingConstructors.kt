package org.jesperancinha.ktd.crums1.constructors

import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random

data class Pier(var name: String, var dockingStations: Int)

data class ImmutablePier(val name: String, var dockingStations: Int) {

    constructor(pierOrder: String) :
            this(
                pierOrder.split(":")[0],
                pierOrder.split(":")[1].toInt()
            )
}

data class ImmutableExtraPier(
    val name: String,
    val dockingStations: Int,
    val checkinTime: LocalDateTime = LocalDateTime.now()

) {

    constructor(pierOrder: String) :
            this(
                pierOrder.split(":")[0],
                pierOrder.split(":")[1].toInt()
            )

    val lotteryTicket: String

    val checkoutTime = checkinTime.plusDays(2)

    val id: UUID

    init {
        lotteryTicket = Random.nextInt(1000, 2000).toString()
        id = UUID.randomUUID()
        if (dockingStations == 0) {
            throw RuntimeException("Not able to get a free spot!")
        }
    }

}


class BuildingConstructors {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Pier("Olhão - Mercado do Peixe", 10))
            println(ImmutablePier("Olhão - Mercado do Peixe:10"))
            val immutableExtraPier = ImmutableExtraPier("Olhão - Mercado do Peixe:10")
            println(immutableExtraPier)
            println(immutableExtraPier.checkoutTime)
            println(immutableExtraPier.lotteryTicket)
            println(immutableExtraPier.id)
            runCatching {
                println(ImmutableExtraPier("Olhão - Mercado do Peixe:0"))
            }
        }
    }
}
