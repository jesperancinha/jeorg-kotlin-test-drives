package org.jesperancinha.ktd.crums3

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
    }

    @Test
    fun `should test Monoid List to have a closure property`() {
        val treeCollection1 = listOf(
            Tree(leaves = listOf(Leaf())),
            Tree(leaves = listOf(Leaf(), Leaf()))
        ).map { it }
        (treeCollection1.shouldBeTypeOf<ArrayList<Tree>>() +
                treeCollection.shouldBeTypeOf<ArrayList<Tree>>())
            .shouldBeTypeOf<ArrayList<Tree>>() shouldHaveSize 12
    }

    @Test
    fun `should test Monoid List to have an associativity property`() {
        val a = listOf(Tree(leaves = 1.toLeaves()), Tree(leaves = 2.toLeaves()))
        val b = listOf(Tree(leaves = 1.toLeaves()), Tree(leaves = 2.toLeaves()))
        val c = listOf(Tree(leaves = 1.toLeaves()), Tree(leaves = 2.toLeaves()))
        ((a + b) + c) shouldBe (a + (b + c))
    }

    companion object {
        fun Int.toLeaves() = (1..this).map { Leaf() }
    }
}