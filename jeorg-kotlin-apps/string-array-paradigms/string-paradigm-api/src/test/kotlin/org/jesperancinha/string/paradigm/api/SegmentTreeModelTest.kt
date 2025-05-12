package org.jesperancinha.string.paradigm.api

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.junit.jupiter.api.Test
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath

class SegmentTreeModelTest {

    val defaultMutableTreeNode by lazy { DefaultMutableTreeNode() }
    val defaultMutableTreeNode2 by lazy { DefaultMutableTreeNode() }
    val defaultMutableTreeNode3 by lazy { DefaultMutableTreeNode() }
    private val segmentTreeModel by lazy {
        object : SegmentTreeModel {
            override fun findChild(parent: DefaultMutableTreeNode, subDependency: String) = defaultMutableTreeNode
            override fun addCombinationNode(
                parent: DefaultMutableTreeNode,
                dependency: Dependency,
                codeIndex: Int,
            ) = defaultMutableTreeNode2

            override fun clear() = Unit
            override fun getDependencies(path: TreePath) = listOf("Ok")
            override fun addCombination(dependency: Dependency) = defaultMutableTreeNode3
        }
    }

    @Test
    fun `should find child`() {
        segmentTreeModel.findChild(
            DefaultMutableTreeNode(),
            "WOW"
        )
            .shouldBeSameInstanceAs(defaultMutableTreeNode)
            .shouldNotBeSameInstanceAs(defaultMutableTreeNode2)
    }

    @Test
    fun `should add combination node`() {
        segmentTreeModel.addCombinationNode(
            DefaultMutableTreeNode(),
            object : Dependency() {
                override fun dependencyCount() = 10
                override fun getSubDependencyElement(index: Int): String = "Ok"
                override fun startsWith(subDependencyElement: List<String>) = true
            }, 0
        )
            .shouldBeSameInstanceAs(defaultMutableTreeNode2)
            .shouldNotBeSameInstanceAs(defaultMutableTreeNode2)
    }

    @Test
    fun `should clear`() {
        segmentTreeModel.clear() shouldBe Unit
    }


    @Test
    fun `should get dependencies`() {
        segmentTreeModel.getDependencies(TreePath(arrayOf(1)))
            .shouldHaveSize(1)
            .shouldContain("Ok")
    }

    @Test
    fun `should add combination`(){
        segmentTreeModel.addCombination(   object : Dependency() {
            override fun dependencyCount() = 10
            override fun getSubDependencyElement(index: Int): String = "Ok"
            override fun startsWith(subDependencyElement: List<String>) = true
        }) shouldBe defaultMutableTreeNode3
    }

}