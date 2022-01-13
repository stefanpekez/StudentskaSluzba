package view.dialogue.edit;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.AbstractTableModelExams;
import controller.StudentController;
import view.TabbedPane.TablePanel;

public class EditStudentUnpassed extends JPanel {

	private static final long serialVersionUID = 1889083892971914516L;
	
	private JTable table;
	private JButton add;
	private JButton delete;
	private JButton apply;
	
	public EditStudentUnpassed(EditStudentDialogue editDialogue, TablePanel tp, EditStudentPassed tablePassed) {
		
		//TODO init database to store exams
		int selectedStudent = tp.getTable().getSelectedRow();
		
		StudentController.getInstance().setupCurrentExamsDB(selectedStudent);
		table = new ExamsTable();
		
		//TODO add buttos
		this.add = new JButton("ADD");
		this.delete = new JButton("DELETE");
		this.apply = new JButton("MARK AS PASSED");
		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(row != -1) {
					//show dialogue thats going to take this selected row and prepopulate the required fields 
					new GradeInputDialogue(row, selectedStudent, editDialogue, table, tablePassed).setVisible(true);
				} else {
					System.out.println("Please select an exam that you want to mark as passed");
				}
				
			}
		});
		//TODO show table here 
		add(this.add);
		add(this.delete);
		add(this.apply);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
}
