package org.jesperancinha.coffee.system.stats

class CoffeeStatsImpl(
    private val coffeSoldCash: Int, private val coffeSoldCard: Int,
    private val coffeExpresso: Int, private val coffeeMachiatto: Int, private val coffeCappuccino: Int,
    private val averageCoffee: Double, private val fastestProgrammerTime: Int,
    private val slowesProgrammerTime: Int
) {
    override fun toString(): String {
        return """
            Totalcoffee sold: ${coffeSoldCard + coffeSoldCash}
            payment by credit card: $coffeSoldCard
            payment by cash: $coffeSoldCash
            Total coffee dispensed:${coffeCappuccino + coffeeMachiatto + coffeExpresso}
            capuccinno: $coffeCappuccino
            machiatto: $coffeeMachiatto
            expresso: $coffeExpresso
            Average time spent: $averageCoffee
            Slowest EmployeeCallableImpl: $slowesProgrammerTime
            Fastest EmployeeCallableImpl: $fastestProgrammerTime
            """.trimIndent()
    }
}