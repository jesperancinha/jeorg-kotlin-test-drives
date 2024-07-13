package org.jesperancinha.ktd.crums3

import io.kotest.matchers.collections.shouldHaveSize
import java.awt.Color
import kotlin.test.Test

data class Tree(
    val leaves: List<Leaf> = emptyList()
)

data class Leaf(
    val color: Color = Color.GREEN,
    val length: Int = 10
)

val treeCollection = (1..10)
    .map { Tree((1..10).map { Leaf() }) }

class MonadsTest {

    @Test
    fun `should test the identity just function of the monad`() {
        val trees = listOf(Tree())
        trees.shouldHaveSize(1)
    }


    @Test
    fun `should test the bind function of the monad`() {
        val leaves = treeCollection.flatMap { it.leaves }
        leaves.shouldHaveSize(100)
    }
}