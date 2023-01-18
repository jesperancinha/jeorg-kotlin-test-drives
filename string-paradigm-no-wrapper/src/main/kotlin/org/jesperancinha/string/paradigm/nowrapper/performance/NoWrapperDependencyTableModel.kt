package org.jesperancinha.string.paradigm.nowrapper.performance

import org.jesperancinha.string.paradigm.api.Dependency
import org.jesperancinha.string.paradigm.api.SegmentTableModelIntReturn
import javax.swing.table.AbstractTableModel

class NoWrapperDependencyTableModel : AbstractTableModel(), SegmentTableModelIntReturn {
    private val dependencies: MutableList<Dependency> = ArrayList()
    private var columnCount = 0
    override fun addCombination(dependency: Dependency): Int {
        dependencies.add(dependency)
        val codeCount: Int = dependency.dependencyCount()
        if (codeCount != columnCount) {
            columnCount = columnCount.coerceAtLeast(codeCount)
        }
        return dependencies.size - 1
    }

    override fun getRowCount(): Int {
        return dependencies.size
    }

    override fun getColumnCount(): Int {
        return columnCount
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): String? {
        val dependency: Dependency = dependencies[rowIndex]
        return dependency.getSubDependencyElement(columnIndex)
    }

    override fun clear() {
        val size = dependencies.size
        if (size > 0) {
            dependencies.clear()
            fireTableRowsDeleted(0, size - 1)
        }
    }

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 707044827159393606L
    }
}