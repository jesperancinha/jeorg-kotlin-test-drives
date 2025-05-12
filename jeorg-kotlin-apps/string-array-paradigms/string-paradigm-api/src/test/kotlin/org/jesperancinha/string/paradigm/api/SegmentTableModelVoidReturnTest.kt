package org.jesperancinha.string.paradigm.api

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SegmentTableModelVoidReturnTest {
    private val segmentTableModelVoidReturn by lazy {
        object : SegmentTableModelVoidReturn {
            override fun addCombination(dependency: Dependency) = Unit
            override fun clear() = Unit
        }
    }

    @Test
    fun `should add combination`() {
        segmentTableModelVoidReturn.addCombination(object : Dependency() {
            override fun dependencyCount() = 10
            override fun getSubDependencyElement(index: Int) = "Ok"
            override fun startsWith(subDependencyElement: List<String>) = true
        }) shouldBe Unit
    }

    @Test
    fun `should clear`() {
        segmentTableModelVoidReturn.clear() shouldBe Unit
    }
}