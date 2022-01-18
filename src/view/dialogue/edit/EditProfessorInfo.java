package view.dialogue.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.LanguageController;
import controller.ProfessorController;
import utilities.FormatCheckRegex;
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
		
		
		int row = panel.getTable().convertRowIndexToModel(panel.getTable().getSelectedRow());
		
		surname = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Surname"), ProfessorController.getInstance().getSurname(row), this);
		name = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Name"), ProfessorController.getInstance().getName(row), this);
		dateOfBirth = new EditTF(LanguageController.getInstance().getResourceBundle().getString("DateOfBirth"), ProfessorController.getInstance().getDateOfBirth(row), this);
		address = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Address"), ProfessorController.getInstance().getAddress(row), this);
		phoneNumber = new EditTF(LanguageController.getInstance().getResourceBundle().getString("PhoneNumber"), ProfessorController.getInstance().getPhoneNumber(row), this);
		emailAdress = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Email"), ProfessorController.getInstance().getEmailAddress(row), this);
		officeAdress = new EditTF(LanguageController.getInstance().getResourceBundle().getString("OfficeAddress"), ProfessorController.getInstance().getOfficeAddress(row), this);
		idNumber = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Id"), ProfessorController.getInstance().getIdNumber(row), this);
		title = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Title"), ProfessorController.getInstance().getTitle(row), this);
		workingYears = new EditTF(LanguageController.getInstance().getResourceBundle().getString("WorkingYears"),ProfessorController.getInstance().getWorkingYears(row), this);
		
		ok = new JButton(LanguageController.getInstance().getResourceBundle().getString("OkButton"));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				boolean good = ProfessorController.getInstance().editProfessor(row, surname.getTextField().getText(), name.getTextField().getText(), dateOfBirth.getTextField().getText(), address.getTextField().getText(), 
																phoneNumber.getTextField().getText(), emailAdress.getTextField().getText(), officeAdress.getTextField().getText(), 
																idNumber.getTextField().getText(), title.getTextField().getText(), workingYears.getTextField().getText());
				if(good) {
					System.out.println("Successfully edited professor");
				} else {
					System.out.println("Something went wrong");
				}
				panel.updateView();
				dialogue.dispose();
			}
			
		});
		
		exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
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
		if(surname.checkField() && name.checkField() && dateOfBirth.checkField(FormatCheckRegex.DATE1_REG) && address.checkField(FormatCheckRegex.ADDRESS_REG) && 
			phoneNumber.checkField(FormatCheckRegex.PHONE_REG) && emailAdress.checkField(FormatCheckRegex.EMAIL_REG) && officeAdress.checkField(FormatCheckRegex.ADDRESS_REG) && 
			idNumber.checkField(FormatCheckRegex.NUMBERS_REG) && title.checkField() && workingYears.checkField(FormatCheckRegex.NUMBERS_REG)) {
			
			ok.setEnabled(true);
			return;
		}
		
		ok.setEnabled(false);
		return;
	}
}
