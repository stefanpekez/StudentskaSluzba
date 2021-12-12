package view;

import javax.swing.JTable;

import controller.AbstractTableModelSubject;

public class SubjectTable extends JTable {

	private static final long serialVersionUID = -5839231427187920524L;
	
	public SubjectTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelSubject());
	}
	
}
