package org.jesperancinha.service

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import kotlin.test.Test

class RectangleTest {

    @Test
    fun `should validate rectangle in a horizontal way`() {
        Rectangle(
            Point(10, 10),
            Point(20, 10),
            Point(10, 12),
            Point(20, 12)
        ).validate().shouldBeTrue()
    }

    @Test
    fun `should not accept a square`() {
        Rectangle(
            Point(10, 10),
            Point(20, 10),
            Point(10, 20),
            Point(20, 20)
        ).validate().shouldBeFalse()
    }

    @Test
    fun `should not validate non-rectangular shape`() {
        Rectangle(
            Point(10, 10),
            Point(20, 10),
            Point(10, 12),
            Point(20, 5000)
        ).validate().shouldBeFalse()
    }

    @Test
    fun `should  validate regular auto calculated shape`() {
        Rectangle(
            Point(10, 10),
            Point(20, 10),
            Point(10, 12),
        ).validate().shouldBeTrue()
    }
}