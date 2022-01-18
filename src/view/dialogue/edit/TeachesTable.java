package view.dialogue.edit;

import javax.swing.JTable;

import controller.AbstractTableModelTeaches;

public class TeachesTable extends JTable {

	public TeachesTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelTeaches());
	}
}
