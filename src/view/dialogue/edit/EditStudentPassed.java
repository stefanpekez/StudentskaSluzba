package view.dialogue.edit;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.TabbedPane.TablePanel;

public class EditStudentPassed extends JPanel {
	
	private JTable table;
	private JLabel dynAverage = new JLabel("0.00");
	private JLabel dynESPB = new JLabel("0");
	private JPanel tablePanel;
	private JPanel buttonPanel;
	private JPanel averagePanel;
	private JPanel espbPanel;
	
	public EditStudentPassed(EditStudentDialogue editDialogue, TablePanel tp) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Cancel Grade Button
		JButton cancelGrade = new JButton("CANCEL GRADE");
		
		buttonPanel = new JPanel();
		buttonPanel.add(cancelGrade);
		
		// Passed Exams Table
		table = new ExamsPassedTable();
		table.setPreferredScrollableViewportSize(new Dimension(editDialogue.getWidth()-50, editDialogue.getHeight()-150));
		table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
		
		tablePanel = new JPanel();
		tablePanel.add( new JScrollPane(table));
		
		add(Box.createHorizontalGlue());
		
		// Average
		JLabel average = new JLabel("Average Grade: " + dynAverage.getText());
		
		averagePanel = new JPanel();
		averagePanel.add(average);
		
		// ESPB
		JLabel totalEspb = new JLabel("Total ESPB: " + dynESPB.getText());
		
		espbPanel = new JPanel();
		espbPanel.add(totalEspb);
		
		add(buttonPanel);
		add(tablePanel);
		add(averagePanel);
		add(espbPanel);
		
	}
	
	public JTable getTable() {
		return this.table;
	}

}
