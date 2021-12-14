package view.dialogue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.StudentController;
import view.TabbedPane.TablePanel;

public class NewStudentDialogue extends JDialog {
	
	private NewTF namePanel;
	private NewTF surnamePanel;
	private NewTF dateOfBirthPanel;
	private NewTF homeAddressPanel;
	private NewTF phoneNumberPanel;
	private NewTF emailAddressPanel;
	private NewTF indexPanel;
	private NewTF yoePanel;
	private NewCB cyosPanel;
	private NewCB budgetPanel;
	private JPanel buttonsPanel;
	private JButton accept;
	
	//private Type
	
	public NewStudentDialogue(Container mainframe, TablePanel tp) {
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		
		String[] currentYearOfStudy = { "I (prva)", "II (druga)", "III (treca)", "IV (cetvrta)"};
		String[] financing = { "B", "S"};
		
		setSize(500, 500);
		setLocationRelativeTo(mainframe);
		setTitle("Add Student");
		setLayout(boxLayout);
		
		namePanel = new NewTF("Ime*", this, "");
		surnamePanel = new NewTF("Prezime*", this, "");
		dateOfBirthPanel = new NewTF("Datum rodjenja*", this, "dd.mm.yyyy");
		homeAddressPanel = new NewTF("Adresa*", this, "");
		phoneNumberPanel = new NewTF("Broj telefona*", this, "");
		emailAddressPanel = new NewTF("E-mail*", this, "example@email.com");
		indexPanel = new NewTF("Index*", this, "");
		yoePanel = new NewTF("Godina upisa*", this, "");
		cyosPanel = new NewCB("Trenutna godina studija*", currentYearOfStudy);
		budgetPanel = new NewCB("Nacin finansiranja*          ", financing);
		
		//Button Panel
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton("ACCEPT");
		accept.setEnabled(false);
		accept.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				//TODO implement function to add an object of Student class to DBStudent
				StudentController.getInstance().addNewStudent(namePanel, surnamePanel, dateOfBirthPanel, homeAddressPanel, 
						phoneNumberPanel, emailAddressPanel, indexPanel, yoePanel, cyosPanel, budgetPanel);
				tp.updateView();
				exitDialog();
			} 
		});
		
		//exit button
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				exitDialog();
			} 
		});
		
		buttonsPanel.add(accept);
		buttonsPanel.add(exit);
		
		add(namePanel);
		add(surnamePanel);
		add(dateOfBirthPanel);
		add(homeAddressPanel);
		add(phoneNumberPanel);
		add(emailAddressPanel);
		add(indexPanel);
		add(yoePanel);
		add(cyosPanel);
		add(budgetPanel);
		add(buttonsPanel);
		
		setVisible(true);
	}
	
	public void exitDialog() {
		dispose();
	}
	
	public void checkAllFields() {
		if(namePanel.checkField() && surnamePanel.checkField() && dateOfBirthPanel.checkField() && homeAddressPanel.checkField() && 
				phoneNumberPanel.checkField() && emailAddressPanel.checkField() && indexPanel.checkField() && 
				yoePanel.checkField()) {
			
			accept.setEnabled(true);
			return;
		}
		
		accept.setEnabled(false);
		return;
	}

}
