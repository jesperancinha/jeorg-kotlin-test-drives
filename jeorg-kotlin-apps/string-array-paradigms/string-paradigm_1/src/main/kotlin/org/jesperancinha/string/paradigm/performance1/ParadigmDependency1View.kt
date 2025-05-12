package org.jesperancinha.string.paradigm.performance1

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.event.ActionEvent
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import javax.swing.*
import javax.swing.tree.TreePath

class ParadigmDependency1View : JPanel() {
    private val tableModel: ParadigmDependency1TableModel
    private val table: JTable
    private val treeModel: ParadigmDependency1TreeModel
    private val tree: JTree
    private var dependencies = emptyList<StringWrapper>()

    init {
        val toolBar = JToolBar()
        toolBar.add(object : AbstractAction("Dependency loader") {
            override fun actionPerformed(e: ActionEvent) {
                loadCombinations()
            }
        })
        tableModel = ParadigmDependency1TableModel()
        table = JTable(tableModel)
        treeModel = ParadigmDependency1TreeModel()
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
                    val dependency = ParadigmDependency1Impl(data)
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
    private fun loadCombinations(file: File) {
        dependencies = readLines(file)
        loadTableData(dependencies)
        loadTreeData(dependencies)
    }

    private fun loadTableData(dependencies: List<StringWrapper>) {
        tableModel.clear()
        var lastrow = 0
        for (data in dependencies) {
            val dependency = ParadigmDependency1Impl(data)
            lastrow = tableModel.addCombination(dependency)
        }
        tableModel.fireTableStructureChanged()
        tableModel.fireTableRowsInserted(0, lastrow)
    }

    private fun loadTreeData(dependencies: List<StringWrapper>) {
        treeModel.clear()
        val root: Any = treeModel.getRoot()
        tree.expandPath(TreePath(root))
        for (data in dependencies) {
            val dependency = ParadigmDependency1Impl(data)
            val child = treeModel.addCombination(dependency)
            tree.expandPath(TreePath(arrayOf(root, child)))
        }
        tree.selectionPath = TreePath(root)
    }

    @Throws(IOException::class)
    private fun readLines(file: File): List<StringWrapper> {
        val lines: MutableList<StringWrapper> = ArrayList()
        val fileReader = FileReader(file)
        val reader = BufferedReader(fileReader)
        return try {
            var line: String
            while (reader.readLine().also { line = it } != null) {
                if (!line.isEmpty()) {
                    lines.add(StringWrapper(line))
                }
            }
            lines
        } finally {
            fileReader.close()
            reader.close()
        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        val frame = JFrame("Code Combinations View")
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.contentPane.add(ParadigmDependency1View())
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}