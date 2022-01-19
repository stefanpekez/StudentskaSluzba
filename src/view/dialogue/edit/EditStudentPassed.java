package view.dialogue.edit;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.LanguageController;
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
		JButton cancelGrade = new JButton(LanguageController.getInstance().getResourceBundle().getString("CancelGradeButton"));
		cancelGrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					// controller cancel grade
					new DeleteGradeDialogue(table.getSelectedRow(), tabbedpane).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("GradeNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
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
		JLabel average = new JLabel(LanguageController.getInstance().getResourceBundle().getString("AvgGrade"));
		
		averagePanel = new JPanel();
		averagePanel.add(average);
		averagePanel.add(dynAverage);
		
		// ESPB
		JLabel totalEspb = new JLabel(LanguageController.getInstance().getResourceBundle().getString("TotalEspb"));
		
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
