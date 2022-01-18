package controller;

import javax.swing.table.AbstractTableModel;

import model.DBTeaches;

public class AbstractTableModelTeaches extends AbstractTableModel{
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBTeaches.getInstance().getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBTeaches.getInstance().getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBTeaches.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		return DBTeaches.getInstance().getColumnName(column);
	}
}
