package view.dialogue.edit;

import java.awt.Dimension;

import javax.swing.JTable;

import controller.AbstractTableModelExams;
import controller.AbstractTableModelExamsPassed;
import model.DBExams;

public class ExamsPassedTable extends JTable {
	
	public ExamsPassedTable() {
		this.setRowSelectionAllowed(true);
		this.setModel(new AbstractTableModelExamsPassed());
	}
}
