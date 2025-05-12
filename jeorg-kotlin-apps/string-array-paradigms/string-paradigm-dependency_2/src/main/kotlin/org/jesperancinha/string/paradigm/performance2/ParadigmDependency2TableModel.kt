package org.jesperancinha.string.paradigm.performance2

import javax.swing.table.AbstractTableModel

class ParadigmDependency2TableModel : AbstractTableModel() {
    private val dependencies: MutableList<ParadigmDependency2Impl> = ArrayList()
    private var columnCount = 0
    fun addCombination(dependency: ParadigmDependency2Impl): Int {
        dependencies.add(dependency)
        val codeCount = dependency.dependencyCount()
        if (codeCount != columnCount) {
            columnCount = columnCount.coerceAtLeast(codeCount)
            //            fireTableStructureChanged();
        }
        //        fireTableRowsInserted(row, row);
        return dependencies.size - 1
    }

    override fun getRowCount() = dependencies.size

    override fun getColumnCount() = columnCount

    override fun getValueAt(rowIndex: Int, columnIndex: Int) =
        dependencies[rowIndex].getSubDependencyElement(columnIndex).intern()

    fun clear() {
        val size = dependencies.size
        columnCount = 0
        if (size > 0) {
            dependencies.clear()
            fireTableRowsDeleted(0, size - 1)
        }
    }
}