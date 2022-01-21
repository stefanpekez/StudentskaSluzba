package view.dialogue.edit;

import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.LanguageController;
import controller.ProfessorController;
import controller.SubjectController;

public class ProfessorAddTeachesExam extends JDialog {
	
	private JList<String> subjectList;

	public ProfessorAddTeachesExam(EditProfessorTeaches parent, int selectedProfessor, EditProfessorTabbedPane ptp) {
		setLayout(new FlowLayout());
		setSize(275, 300);
		setLocationRelativeTo(parent);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("AddSubjectTitle"));
		
		//	Label
		JPanel labelPanel = new JPanel();
		JLabel subjectsLabel = new JLabel(LanguageController.getInstance().getResourceBundle().getString("SubjectList"));
		labelPanel.add(subjectsLabel);
		
		//	List
		JPanel listPanel = new JPanel();
		subjectList = new JList<String>(SubjectController.getInstance().getSubjectListForProfessor(selectedProfessor));
		JScrollPane subjectScroller = new JScrollPane(subjectList);
		listPanel.add(subjectScroller);
		
		//	Buttons
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton(LanguageController.getInstance().getResourceBundle().getString("OkButton"));
		
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
					JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("SubjectNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
			}
		});
		
		JButton cancel = new JButton(LanguageController.getInstance().getResourceBundle().getString("Cancel"));
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
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setResizable(false);
		setVisible(true);
	}
}
