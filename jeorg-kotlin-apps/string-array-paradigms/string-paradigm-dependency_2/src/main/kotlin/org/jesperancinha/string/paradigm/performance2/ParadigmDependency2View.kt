package org.jesperancinha.string.paradigm.performance2

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.event.ActionEvent
import java.io.File
import java.io.IOException
import javax.swing.*
import javax.swing.tree.TreePath

class ParadigmDependency2View : JPanel() {
    private val tableModel: ParadigmDependency2TableModel
    private val table: JTable
    private val treeModel: ParadigmDependency2TreeModel
    private val tree: JTree
    private var dependencies = emptyList<StringWrapper>()

    init {
        val toolBar = JToolBar()
        toolBar.add(object : AbstractAction("Dependency loader") {
            override fun actionPerformed(e: ActionEvent) {
                loadCombinations()
            }
        })
        tableModel = ParadigmDependency2TableModel()
        table = JTable(tableModel)
        treeModel = ParadigmDependency2TreeModel()
        tree = object : JTree(treeModel) {
            override fun getPreferredScrollableViewportSize(): Dimension {
                return Dimension(300, 400)
            }
        }
        tree.addTreeSelectionListener { showCombinations(tree.getSelectionPaths()) }
        val pane = JSplitPane()
        pane.leftComponent = JScrollPane(tree)
        pane.rightComponent = JScrollPane(table)
        layout = BorderLayout()
        add(toolBar, BorderLayout.PAGE_START)
        add(pane, BorderLayout.CENTER)
    }

    private fun showCombinations(paths: Array<TreePath>?) {
        val filtered: MutableList<StringWrapper> = ArrayList()
        if (paths != null) {
            for (path in paths) {
                val subDependencyElement = treeModel.getDependencies(path)
                for (data in dependencies) {
                    val dependency = ParadigmDependency2Impl(data)
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
        chooser.currentDirectory = File(".")
        val result = chooser.showOpenDialog(this)
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                loadCombinations(chooser.selectedFile)
            } catch (e1: IOException) {
                JOptionPane.showMessageDialog(this, e1.localizedMessage, "Error", JOptionPane.ERROR_MESSAGE)
            }
        }
    }

    @Throws(IOException::class)
    private fun loadCombinations(file: File) = run {
        dependencies = readLines(file)
            .sortedWith { o1, o2 -> o1.toString().compareTo(o2.toString()) }
            .also {
                loadTableData(it)
                loadTreeData(it)
            }
    }


    private fun loadTableData(dependencies: List<StringWrapper>) {
        tableModel.clear()
        var lastrow = 0
        for (data in dependencies) {
            val dependency = ParadigmDependency2Impl(data)
            lastrow = tableModel.addCombination(dependency)
        }
        tableModel.fireTableStructureChanged()
        tableModel.fireTableRowsInserted(0, lastrow)
    }

    private fun loadTreeData(dependencies: List<StringWrapper>) {
        treeModel.clear()
        tree.expandPath(TreePath(treeModel.root))
        for (data in dependencies) {
            val dependency = ParadigmDependency2Impl(data)
            val child = treeModel.addCombination(dependency)
            tree.expandPath(TreePath(arrayOf(treeModel.root, child)))
        }
        tree.selectionPath = TreePath(treeModel.root)
    }

    @Throws(IOException::class)
    private fun readLines(file: File): List<StringWrapper> = file.readLines().map { StringWrapper(it) }
}

fun main() {
    EventQueue.invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        JFrame("Code Combinations View").apply {
            defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            contentPane.add(ParadigmDependency2View())
            pack()
            setLocationRelativeTo(null)
            isVisible = true
        }

    }
}