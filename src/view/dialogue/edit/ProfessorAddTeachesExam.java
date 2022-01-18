package view.dialogue.edit;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.ProfessorController;
import controller.SubjectController;

public class ProfessorAddTeachesExam extends JDialog {
	
	private JList<String> subjectList;

	public ProfessorAddTeachesExam(EditProfessorTeaches parent, int selectedProfessor, EditProfessorTabbedPane ptp) {
		setLayout(new FlowLayout());
		setSize(275, 300);
		setLocationRelativeTo(parent);
		setTitle("Add subject");
		
		//	Label
		JPanel labelPanel = new JPanel();
		JLabel subjectsLabel = new JLabel("Subjects:");
		labelPanel.add(subjectsLabel);
		
		//	List
		JPanel listPanel = new JPanel();
		subjectList = new JList<String>(SubjectController.getInstance().getSubjectListForProfessor(selectedProfessor));
		JScrollPane subjectScroller = new JScrollPane(subjectList);
		listPanel.add(subjectScroller);
		
		//	Buttons
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("OK");
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(subjectList.getSelectedIndex() != -1) {
					//ProfessorController.getInstance().addSubjectTeach(subjectList.getSelectedValue(), selectedProfessor);
					ProfessorController.getInstance().addSubjectTeach(subjectList.getSelectedValuesList(), selectedProfessor);
					ptp.updateView();
					dispose();
				} else
					JOptionPane.showMessageDialog(getParent(), "Please select a subject to add", "Error", 0);
			}
		});
		
		JButton cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		
		add(labelPanel);
		add(listPanel);
		add(buttonPanel);
		
		setResizable(false);
		setVisible(true);
	}
}
