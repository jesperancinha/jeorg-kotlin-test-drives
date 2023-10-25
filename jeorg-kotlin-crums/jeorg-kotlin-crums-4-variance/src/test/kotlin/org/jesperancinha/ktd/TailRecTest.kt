package org.jesperancinha.ktd

import io.kotest.matchers.shouldBe
import org.jesperancinha.ktd.TailRec.Companion.fibonnaci
import org.jesperancinha.ktd.TailRec.Companion.fibonnaciOptimized
import kotlin.test.Test

class TailRecTest {

    @Test
    fun `should calculate 1 element fibonacci`() {
        fibonnaci(1) shouldBe 0
        fibonnaciOptimized(1) shouldBe 0
    }

    @Test
    fun `should calculate 2 element fibonacci`() {
        fibonnaci(2) shouldBe 1
        fibonnaciOptimized(2) shouldBe 1
    }

    @Test
    fun `should calculate 3 element fibonacci`() {
        fibonnaci(3) shouldBe 1
        fibonnaciOptimized(3) shouldBe 1
    }

    @Test
    fun `should calculate 4 element fibonacci`() {
        fibonnaci(4) shouldBe 2
        fibonnaciOptimized(4) shouldBe 2
    }

    @Test
    fun `should calculate 5 element fibonacci`() {
        fibonnaci(5) shouldBe 3
        fibonnaciOptimized(5) shouldBe 3
    }

    @Test
    fun `should calculate 9 element fibonacci`() {
        fibonnaci(9) shouldBe 21
        fibonnaciOptimized(9) shouldBe 21
    }
}