package com.jesperancinha.string.paradigm.api

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SegmentTableModelIntReturnTest {
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