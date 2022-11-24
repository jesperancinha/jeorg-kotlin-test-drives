package org.jesperancinha.ktd.microschip

data class Point(
    val x: Int,
    val y: Int
)

data class Wafer(
    val radius: Int
)

data class Measurement(
    val value:Double,
    val point: Point
)