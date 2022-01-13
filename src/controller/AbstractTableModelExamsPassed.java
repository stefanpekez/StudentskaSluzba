package controller;

import javax.swing.table.AbstractTableModel;

import model.DBExamsPassed;

public class AbstractTableModelExamsPassed extends AbstractTableModel {

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBExamsPassed.getInstance().getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBExamsPassed.getInstance().getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBExamsPassed.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		return DBExamsPassed.getInstance().getColumnName(column);
	}

}
