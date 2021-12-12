package controller;

import javax.swing.table.AbstractTableModel;

import model.DBSubject;

public class AbstractTableModelSubject extends AbstractTableModel {

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBSubject.getInstance().getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBSubject.getInstance().getColumnCount();
	}
	
	@Override
	public String getColumnName(int column) {
		return DBSubject.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBSubject.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

}
