package org.jesperancinha.string.paradigm.performance1

import javax.swing.table.AbstractTableModel

class ParadigmDependency1TableModel : AbstractTableModel() {
    private val dependencies: MutableList<ParadigmDependency1Impl> = ArrayList()
    private var columnCount = 0
    fun addCombination(dependency: ParadigmDependency1Impl): Int {
        dependencies.add(dependency)
        val codeCount = dependency.dependencyCount()
        if (codeCount != columnCount) {
            columnCount = columnCount.coerceAtLeast(codeCount)
            //            fireTableStructureChanged();
        }
        //        fireTableRowsInserted(row, row);
        return dependencies.size - 1
    }

    override fun getRowCount(): Int {
        return dependencies.size
    }

    override fun getColumnCount(): Int {
        return columnCount
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        val dependency = dependencies[rowIndex]
        return dependency.getSubDependencyElement(columnIndex)!!.intern()
    }

    fun clear() {
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
        private const val serialVersionUID = -7320366591500286562L
    }
}