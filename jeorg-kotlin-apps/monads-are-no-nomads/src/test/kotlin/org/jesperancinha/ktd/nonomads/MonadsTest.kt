package org.jesperancinha.ktd.nonomads

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.awt.Color
import kotlin.test.Test

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

    @Test
    fun `should test left identity of the Monad`() {
        val tree1 = Tree()
        val f: (Tree) -> List<Tree> = { x -> listOf(Tree()) }
        listOf(tree1).flatMap(f) shouldBe f(tree1)
    }

    @Test
    fun `should test right identity of the Monad`() {
        val tree1 = Tree()
        val trees: List<Tree> = listOf(tree1)
        trees.flatMap { listOf(it) } shouldBe trees
    }

    @Test
    fun `should test associativity of the Monad`() {
        val tree1 = Tree()
        val trees: List<Tree> = listOf(tree1)
        val f: (Tree) -> List<Tree> = { x -> listOf(x) }
        val g: (Tree) -> List<Tree> = { x -> listOf(x.copy(leaves = listOf(Leaf()))) }
        trees.flatMap(f).flatMap(g) shouldBe trees.flatMap { x -> f(x).flatMap(g) }
    }
}