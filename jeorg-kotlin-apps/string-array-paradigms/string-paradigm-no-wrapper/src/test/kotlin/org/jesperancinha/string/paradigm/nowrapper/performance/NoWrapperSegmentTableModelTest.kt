package org.jesperancinha.string.paradigm.nowrapper.performance

import io.kotest.matchers.shouldBe
import org.jesperancinha.string.paradigm.api.Dependency
import org.jesperancinha.string.paradigm.api.SegmentTableModelIntReturn
import org.junit.jupiter.api.Test

class NoWrapperSegmentTableModelTest {
    private val segmentTableModelIntReturn by lazy {
        object : SegmentTableModelIntReturn {
            override fun addCombination(dependency: Dependency) = 123
            override fun clear() = Unit
        }
    }

    @Test
    fun `should add combination`(){
        segmentTableModelIntReturn.addCombination(object: Dependency(){
            override fun dependencyCount() = 10
            override fun getSubDependencyElement(index: Int) = "Ok"
            override fun startsWith(subDependencyElement: List<String>) = true
        }) shouldBe 123
    }

    @Test
    fun `should clear`(){
        segmentTableModelIntReturn.clear() shouldBe Unit
    }
}