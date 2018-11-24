package com.jesperancinha.string.paradigm.nowrapper.performance;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.steelzack.string.paradigm.api.Dependency;
import com.steelzack.string.paradigm.api.SegmentTableModelIntReturn;


public class NoWrapperDependencyTableModel extends AbstractTableModel implements SegmentTableModelIntReturn{

    /**
	 * 
	 */
	private static final long serialVersionUID = 707044827159393606L;
	private List<Dependency> dependencies = new ArrayList<Dependency>();
    private int columnCount;
    
    @Override
    public int addCombination(Dependency dependency) {
        this.dependencies.add(dependency);
        
        int codeCount = dependency.getDependencyCount();
        if(codeCount != this.columnCount) {
            this.columnCount = Math.max(columnCount, codeCount);
        }
        
        int row = dependencies.size() - 1;
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
