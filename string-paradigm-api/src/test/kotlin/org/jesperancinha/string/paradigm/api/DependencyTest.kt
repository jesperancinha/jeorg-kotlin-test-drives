package org.jesperancinha.string.paradigm.api

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DependencyTest {

    private val dependency by lazy {
        object : Dependency() {
            override fun dependencyCount(): Int = 10
            override fun getSubDependencyElement(index: Int): String = "Ok"
            override fun startsWith(subDependencyElement: List<String>): Boolean = true
        }
    }

    @Test
    fun `should get dependency count`() {
        dependency.dependencyCount() shouldBe 10
    }

    @Test
    fun `should get sub dependency element`() {
        dependency.getSubDependencyElement(10) shouldBe "Ok"
    }

    @Test
    fun `should get starts with`() {
        dependency.startsWith(emptyList()).shouldBeTrue()
    }
}