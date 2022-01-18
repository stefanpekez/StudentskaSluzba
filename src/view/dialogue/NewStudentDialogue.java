package view.dialogue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.LanguageController;
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
		
		String first = LanguageController.getInstance().getResourceBundle().getString("First");
		String second = LanguageController.getInstance().getResourceBundle().getString("Second");
		String third = LanguageController.getInstance().getResourceBundle().getString("Third");
		String fourth = LanguageController.getInstance().getResourceBundle().getString("Fourth");
		
		
		String[] currentYearOfStudy = {first, second, third, fourth};
		String[] financing = { "B", "S"};
		
		setSize(500, 500);
		setLocationRelativeTo(mainframe);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("AddStudent"));
		setLayout(boxLayout);
		
		namePanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Name"), this, "");
		surnamePanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Surname"), this, "");
		dateOfBirthPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("DateOfBirth"), this, "dd/mm/yyyy");
		homeAddressPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Address"), this, "Ulica Broj, Mesto, Drzava");
		phoneNumberPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("PhoneNumber"), this, "");
		emailAddressPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Email"), this, "example@email.com");
		indexPanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Index"), this, "xx-yyy-zzzz (x-smer, y-broj, z-godina upisa)");
		yoePanel = new NewTF(LanguageController.getInstance().getResourceBundle().getString("YearOfEnrollment"), this, "");
		cyosPanel = new NewCB(LanguageController.getInstance().getResourceBundle().getString("CurrentYearOfStudy"), currentYearOfStudy);
		budgetPanel = new NewCB(LanguageController.getInstance().getResourceBundle().getString("FinanceType"), financing);
		
		//Button Panel
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton(LanguageController.getInstance().getResourceBundle().getString("AcceptButton"));
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
		JButton exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
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
		
		setResizable(false);
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
