package view.dialogue.edit;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.StudentController;
import controller.SubjectController;
import view.TabbedPane.TablePanel;

public class EditStudentUnpassed extends JPanel {

	private static final long serialVersionUID = 1889083892971914516L;
	
	private JTable table;
	private JButton add;
	private JButton delete;
	private JButton apply;
	
	public EditStudentUnpassed(EditStudentDialogue editDialogue, TablePanel tp, EditStudentTabbedPane stp, EditStudentPassed tablePassed) {
		
		//TODO init database to store exams
		int selectedStudent = tp.getTable().convertRowIndexToModel(tp.getTable().getSelectedRow());
		
		StudentController.getInstance().setupCurrentExamsDB(selectedStudent);
		table = new ExamsTable();
		
		EditStudentUnpassed instance = this;
		
		int selectedSubject = table.getSelectedRow();
		
		
		//TODO add buttos
		this.add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new StudentAddUnpassedExam(instance, stp, selectedStudent);
			}
		});
		this.delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SubjectController.getInstance().deleteUnpassedExam(selectedStudent, (String) table.getModel().getValueAt(table.getSelectedRow(), 0));
				stp.updateView(2);
			}
		});
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
	
	public ExamsTable getTable() {
		return (ExamsTable) table;
	}
}
