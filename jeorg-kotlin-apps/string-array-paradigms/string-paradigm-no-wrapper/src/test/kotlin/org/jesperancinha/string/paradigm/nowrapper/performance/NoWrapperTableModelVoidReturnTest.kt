package org.jesperancinha.string.paradigm.nowrapper.performance

import io.kotest.matchers.shouldBe
import org.jesperancinha.string.paradigm.api.Dependency
import org.jesperancinha.string.paradigm.api.SegmentTableModelVoidReturn
import org.junit.jupiter.api.Test

class NoWrapperTableModelVoidReturnTest {
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