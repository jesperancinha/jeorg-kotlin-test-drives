package com.jesperancinha.string.paradigm.performance2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ParadigmDependency2TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7320366591500286562L;
	private List<ParadigmDependency2Impl> dependencies = new ArrayList<ParadigmDependency2Impl>();
    private int columnCount;
    
    public int addCombination(ParadigmDependency2Impl dependency) {
        this.dependencies.add(dependency);
        
        int codeCount = dependency.getDependencyCount();
        if(codeCount != this.columnCount) {
            this.columnCount = Math.max(columnCount, codeCount);
//            fireTableStructureChanged();
        }
        
        int row = dependencies.size() - 1;
//        fireTableRowsInserted(row, row);
        return row;
    }

    @Override
    public int getRowCount() {
        return dependencies.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ParadigmDependency2Impl dependency = dependencies.get(rowIndex);
        return dependency.getSubDependencyElement(columnIndex).intern();
    }

    public void clear() {
        int size = this.dependencies.size();
        if(size > 0) {
            this.dependencies.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }
}
