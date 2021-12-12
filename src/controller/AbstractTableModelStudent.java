package controller;

import javax.swing.table.AbstractTableModel;

import model.DBStudent;

public class AbstractTableModelStudent extends AbstractTableModel {
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBStudent.getInstance().getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBStudent.getInstance().getColumnCount();
	}
	
	@Override
	public String getColumnName(int column) {
		return DBStudent.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBStudent.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
}
