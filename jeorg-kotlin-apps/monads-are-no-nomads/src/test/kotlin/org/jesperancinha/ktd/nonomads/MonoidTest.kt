package org.jesperancinha.ktd.nonomads

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.awt.Color
import java.util.ArrayList
import kotlin.test.Test

class MonoidTest {

    @Test
    fun `should test Monoid List to have an identity`() {
        val emptyList = emptyList<Tree>()
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
        ((treeCollectionA + treeCollectionB) + treeCollectionC) shouldBe
                (treeCollectionA + (treeCollectionB + treeCollectionC))
    }


    @Test
    fun `should test Custom Monoid Option to have an identity`() {
        val optionTreeOneLeaf: Option<Tree> = Some(Tree(leaves = listOf(Leaf())))
        (emptyOptionTree() + optionTreeOneLeaf) shouldBe (optionTreeOneLeaf + emptyOptionTree())
        (optionTreeOneLeaf + emptyOptionTree()) shouldBe (optionTreeOneLeaf)
    }

    @Test
    fun `should test Custom Monoid Option to have closure property`() {
        val optionTreeColorBlack: Option<Tree> = Some(Tree(leaves = listOf(Leaf(color = Color.BLACK))))
        val optionTreeColorOrange: Option<Tree> = Some(Tree(leaves = listOf(Leaf(color = Color.ORANGE))))
        (optionTreeColorBlack.shouldBeTypeOf<Some<Tree>>() +
                optionTreeColorOrange.shouldBeTypeOf<Some<Tree>>())
            .shouldBeTypeOf<Some<Tree>>()
    }

    @Test
    fun `should test Custom Monoid Option to have an associativity property`() {
        val optionTreeColorBlack: Option<Tree> = Some(Tree(leaves = listOf(Leaf(color = Color.BLACK))))
        val optionTreeColorOrange: Option<Tree> = Some(Tree(leaves = listOf(Leaf(color = Color.ORANGE))))
        val optionTreeColorPink: Option<Tree> = Some(Tree(leaves = listOf(Leaf(color = Color.PINK))))
        ((optionTreeColorBlack + optionTreeColorOrange) + optionTreeColorPink) shouldBe
                (optionTreeColorBlack + (optionTreeColorOrange + optionTreeColorPink))
    }

    companion object {
        fun Int.toLeaves() = (1..this).map { Leaf() }

        fun emptyOptionTree() = None

        private operator fun Option<Tree>.plus(optionTreeOneLeaf: Option<Tree>): Option<Tree> =
            this.takeIf { it == None }?.run { optionTreeOneLeaf }
                ?: this.map { it.copy(leaves = it.leaves + optionTreeOneLeaf.fold({ emptyList() }) { opt -> opt.leaves }) }

    }
}


