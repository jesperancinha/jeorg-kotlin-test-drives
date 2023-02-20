package org.jesperancinha.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.math.MathContext.DECIMAL64

class Point(
    val x: Long,
    val y: Long,
) {
    fun makePoint(p2: Point, p3: Point) =
        Point(
            x + (p2.x - x) - (p3.x - x),
            y + (p3.y - y) - (p2.y - y)
        )
}

class Rectangle(
    private val p1: Point,
    private val p2: Point,
    private val p3: Point,
    private val p4: Point,
) {
    constructor(p1: Point, p2: Point, p3: Point) : this(p1, p2, p3, calculateMissingPoint(p1, p2, p3))

    fun validate() = (
            distances(p1, p2, p3, p4) + distances(p2, p3, p1, p4) +
                    distances(p3, p4, p2, p1) + distances(p4, p1, p2, p3))
        .also {
            if (it.size != 3) {
                logger.error(
                    "Rectangle is not valid!\nThere are no 3 common distances between one point and the other three:\n{}",
                    it
                )
            }
        }.size == 3

    private fun distances(p1: Point, p2: Point, p3: Point, p4: Point) =
        setOf(distance(p1, p2), distance(p1, p3), distance(p1, p4))


    companion object {
        val logger: Logger = LoggerFactory.getLogger(Rectangle::class.java)


        /**
         * Short summary of calculations
         * h^2 = c1^2 + c2^2
         * c1^2 = h^2 - c2^2
         * c1 = sqrt(h^2 - c2^2)
         * c2 = sqrt(h^2 - c1^2)
         */
        fun calculateMissingPoint(p1: Point, p2: Point, p3: Point): Point = when (calculateMiddlePoint(p1, p2, p3)) {
                p1 -> p1.makePoint(p2, p3)
                p2 -> p2.makePoint(p3, p1)
                p3 -> p3.makePoint(p1, p2)
                else -> throw RuntimeException("Invalid Point Found! This should not happen")
            }

        private fun calculateMiddlePoint(p1: Point, p2: Point, p3: Point): Point = setOf(p1, p2, p3).minBy {
            when (it) {
                p1 -> maxOf(distance(p1, p2), distance(p1, p3))
                p2 -> maxOf(distance(p2, p1), distance(p2, p3))
                p3 -> maxOf(distance(p3, p1), distance(p3, p2))
                else -> throw RuntimeException("Invalid Point Found! This should not happen")
            }
        }

        fun distance(pa: Point, pb: Point): BigDecimal =
            (pb.x - pa.x).toBigDecimal().pow(2).add(
                (pb.y - pa.y).toBigDecimal().pow(2)
            ).sqrt(DECIMAL64)

    }
}