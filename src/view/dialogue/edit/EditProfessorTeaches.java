package view.dialogue.edit;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ProfessorController;
import controller.SubjectController;
import view.TabbedPane.TablePanel;

public class EditProfessorTeaches extends JPanel {
	
	private JTable table;
	private JButton add;
	private JButton remove;
	
	public EditProfessorTeaches(EditProfessorDialogue parent, TablePanel panel, EditProfessorTabbedPane ptp) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		int selectedProfessor = panel.getTable().convertRowIndexToModel(panel.getTable().getSelectedRow());
		
		ProfessorController.getInstance().setupTaughtSubjects(selectedProfessor);
		
		EditProfessorTeaches instance = this;
		
		JPanel tablePanel = new JPanel();
		table = new TeachesTable();
		table.setPreferredScrollableViewportSize(new Dimension(parent.getWidth()-50, parent.getHeight()-150));
		table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
		
		tablePanel.add(new JScrollPane(table));
		
		JPanel buttonPanel = new JPanel();
		add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ProfessorAddTeachesExam(instance, selectedProfessor, ptp);
			}
		});
		
		remove = new JButton("REMOVE");
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectedRows().length != 0) {
					
					String[] allSubjects = new String[table.getSelectedRows().length];
					for(int i = 0; i < table.getSelectedRows().length; ++i) {
						allSubjects[i] = (String) table.getModel().getValueAt(table.getSelectedRows()[i], 0);
					}
					
					SubjectController.getInstance().deleteTaughtSubject(selectedProfessor, allSubjects);
					ptp.updateView();
					
				} else 
					JOptionPane.showMessageDialog(getParent(), "Please select a subject to remove", "Error", 0);
			}
		});
		
		buttonPanel.add(add);
		buttonPanel.add(remove);
		
		add(buttonPanel);
		add(tablePanel);
		
	}
	
	public TeachesTable getTable() {
		return (TeachesTable) table;
	}

}
