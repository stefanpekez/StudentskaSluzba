package controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Address;
import model.DBProfessor;
import model.Professor;
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
		
		return DBProfessor.getInstance().addNewProfessor(psurname, pname, date,new Address(paddress), pphoneNumber, pemailAdress,new Address(pofficeAdress), pidNumber, ptitle, pworkingYears);
		
	}
	
	public boolean editProfessor(int row, String surname, String name, String dateOfBirth, String address, String phoneNumber, String emailAdress, 
								   String officeAdress, String idNumber, String title, String workingYears) {
		
		LocalDate date;
		int yrs;
		
		try {
			date = LocalDate.parse(dateOfBirth);
			yrs = Integer.parseInt(workingYears);	
		} catch (DateTimeParseException e) {
			System.out.println("Wrong date format");
			return false;
		} catch (NumberFormatException e) {
			System.out.println("Cant convert to int");
			return false;
		}
		
		
		return DBProfessor.getInstance().editProfessor(row, surname, name, date, new Address(address), phoneNumber, emailAdress, new Address(officeAdress), idNumber, title, yrs);
		
	}
	
	public boolean deleteProfessor(int rowindex) {
		DBProfessor.getInstance().deleteProfessor(rowindex);
		return true;
	}
	
	public String getSurname(int row){
		return DBProfessor.getInstance().getProfessor(row).getSurname();
	}
	public String getName(int row){
		return DBProfessor.getInstance().getProfessor(row).getName();
	}
	public String getDateOfBirth(int row){
		LocalDate date = DBProfessor.getInstance().getProfessor(row).getDateOfBirth();
		if(date != null) return date.toString();
		
		return "";
	}
	public String getAddress(int row){
		return DBProfessor.getInstance().getProfessor(row).getHomeAdress().toString();
	}
	public String getPhoneNumber(int row){
		return DBProfessor.getInstance().getProfessor(row).getPhoneNumber();
	}
	public String getEmailAddress(int row){
		return DBProfessor.getInstance().getProfessor(row).getEmailAdress();
	}
	public String getOfficeAddress(int row){
		return DBProfessor.getInstance().getProfessor(row).getOfficeAdress().toString();
	}
	public String getIdNumber(int row){
		return DBProfessor.getInstance().getProfessor(row).getIdNumber();
	}
	public String getTitle(int row){
		return DBProfessor.getInstance().getProfessor(row).getTitle();
	}
	public String getWorkingYears(int row){
		return Integer.toString(DBProfessor.getInstance().getProfessor(row).getWorkingYears());
	}

	
}
