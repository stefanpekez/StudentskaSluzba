package view.dialogue;

import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.LanguageController;
import controller.SubjectController;
import view.TabbedPane.TablePanel;

public class NewSubjectDialogue extends JDialog {
	
	private NewTF idPanel;
	private NewTF subjectNamePanel;
	private NewTF espbPanel;
	private NewCB yearPanel;
	private NewCB currentSemesterPanel;
	private JPanel buttonsPanel;
	private JButton accept;
	private JButton exit;
	
	public NewSubjectDialogue(Container mainframe, TablePanel tp) {
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		
		setSize(500, 300);
		setLocationRelativeTo(mainframe);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("AddSubjectTitle"));
		setLayout(boxLayout);
		
		String[] currentSemester = { "ZIMSKI", "LETNJI"};
		String[] year = { "1", "2", "3", "4"};
		
		idPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Id"), this, "");
		subjectNamePanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Name"), this, "");
		espbPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Espb"), this, "");
		yearPanel = new NewCB(LanguageController.getInstance().getResourceBundle().getString("Year"), year);
		currentSemesterPanel = new NewCB(LanguageController.getInstance().getResourceBundle().getString("Semester"), currentSemester);
		
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton(LanguageController.getInstance().getResourceBundle().getString("AcceptButton"));
		accept.setEnabled(false);
		accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SubjectController.getInstance().addNewSubject(idPanel, subjectNamePanel, espbPanel, yearPanel, currentSemesterPanel);
				tp.updateView();
				exitDialog();
			}
		});
		
		//exit button
		exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
		exit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				exitDialog();
			} 
		});
		
		add(idPanel);
		add(subjectNamePanel);
		add(espbPanel);
		add(yearPanel);
		add(currentSemesterPanel);
		buttonsPanel.add(accept);
		buttonsPanel.add(exit);
		add(buttonsPanel);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
	}
	
	public void exitDialog() {
		dispose();
	}
	
	public void checkAllFields() {
		if(idPanel.checkField() && subjectNamePanel.checkField() && espbPanel.checkField()) {
			
			accept.setEnabled(true);
			return;
		}
		
		accept.setEnabled(false);
		return;
	}

}
