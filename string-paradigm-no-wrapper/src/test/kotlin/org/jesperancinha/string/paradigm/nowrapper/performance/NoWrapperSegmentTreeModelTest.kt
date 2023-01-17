package org.jesperancinha.string.paradigm.nowrapper.performance

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.jesperancinha.string.paradigm.api.Dependency
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath

private const val PRESENT = "Present with cheese and a spicy delightful paprika sauce"

class NoWrapperSegmentTreeModelTest {
    private val defaultMutableTreeNode by lazy { DefaultMutableTreeNode(PRESENT) }
    private val defaultMutableTreeNode2 by lazy { DefaultMutableTreeNode("Ok") }
    private val noWrapperDependencyTreeModel by lazy {
        NoWrapperDependencyTreeModel()
    }

    private val noWrapperDependencyTreeModel2 by lazy {
        NoWrapperDependencyTreeModel()
    }

    @Test
    fun `should not find node`() {
        shouldThrow<NullPointerException> {
            noWrapperDependencyTreeModel.findChild(
                DefaultMutableTreeNode("AH"),
                "WOW"
            )
        }

    }

    @Test
    fun `should find child`() {
        val parent = DefaultMutableTreeNode("Past without cheese")
        parent.add(DefaultMutableTreeNode(PRESENT))
        noWrapperDependencyTreeModel2.findChild(
            parent,
            PRESENT
        ).userObject shouldBe defaultMutableTreeNode.userObject
    }

    @Test
    fun `should add combination node`() {
        noWrapperDependencyTreeModel.addCombinationNode(
            DefaultMutableTreeNode(),
            object : Dependency() {
                override fun dependencyCount() = 10
                override fun getSubDependencyElement(index: Int): String = "Ok"
                override fun startsWith(subDependencyElement: List<String>) = true
            }, 0
        ).should {
            it.userObject shouldBe "Ok"
        }
    }

    @Test
    fun `should clear`() {
        noWrapperDependencyTreeModel.clear() shouldBe Unit
    }


    @Test
    @Disabled
    fun `should get dependencies`() {
        noWrapperDependencyTreeModel.addCombinationNode(
            DefaultMutableTreeNode(PRESENT),
            NoWrapperDependencyImpl("Ok"),
            1
        )
        noWrapperDependencyTreeModel.getDependencies(TreePath(arrayOf(DefaultMutableTreeNode("Ok"))))
            .shouldHaveSize(1)
            .shouldContain("Ok")
    }

    @Test
    fun `should add combination`() {
        noWrapperDependencyTreeModel.addCombination(object : Dependency() {
            override fun dependencyCount() = 10
            override fun getSubDependencyElement(index: Int): String = "Ok"
            override fun startsWith(subDependencyElement: List<String>) = true
        }).userObject shouldBe defaultMutableTreeNode2.userObject
    }

}