package controller;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Address;
import model.DBProfessor;
import model.DBTeaches;
import model.Subject;
import view.dialogue.NewCB;
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
								   NewTF officeAdress, NewTF idNumber, NewCB title, NewTF workingYears) {
		
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
		
		String ptitle = null;
		switch(title.getComboBox().getSelectedIndex()) {
			case 0:
				ptitle = "REGULAR_PROFESSOR";
				break;
			case 1:
				ptitle = "IREGULAR_PROFESSOR";
				break;
			case 2:
				ptitle = "DOCENT";
				break;
			default:
				break;
		}
		
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
	
	public void searchProfessor(String query) {
		
		String[] parsedQuery = query.split(",");
		
		switch (parsedQuery.length) {
		case 1:
			DBProfessor.getInstance().professorSearchOne(parsedQuery[0].replaceAll("\\W", ""));
			break;
		case 2:
			DBProfessor.getInstance().professorSearchTwo(parsedQuery[0].replaceAll("\\W", ""), parsedQuery[1].replaceAll("\\W", ""));
			break;
		default:
			System.out.println("Wrong query format");
			break;
		}
	}
	
	public void setupTaughtSubjects(int row) {
		DBProfessor.getInstance().setupTaughtSubjects(row);
	}
	
	public void addSubjectTeach(List<String> selectedSubjects, int selectedProfessor) {
		DBTeaches.getInstance().addSubjectTeach(selectedSubjects, selectedProfessor);
	}
	
	public void serialize() throws IOException {
		DBProfessor.getInstance().serialize();
	}
	
	public String[] getProfessorList() {
		return DBProfessor.getInstance().getProfessorList();
	}
	
	public String[] getProfessorDepartmentList() {
		return DBProfessor.getInstance().getProfessorDepartmentList();
	}
	
	public boolean deleteProfessor(int rowindex) {
		DBProfessor.getInstance().deleteProfessor(rowindex);
		return true;
	}
	
	public String[] getProfessorsOverWorkingYearLimit(int departmentId) {
		return DBProfessor.getInstance().getProfessorsOverWorkingYearLimit(departmentId);
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
