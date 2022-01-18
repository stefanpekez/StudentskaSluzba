package view.dialogue.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.LanguageController;
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
		
		String first = LanguageController.getInstance().getResourceBundle().getString("First");
		String second = LanguageController.getInstance().getResourceBundle().getString("Second");
		String third = LanguageController.getInstance().getResourceBundle().getString("Third");
		String fourth = LanguageController.getInstance().getResourceBundle().getString("Fourth");
		
		
		String[] currentYearOfStudy = {first, second, third, fourth};
		String[] financing = { "B", "S"};
		
		int selectedStudentIdx = tp.getTable().convertRowIndexToModel(tp.getTable().getSelectedRow());
		
		namePanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Name"), this, StudentController.getInstance().getName(selectedStudentIdx));
		surnamePanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Surname"), this, StudentController.getInstance().getSurname(selectedStudentIdx));
		dateOfBirthPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("DateOfBirth"), this, StudentController.getInstance().getDateOfBirth(selectedStudentIdx));
		homeAddressPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Address"), this, StudentController.getInstance().getHomeAddress(selectedStudentIdx));
		phoneNumberPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("PhoneNumber"), this, StudentController.getInstance().getPhoneNumber(selectedStudentIdx));
		emailAddressPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Email"), this, StudentController.getInstance().getEmailAddress(selectedStudentIdx));
		indexPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Index"), this, StudentController.getInstance().getIndexNumber(selectedStudentIdx));
		yoePanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("YearOfEnrollment"), this, StudentController.getInstance().getYearOfEnrollment(selectedStudentIdx));
		cyosPanel = new EditCB(LanguageController.getInstance().getResourceBundle().getString("CurrentYearOfStudy"), currentYearOfStudy, StudentController.getInstance().getCurrentYearOfStudy(selectedStudentIdx), "cyos");
		budgetPanel = new EditCB(LanguageController.getInstance().getResourceBundle().getString("FinanceType"), financing, StudentController.getInstance().getBudgetStatus(selectedStudentIdx), "b");

		
		//Button Panel
		buttonsPanel = new JPanel();

		//accept button
		accept = new JButton(LanguageController.getInstance().getResourceBundle().getString("AcceptButton"));
		accept.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				StudentController.getInstance().editStudent(selectedStudentIdx, namePanel, surnamePanel, dateOfBirthPanel, homeAddressPanel, 
						phoneNumberPanel, emailAddressPanel, indexPanel, yoePanel, cyosPanel, budgetPanel);
				tp.updateView();
				exitDialog(editDialogue);
			} 
		});

		//exit button
		JButton exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
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
