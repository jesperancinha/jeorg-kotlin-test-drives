package org.jesperancinha.string.paradigm.original.performance

import org.jesperancinha.string.paradigm.api.Dependency
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.event.ActionEvent
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import javax.swing.*
import javax.swing.event.TreeSelectionEvent
import javax.swing.event.TreeSelectionListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath

class OriginalDependencyView : JPanel() {
    private val tableModel: OriginalDependencyTableModel
    private val table: JTable
    private val treeModel: OriginalDependencyTreeModel
    private val tree: JTree
    private var dependencies = emptyList<String>()

    init {
        val toolBar = JToolBar()
        toolBar.add(object : AbstractAction("Load dependencies") {
            override fun actionPerformed(e: ActionEvent) {
                loadCombinations()
            }
        })
        tableModel = OriginalDependencyTableModel()
        table = JTable(tableModel)
        treeModel = OriginalDependencyTreeModel()
        tree = object : JTree(treeModel) {
            override fun getPreferredScrollableViewportSize(): Dimension {
                return Dimension(300, 400)
            }
        }
        tree.addTreeSelectionListener(object : TreeSelectionListener {
            override fun valueChanged(e: TreeSelectionEvent) {
                showCombinations(tree.getSelectionPaths())
            }
        })
        val pane = JSplitPane()
        pane.setLeftComponent(JScrollPane(tree))
        pane.setRightComponent(JScrollPane(table))
        setLayout(BorderLayout())
        add(toolBar, BorderLayout.PAGE_START)
        add(pane, BorderLayout.CENTER)
    }

    private fun showCombinations(paths: Array<TreePath>?) {
        val filtered: MutableList<String> = ArrayList()
        if (paths != null) {
            for (path in paths) {
                val subDependencyElement = treeModel.getDependencies(path)
                for (data in dependencies) {
                    val dependency = OriginalDependencyImpl(data)
                    if (dependency.startsWith(subDependencyElement)) {
                        filtered.add(data)
                    }
                }
            }
        }
        loadTableData(filtered)
    }

    private fun loadCombinations() {
        val chooser = JFileChooser()
        chooser.setCurrentDirectory(File("."))
        val result: Int = chooser.showOpenDialog(this)
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                loadCombinations(chooser.getSelectedFile())
            } catch (e1: IOException) {
                JOptionPane.showMessageDialog(
                    this,
                    e1.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE
                )
            }
        }
    }

    @Throws(IOException::class)
    private fun loadCombinations(file: File) {
        dependencies = readLines(file)
        loadTableData(dependencies)
        loadTreeData(dependencies)
    }

    private fun loadTableData(dependencies: List<String>) {
        tableModel.clear()
        for (data in dependencies) {
            val dependency = OriginalDependencyImpl(data)
            tableModel.addCombination(dependency)
        }
    }

    private fun loadTreeData(dependencies: List<String>) {
        treeModel.clear()
        val root: Any = treeModel.getRoot()
        tree.expandPath(TreePath(root))
        for (data in dependencies) {
            val dependency: Dependency = OriginalDependencyImpl(data)
            val child: DefaultMutableTreeNode? = treeModel.addCombination(dependency)
            tree.expandPath(TreePath(arrayOf(root, child)))
        }
        tree.setSelectionPath(TreePath(root))
    }

    @Throws(IOException::class)
    private fun readLines(file: File): List<String> {
        val lines: MutableList<String> = ArrayList()
        val reader = BufferedReader(FileReader(file))
        return try {
            var line: String
            while (reader.readLine().also { line = it } != null) {
                if (!line.isEmpty()) {
                    lines.add(line)
                }
            }
            lines
        } finally {
            reader.close()
        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        val frame = JFrame("Code Combinations View")
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.contentPane.add(OriginalDependencyView())
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}