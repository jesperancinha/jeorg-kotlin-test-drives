package org.jesperancinha.string.paradigm.performance2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * Lar Pereira is a fictional character used in many of my repositories and his sayings are used here to illustrate how the wrapper works
 * Fenestev is another fictional character and is the boss of Lars Pereira
 * Lucy Dalia is another fictional character who is also a colleague of Lars Pereira
 */
class ParadigmDependency2TableModelTest {

    @Test
    fun `should add combination correctly`() {
        val paradigmDependency2TableModel = ParadigmDependency2TableModel()
        val testCombination = ParadigmDependency2Impl(StringWrapper("Lucy: I guess now you know Fenestev;Lars: Yes, I know him now. I didn't believe you. Still unbelievable. I'm sorry I doubted you.;Lucy: It's alright, nobody believes this until they meet Fenestev"))
        paradigmDependency2TableModel.addCombination(testCombination)
        paradigmDependency2TableModel.columnCount shouldBe 3
        paradigmDependency2TableModel.rowCount shouldBe 1

    }

    @Test
    fun getRowCount() {
    }

    @Test
    fun getColumnCount() {
    }

    @Test
    fun getValueAt() {
    }

    @Test
    fun clear() {
    }
}