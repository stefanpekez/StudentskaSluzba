package view.dialogue.edit;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.StudentController;
import view.TabbedPane.TablePanel;

public class EditStudentUnpassed extends JPanel {

	private static final long serialVersionUID = 1889083892971914516L;
	
	private JTable table;	
	private JButton add;
	private JButton delete;
	private JButton apply;
	
	public EditStudentUnpassed(EditStudentDialogue editDialogue, TablePanel tp) {
		
		//TODO init database to store exams
		StudentController.getInstance().setupCurrentExamsDB(tp.getTable().getSelectedRow());
		table = new ExamsTable();
		
		//TODO add buttos
		this.add = new JButton("ADD");
		this.delete = new JButton("DELETE");
		this.apply = new JButton("APPLY");
		
		//TODO show table here 
		add(this.add);
		add(this.delete);
		add(this.apply);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
