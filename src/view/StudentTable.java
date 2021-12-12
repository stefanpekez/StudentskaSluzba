package view;

import javax.swing.JTable;

import controller.AbstractTableModelStudent;

public class StudentTable extends JTable {

	public StudentTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelStudent());
	}
}
