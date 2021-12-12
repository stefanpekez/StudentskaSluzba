package view;

import javax.swing.JTable;

import controller.AbstractTableModelProfessor;

public class ProfessorTable extends JTable{

	private static final long serialVersionUID = 604763724214602433L;

	public ProfessorTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelProfessor());
	}
}
