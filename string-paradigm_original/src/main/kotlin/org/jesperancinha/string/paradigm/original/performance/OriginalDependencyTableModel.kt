package org.jesperancinha.string.paradigm.original.performance

import org.jesperancinha.string.paradigm.api.Dependency
import org.jesperancinha.string.paradigm.api.SegmentTableModelVoidReturn
import javax.swing.table.AbstractTableModel

class OriginalDependencyTableModel : AbstractTableModel(), SegmentTableModelVoidReturn {
    private val dependencies: MutableList<Dependency> = ArrayList()
    private var columnCount = 0
    override fun addCombination(dependency: Dependency) {
        dependencies.add(dependency)
        val codeCount: Int = dependency.dependencyCount()
        if (codeCount != columnCount) {
            columnCount = columnCount.coerceAtLeast(codeCount)
            fireTableStructureChanged()
        }
        val row = dependencies.size - 1
        fireTableRowsInserted(row, row)
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
}