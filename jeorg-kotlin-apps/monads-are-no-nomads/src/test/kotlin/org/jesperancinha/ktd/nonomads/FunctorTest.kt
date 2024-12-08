package org.jesperancinha.ktd.nonomads

import arrow.core.Option
import arrow.core.Some
import arrow.core.raise.either
import io.kotest.inspectors.forAll
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.awt.Color

/**
 * The Arrow Project has had its ups and downs and massive changes are constantly happening
 * If you are not finding a particular code that you expected to find here, it is important to mention a few changes
 *
 * From Arrow 2.0.0 onwards, these packages do not exist anymore:
 *
 * import arrow.core.andThen
 * import arrow.core.compose
 *
 * This compromises some example code that used to be here, especially code related to functors, monoids and monads
 */
class FunctorTest {
    @Test
    fun `should not be a functor let`() {
        val newTree = treeCollection.let { it.map { tree: Tree -> tree.copy(leaves = listOf(Leaf())) } }
        newTree.shouldNotBeNull().shouldHaveSize(10).forAll {
            it.leaves.shouldHaveSize(1)
        }
    }

    @Test
    fun `should test composition with function calls and mapping function of a List`() {
        val newTree = treeCollection.map { it.leaves }.map { Tree(leaves = it) }
        newTree.shouldNotBeNull().shouldHaveSize(10)
        val f: (Tree) -> Tree = { Tree(leaves = (1..5).map { Leaf(color = Color.BLUE) }) }
        val g: (Tree) -> Tree = { Tree(leaves = (1..6).map { Leaf(color = Color.BLACK) }) }
        treeCollection.map{ g(f(it)) } shouldBe treeCollection.map(f).map(g)
        treeCollection.map(f).map(g).shouldForAll {
            it.leaves.shouldForAll { it.color shouldBe Color.BLACK }
        }
    }
    @Test
    fun `should test identity function of a List`() {
        treeCollection.map { it } shouldBe treeCollection
    }

    @Test
    fun `should test functor transformation with map`() {
        treeCollection.map { tree ->
            tree.copy(
                leaves = listOf(
                    Leaf(color = Color.PINK, length = 100)
                )
            )
        }.shouldHaveSize(10)
            .forAll { tree ->
                tree.should {
                    it.leaves.shouldHaveSize(1).first().shouldNotBeNull().should { leaf ->
                        leaf.length shouldBe 100
                        leaf.color shouldBe Color.PINK
                    }
                }
            }
    }


    @Test
    fun `should test functor transformation with Some map`() {
        val makeTreeEmpty: Option<(Tree) -> Tree> = Some { tree -> tree.copy(leaves = emptyList()) }
        val resultTree = makeTreeEmpty.map { it(Tree(20)) }.fold(
            ifSome = {
                it
            },
            ifEmpty = { Tree(leaves = (1..20).map { Leaf() }) }
        )
        resultTree.leaves.shouldBeEmpty()
    }

    @Test
    fun `should test functor transformation with Either map`() {
        val createNewEmptyTree = either<TreeError, Tree> {
            Tree(20).copy(leaves = emptyList())
        }
        createNewEmptyTree.fold(ifLeft = {
            fail("This should not have happened!")
        }, ifRight = {
            it.shouldNotBeNull().leaves.shouldBeEmpty()
        })
        val map = createNewEmptyTree.map { it.leaves }
        map.fold(ifLeft = {
            fail("This should not have happened")
        }, ifRight = {
            it.shouldBeEmpty()
        })
    }
}