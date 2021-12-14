package view.dialogue.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ProfessorController;
import model.Professor;
import view.TabbedPane.TablePanel;

public class EditProfessorInfo extends JPanel {

	private static final long serialVersionUID = 5180996724653669104L;
	
	private EditTF surname;
	private EditTF name;
	private EditTF dateOfBirth;
	private EditTF address;
	private EditTF phoneNumber;
	private EditTF emailAdress;
	private EditTF officeAdress;
	private EditTF idNumber;
	private EditTF title;
	private EditTF workingYears;
	
	private JButton ok;
	private JButton exit;
	
	public EditProfessorInfo(EditProfessorDialogue dialogue, TablePanel panel) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		int row = panel.getTable().getSelectedRow();
		
		surname = new EditTF("Surname*", ProfessorController.getInstance().getSurname(row), this);
		name = new EditTF("Name*", ProfessorController.getInstance().getName(row), this);
		dateOfBirth = new EditTF("dateOfBirth*", ProfessorController.getInstance().getDateOfBirth(row), this);
		address = new EditTF("Address*", ProfessorController.getInstance().getAddress(row), this);
		phoneNumber = new EditTF("Phone*", ProfessorController.getInstance().getPhoneNumber(row), this);
		emailAdress = new EditTF("Email*", ProfessorController.getInstance().getEmailAddress(row), this);
		officeAdress = new EditTF("Office Adress*", ProfessorController.getInstance().getOfficeAddress(row), this);
		idNumber = new EditTF("ID*", ProfessorController.getInstance().getIdNumber(row), this);
		title = new EditTF("Title*", ProfessorController.getInstance().getTitle(row), this);
		workingYears = new EditTF("Working Years*",ProfessorController.getInstance().getWorkingYears(row), this);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				ProfessorController.getInstance().editProfessor(row, surname.getTextField().getText(), name.getTextField().getText(), dateOfBirth.getTextField().getText(), address.getTextField().getText(), 
																phoneNumber.getTextField().getText(), emailAdress.getTextField().getText(), officeAdress.getTextField().getText(), 
																idNumber.getTextField().getText(), title.getTextField().getText(), workingYears.getTextField().getText());
				panel.updateView();
				dialogue.dispose();
			}
			
		});
		
		exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogue.dispose();
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.add(ok);
		buttons.add(exit);
		
		add(surname);
		add(name);
		add(dateOfBirth);
		add(address);
		add(phoneNumber);
		add(emailAdress);
		add(officeAdress);
		add(idNumber);
		add(title);
		add(workingYears);
		add(buttons);
		
	}
	
	public void checkAllFields() {
		if(surname.checkField() && name.checkField() && dateOfBirth.checkField() && address.checkField() && 
			phoneNumber.checkField() && emailAdress.checkField() && officeAdress.checkField() && 
			idNumber.checkField() && title.checkField() && workingYears.checkField()) {
			
			ok.setEnabled(true);
			return;
		}
		
		ok.setEnabled(false);
		return;
	}
}
