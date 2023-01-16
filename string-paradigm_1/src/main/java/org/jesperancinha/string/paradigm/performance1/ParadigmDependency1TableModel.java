package org.jesperancinha.string.paradigm.performance1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ParadigmDependency1TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7320366591500286562L;
	private List<ParadigmDependency1Impl> dependencies = new ArrayList<ParadigmDependency1Impl>();
    private int columnCount;
    
    public int addCombination(ParadigmDependency1Impl dependency) {
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
        ParadigmDependency1Impl dependency = dependencies.get(rowIndex);
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
