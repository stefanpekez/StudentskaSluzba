package view.dialogue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.LanguageController;
import controller.ProfessorController;
import utilities.FormatCheckRegex;
import view.TabbedPane.TablePanel;

public class NewProfessorDialogue extends JDialog {

	private static final long serialVersionUID = -4499849255895981874L;
	
	private NewTF surname;
	private NewTF name;
	private NewTF dateOfBirth;
	private NewTF address;
	private NewTF phoneNumber;
	private NewTF emailAdress;
	private NewTF officeAdress;
	private NewTF idNumber;
	private NewTF title;
	private NewTF workingYears;
	
	private JButton ok;
	private JButton exit;
	
	
	public NewProfessorDialogue(Container parent, TablePanel panel) {
		setTitle(LanguageController.getInstance().getResourceBundle().getString("NewProfessorTitle"));
		
		setSize(500, 500);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(parent);
		
		surname = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Surname"), this, "");
		name = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Name"), this, "");
		dateOfBirth = new NewTF(LanguageController.getInstance().getResourceBundle().getString("DateOfBirth"), this, "dd/mm/yyyy");
		address = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Address"), this, "Street Number, City, Country");
		phoneNumber = new NewTF(LanguageController.getInstance().getResourceBundle().getString("PhoneNumber"), this, "");
		emailAdress = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Email"), this, "example@uns.ac.rs");
		officeAdress = new NewTF(LanguageController.getInstance().getResourceBundle().getString("OfficeAddress"), this, "Street Number, City, Country");
		idNumber = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Id"), this, "");
		title = new NewTF(LanguageController.getInstance().getResourceBundle().getString("Title"), this, "");
		workingYears = new NewTF(LanguageController.getInstance().getResourceBundle().getString("WorkingYears"), this, "");
		
		ok = new JButton(LanguageController.getInstance().getResourceBundle().getString("AcceptButton"));
		ok.setEnabled(false);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean good = ProfessorController.getInstance().addNewProfessor(surname, name, dateOfBirth, address, phoneNumber, emailAdress, officeAdress, idNumber, title, workingYears);
				if(good) {
					System.out.println("Successfully added professor");
				} else {
					System.out.println("Something went wrong");
				}
				panel.updateView();
				dispose();
			}
		});
		
		exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
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
		if(surname.checkField() && name.checkField() && dateOfBirth.checkField(FormatCheckRegex.DATE_REG) && address.checkField(FormatCheckRegex.ADDRESS_REG) && 
			phoneNumber.checkField(FormatCheckRegex.PHONE_REG) && emailAdress.checkField(FormatCheckRegex.EMAIL_REG) && officeAdress.checkField(FormatCheckRegex.ADDRESS_REG) && 
			idNumber.checkField(FormatCheckRegex.NUMBERS_REG) && title.checkField() && workingYears.checkField(FormatCheckRegex.NUMBERS_REG)) {
			
			ok.setEnabled(true);
			return;
		}
		
		ok.setEnabled(false);
		return;
	}

}
