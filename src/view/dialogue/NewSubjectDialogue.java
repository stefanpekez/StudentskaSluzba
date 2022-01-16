package view.dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.StudentController;
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
		setTitle("Add Subject");
		setLayout(boxLayout);
		
		String[] currentSemester = { "ZIMSKI", "LETNJI"};
		String[] year = { "1", "2", "3", "4"};
		
		idPanel = new NewTF("ID*", this, "");
		subjectNamePanel = new NewTF("Subject Name*", this, "");
		espbPanel = new NewTF("ESPB*", this, "");
		yearPanel = new NewCB("Year*                                  ", year);
		currentSemesterPanel = new NewCB("Semester*                         ", currentSemester);
		
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton("ACCEPT");
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
		exit = new JButton("EXIT");
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
