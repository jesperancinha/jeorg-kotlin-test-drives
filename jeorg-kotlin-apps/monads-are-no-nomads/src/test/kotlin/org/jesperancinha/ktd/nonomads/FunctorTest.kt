package org.jesperancinha.ktd.nonomads

import arrow.core.Option
import arrow.core.Some
import arrow.core.andThen
import arrow.core.compose
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.awt.Color

class FunctorTest {
    @Test
    fun `should test functor transformation with map`() {
        treeCollection.map { tree ->
            tree.copy(
                leaves = listOf(
                    Leaf(color = Color.PINK, length = 100)
                )
            )
        }
            .shouldHaveSize(10)
            .forEach { tree ->
                tree.should {
                    it.leaves.shouldHaveSize(1)
                        .first()
                        .shouldNotBeNull()
                        .should { leaf ->
                            leaf.length shouldBe 100
                            leaf.color shouldBe Color.PINK
                        }
                }
            }
    }


    @Test
    fun `should test functor transformation with Some map`() {
        val someFunction: Option<(Tree) -> Tree> = Some { tree -> tree.copy(leaves = emptyList()) }
        val resultTree = someFunction.map { it(Tree()) }
            .fold(
                ifSome = {
                    it
                },
                ifEmpty = { Tree(leaves = (1..20).map { Leaf() }) }
            )
        resultTree.leaves.shouldBeEmpty()
    }

    @Test
    fun `should test functor relation with andThen`() {
        val add5Leaves: (Tree) -> Tree = { it.copy(leaves = it.leaves + (1..5).map { Leaf() }) }
        val divideLeavesBy2: (Tree) -> Tree =
            { tree -> tree.copy(leaves = tree.leaves.subList(tree.leaves.size / 2, tree.leaves.size)) }
        val add5AndThenDivideBy2 = add5Leaves.andThen(divideLeavesBy2)
        val result = add5AndThenDivideBy2(Tree())
        result.leaves.shouldHaveSize(3)

        val divideBy2AndThenAdd5 = add5Leaves.compose(divideLeavesBy2)
        val result2 = divideBy2AndThenAdd5(Tree())
        result2.leaves.shouldHaveSize(5)
    }

}