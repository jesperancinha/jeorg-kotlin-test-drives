package org.jesperancinha.ktd.crums2.crum2

/**
 * Created by jofisaes on 07/07/2021
 */
data class Building(
    val bricks: Long,
    val goal: String,
    val concreteKg: Double,
    val steelKg: Double
) {
    fun buildIt(): String = "The building $goal, is done!"

    fun concreteWeight(): String = "${concreteKg / 1000} metric tons"

    fun steelWeight(): String = "${steelKg / 1000} metric tons"

    fun brickStats(): String = "built with $bricks bricks"

    fun printReport(): Unit = println("I'm sorry, but this time I have nothing to print!")
}