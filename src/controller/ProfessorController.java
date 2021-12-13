package controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Address;
import model.DBProfessor;
import view.dialogue.NewTF;

public class ProfessorController {
	
	private static ProfessorController instance = null;
	
	public static ProfessorController getInstance() {
		if(instance == null) {
			instance = new ProfessorController();
		}
		
		return instance;
	}
	
	public boolean addNewProfessor(NewTF surname, NewTF name, NewTF dateOfBirth, NewTF address, NewTF phoneNumber, NewTF emailAdress, 
								   NewTF officeAdress, NewTF idNumber, NewTF title, NewTF workingYears) {
		
		//checking if date is correct
		Pattern patterndate = Pattern.compile("[0-9]{1,2}/[0-9]{2}/[0-9]{1,4}");
		Matcher datematcher = patterndate.matcher(dateOfBirth.getTextField().getText());
		boolean matchfound = datematcher.find();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate date;
		String pdateOfBirth = dateOfBirth.getTextField().getText();
		
		if(matchfound) {
			System.out.println("Dobar date format");
			date = LocalDate.parse(pdateOfBirth, formatter);
			
		}else {
			System.out.println("Los date format");
			return false;
		}
		
		//check for addresses
		String paddress = address.getTextField().getText();
		String pofficeAdress = officeAdress.getTextField().getText();
		String pemailAdress = emailAdress.getTextField().getText();
		
		//check for workingyears
		int pworkingYears;
		
		try {
			pworkingYears = Integer.parseInt(workingYears.getTextField().getText());
		} catch (NumberFormatException e) {
			System.out.println("Cant convert working years");
			return false;
		}
		
		String psurname = surname.getTextField().getText();
		String pname = name.getTextField().getText();
		String pphoneNumber = phoneNumber.getTextField().getText();
		String pidNumber = idNumber.getTextField().getText();
		String ptitle = title.getTextField().getText();
		
		
		System.out.println(date);
		
		DBProfessor.getInstance().addNewProfessor(psurname, pname, date,new Address("",0,"",""), pphoneNumber, pemailAdress,new Address("",0,"",""), pidNumber, ptitle, pworkingYears);
		
		return true;
	}
	
	public boolean deleteProfessor(int rowindex) {
		DBProfessor.getInstance().deleteProfessor(rowindex);
		return true;
	}
	
}
