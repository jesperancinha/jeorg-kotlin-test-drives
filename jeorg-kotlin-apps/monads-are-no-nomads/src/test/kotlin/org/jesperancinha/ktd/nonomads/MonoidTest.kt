package org.jesperancinha.ktd.nonomads

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.util.ArrayList
import kotlin.test.Test

class MonoidTest {

    @Test
    fun `should test Monoid List to have an identity`() {
        val emptyList = listOf<Tree>()
        emptyList.shouldBeEmpty()
        (treeCollection + emptyList) shouldBe (emptyList + treeCollection)
        (emptyList + treeCollection) shouldBe treeCollection
    }

    @Test
    fun `should test Monoid List to have a closure property`() {
        val preTreeCollection1 = listOf(
            Tree(leaves = listOf(Leaf())),
            Tree(leaves = listOf(Leaf(), Leaf()))
        )
        val treeCollection1 = preTreeCollection1.toList()
        println(preTreeCollection1.javaClass)
        println(treeCollection1.javaClass)
        (treeCollection1.shouldBeTypeOf<ArrayList<Tree>>() +
                treeCollection.shouldBeTypeOf<ArrayList<Tree>>())
            .shouldBeTypeOf<ArrayList<Tree>>() shouldHaveSize 12
    }

    @Test
    fun `should test Monoid List to have an associativity property`() {
        val treeCollectionA = listOf(Tree(leaves = 1.toLeaves()), Tree(leaves = 2.toLeaves()))
        val treeCollectionB = listOf(Tree(leaves = 1.toLeaves()), Tree(leaves = 2.toLeaves()))
        val treeCollectionC = listOf(Tree(leaves = 1.toLeaves()), Tree(leaves = 2.toLeaves()))
        ((treeCollectionA + treeCollectionB) + treeCollectionC) shouldBe (treeCollectionA + (treeCollectionB + treeCollectionC))
    }

    companion object {
        fun Int.toLeaves() = (1..this).map { Leaf() }
    }
}