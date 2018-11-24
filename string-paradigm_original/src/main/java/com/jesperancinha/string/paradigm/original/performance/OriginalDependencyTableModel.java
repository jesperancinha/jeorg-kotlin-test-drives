package com.jesperancinha.string.paradigm.original.performance;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jesperancinha.string.paradigm.api.Dependency;
import com.jesperancinha.string.paradigm.api.SegmentTableModelVoidReturn;


public class OriginalDependencyTableModel extends AbstractTableModel implements SegmentTableModelVoidReturn {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4568849356390214463L;
	private List<Dependency> dependencies = new ArrayList<Dependency>();
    private int columnCount;
    
    @Override
    public void addCombination(Dependency dependency) {
        this.dependencies.add(dependency);
        
        int codeCount = dependency.getDependencyCount();
        if(codeCount != this.columnCount) {
            this.columnCount = Math.max(columnCount, codeCount);
            fireTableStructureChanged();
        }
        
        int row = dependencies.size() - 1;
        fireTableRowsInserted(row, row);
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
        Dependency dependency = dependencies.get(rowIndex);
        return dependency.getSubDependencyElement(columnIndex);
    }

    public void clear() {
        int size = this.dependencies.size();
        if(size > 0) {
            this.dependencies.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }
}
