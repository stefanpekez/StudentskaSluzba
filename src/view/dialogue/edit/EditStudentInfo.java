package view.dialogue.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.StudentController;
import view.TabbedPane.TablePanel;

public class EditStudentInfo extends JPanel {
	
	private EditTF namePanel;
	private EditTF surnamePanel;
	private EditTF dateOfBirthPanel;
	private EditTF homeAddressPanel;
	private EditTF phoneNumberPanel;
	private EditTF emailAddressPanel;
	private EditTF indexPanel;
	private EditTF yoePanel;
	private EditCB cyosPanel;
	private EditCB budgetPanel;
	private JPanel buttonsPanel;
	private JButton accept;
	
	public EditStudentInfo(EditStudentDialogue editDialogue, TablePanel tp) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		String[] currentYearOfStudy = { "I (prva)", "II (druga)", "III (treca)", "IV (cetvrta)"};
		String[] financing = { "B", "S"};
		
		int selectedStudentIdx = tp.getTable().getSelectedRow();
		
		namePanel = new EditTF("Ime*", this, StudentController.getInstance().getName(selectedStudentIdx));
		surnamePanel = new EditTF("Prezime*", this, StudentController.getInstance().getSurname(selectedStudentIdx));
		dateOfBirthPanel = new EditTF("Datum rodjenja*", this, StudentController.getInstance().getDateOfBirth(selectedStudentIdx));
		homeAddressPanel = new EditTF("Adresa*", this, StudentController.getInstance().getHomeAddress(selectedStudentIdx));
		phoneNumberPanel = new EditTF("Broj telefona*", this, StudentController.getInstance().getPhoneNumber(selectedStudentIdx));
		emailAddressPanel = new EditTF("E-mail*", this, StudentController.getInstance().getEmailAddress(selectedStudentIdx));
		indexPanel = new EditTF("Index*", this, StudentController.getInstance().getIndexNumber(selectedStudentIdx));
		yoePanel = new EditTF("Godina upisa*", this, StudentController.getInstance().getYearOfEnrollment(selectedStudentIdx));
		cyosPanel = new EditCB("Trenutna godina studija*     ", currentYearOfStudy, StudentController.getInstance().getCurrentYearOfStudy(selectedStudentIdx), "cyos");
		budgetPanel = new EditCB("Nacin finansiranja*               ", financing, StudentController.getInstance().getBudgetStatus(selectedStudentIdx), "b");

		
		//Button Panel
		buttonsPanel = new JPanel();

		//accept button
		accept = new JButton("ACCEPT");
		accept.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				StudentController.getInstance().editStudent(selectedStudentIdx, namePanel, surnamePanel, dateOfBirthPanel, homeAddressPanel, 
						phoneNumberPanel, emailAddressPanel, indexPanel, yoePanel, cyosPanel, budgetPanel);
				tp.updateView();
				exitDialog(editDialogue);
			} 
		});

		//exit button
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				exitDialog(editDialogue);
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
		
	}
	
	public void exitDialog(JDialog editDialogue) {
		editDialogue.dispose();
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
