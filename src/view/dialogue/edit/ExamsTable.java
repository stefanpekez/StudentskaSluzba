package view.dialogue.edit;

import javax.swing.JTable;

import controller.AbstractTableModelExams;

public class ExamsTable extends JTable {

	private static final long serialVersionUID = 5882156674261681594L;
	
	public ExamsTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelExams());
	}
}
