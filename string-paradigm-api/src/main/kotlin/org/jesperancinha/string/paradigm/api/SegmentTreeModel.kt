package org.jesperancinha.string.paradigm.api

import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath

interface SegmentTreeModel {
    fun findChild(parent: DefaultMutableTreeNode, subDependency: String): DefaultMutableTreeNode
    fun addCombinationNode(
        parent: DefaultMutableTreeNode,
        dependency: Dependency,
        codeIndex: Int
    ): DefaultMutableTreeNode

    fun clear()
    fun getDependencies(path: TreePath): List<String>
    fun addCombination(dependency: Dependency): DefaultMutableTreeNode
}