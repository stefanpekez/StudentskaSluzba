package controller;

import javax.swing.table.AbstractTableModel;

import model.DBProfessor;

public class AbstractTableModelProfessor extends AbstractTableModel{

	private static final long serialVersionUID = 5300533108989125699L;

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBProfessor.getInstance().getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBProfessor.getInstance().getColumnCount();
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return DBProfessor.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBProfessor.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

}
