package view.dialogue.edit;

import javax.swing.JTable;

import controller.AbstractTableModelExamsPassed;

public class ExamsPassedTable extends JTable {
	
	public ExamsPassedTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelExamsPassed());
	}
}
