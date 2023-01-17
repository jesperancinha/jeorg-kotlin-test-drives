package org.jesperancinha.string.paradigm.nowrapper.performance

import org.jesperancinha.string.paradigm.api.Dependency
import org.jesperancinha.string.paradigm.api.SegmentTreeModel
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.TreePath

class NoWrapperDependencyTreeModel : DefaultTreeModel(null), SegmentTreeModel {
    init {
        clear()
    }

    override fun getRoot(): DefaultMutableTreeNode {
        return super.getRoot() as DefaultMutableTreeNode
    }

    override fun getChild(parent: Any, index: Int): DefaultMutableTreeNode {
        return super.getChild(parent, index) as DefaultMutableTreeNode
    }

    override fun clear() {
        val root = DefaultMutableTreeNode("Combinations")
        root.allowsChildren = true
        setRoot(root)
    }

    override fun getDependencies(path: TreePath): List<String> {
        val last: DefaultMutableTreeNode = path.lastPathComponent as DefaultMutableTreeNode
        val objects: Array<Any> = last.userObjectPath
        val subDependencyElement: MutableList<String> = ArrayList()
        for (i in 1 until objects.size) {
            subDependencyElement.add(objects[i] as String)
        }
        return subDependencyElement
    }

    override fun addCombination(dependency: Dependency) = addCombinationNode(getRoot(), dependency, 0)

    override fun addCombinationNode(
        parent: DefaultMutableTreeNode,
        dependency: Dependency,
        codeIndex: Int
    ): DefaultMutableTreeNode {
        val subDependency = dependency.getSubDependencyElement(codeIndex)
        var child: DefaultMutableTreeNode? = parent.let {
            subDependency?.let { it1 ->
                try {
                    findChild(it, it1)
                } catch (_: Exception) {
                    null
                }
            }
        }
        if (child == null) {
            child = DefaultMutableTreeNode(subDependency)
            child.allowsChildren = true
            insertNodeInto(child, parent, getChildCount(parent))
        }
        if (codeIndex < dependency.dependencyCount() - 1) {
            addCombinationNode(child, dependency, codeIndex + 1)
        }
        return child
    }

    override fun findChild(parent: DefaultMutableTreeNode, subDependency: String): DefaultMutableTreeNode {
        for (i in 0 until getChildCount(parent)) {
            val child: DefaultMutableTreeNode = getChild(parent, i)
            if (child.userObject == subDependency) {
                return child
            }
        }
        throw NullPointerException()
    }

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 7448300246415449363L
    }
}