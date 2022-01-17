package view.dialogue.edit;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.StudentController;
import view.TabbedPane.TablePanel;

public class EditStudentPassed extends JPanel {
	
	private JTable table;
	private JLabel dynAverage = new JLabel();
	private JLabel dynESPB = new JLabel();
	private JPanel tablePanel;
	private JPanel buttonPanel;
	private JPanel averagePanel;
	private JPanel espbPanel;
	private TablePanel tp;
	
	public EditStudentPassed(EditStudentDialogue editDialogue, TablePanel tp, EditStudentTabbedPane tabbedpane) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		int selectedStudent = tp.getTable().convertRowIndexToModel(tp.getTable().getSelectedRow());
		
		StudentController.getInstance().setupPassedExamsDB(selectedStudent);
		
		// Cancel Grade Button
		JButton cancelGrade = new JButton("CANCEL GRADE");
		cancelGrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					// controller cancel grade
					new DeleteGradeDialogue(table.getSelectedRow(), tabbedpane).setVisible(true);
				} else {
					System.out.println("Please select a grade that you want to cancel");
				}
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.add(cancelGrade);
		
		// Passed Exams Table
		table = new ExamsPassedTable();
		table.setPreferredScrollableViewportSize(new Dimension(editDialogue.getWidth()-50, editDialogue.getHeight()-150));
		table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
		
		tablePanel = new JPanel();
		tablePanel.add( new JScrollPane(table));
		
		// Average
		JLabel average = new JLabel("Average Grade: ");
		
		averagePanel = new JPanel();
		averagePanel.add(average);
		averagePanel.add(dynAverage);
		
		// ESPB
		JLabel totalEspb = new JLabel("Total ESPB: ");
		
		espbPanel = new JPanel();
		espbPanel.add(totalEspb);
		espbPanel.add(dynESPB);
		
		add(buttonPanel);
		add(tablePanel);
		add(averagePanel);
		add(espbPanel);
		
	}
	
	public JTable getTable() {
		return this.table;
	}
	
	public JLabel getDynAverage() {
		return this.dynAverage;
	}
	
	public JLabel getDynESPB() {
		return this.dynESPB;
	}

}
