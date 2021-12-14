package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.DBStudent;
import view.dialogue.NewCB;
import view.dialogue.NewTF;

public class StudentController {
	
	private static StudentController instance = null;
	
	public static StudentController getInstance() {
		if(instance == null) {
			instance = new StudentController();
		}
		
		return instance;
	}
	
	public void addNewStudent(NewTF namePanel, NewTF surnamePanel, NewTF dateOfBirthPanel, NewTF homeAddressPanel, 
			NewTF phoneNumberPanel, NewTF emailAddressPanel, NewTF indexPanel, NewTF yoePanel, NewCB cyosPanel, NewCB budgetPanel) {
		
		String studentName = namePanel.getTextField().getText();
		formatCheck("[A-Z]{1}[a-z]*$", studentName);
		
		String studentSurname = surnamePanel.getTextField().getText();
		formatCheck("[A-Z]{1}[a-z]*$", studentSurname);
		
		String studentBirth = dateOfBirthPanel.getTextField().getText();
		formatCheck("[0-9]{2}/[0-9]{2}/[0-9]{4}$", studentBirth);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate date;
		String pdateOfBirth = dateOfBirthPanel.getTextField().getText();
		
		date = LocalDate.parse(pdateOfBirth, formatter);
		
		String studentHomeAddress = homeAddressPanel.getTextField().getText();
		formatCheck("([A-Z]{1}[a-z]*\\s|[A-Z]{1}[a-z]*)|(\\s([A-Z]{1}[a-z]*|[a-z]*))\\s[0-9]{2,3},"
				+ "\\s([A-Z]{1}[a-z]*\\s|[A-Z]{1}[a-z]*)+,"
				+ "\\s([A-Z]{1}[a-z]*\\s|[A-Z]{1}[a-z]*)+$", studentHomeAddress);
		
		String studentPhoneNumber = homeAddressPanel.getTextField().getText();
		formatCheck("[0-9]+$", studentPhoneNumber);
		
		String studentEmail = emailAddressPanel.getTextField().getText();
		formatCheck("[a-z]+@[a-z]+\\.(com|acs\\.rs)$", studentEmail);
		
		String studentIndex = indexPanel.getTextField().getText();
		formatCheck("[a-z]{2}-[0-9]{3}-[0-9]{4}$", studentIndex);
		
		int studentYOE = Integer.parseInt(yoePanel.getTextField().getText());
		formatCheck("[0-9]{4}$", yoePanel.getTextField().getText());
		
		int studentCYOS = 1 + cyosPanel.getComboBox().getSelectedIndex();
		System.out.println(studentCYOS);
		
		int studentFinansing = budgetPanel.getComboBox().getSelectedIndex();
		
		DBStudent.getInstance().addNewStudent(studentName, studentSurname, date, studentHomeAddress, studentPhoneNumber, 
				studentEmail, studentIndex, studentYOE, studentCYOS, studentFinansing);
	}
	
	private void formatCheck(String regexPattern, String textField) {
		Pattern pattern = Pattern.compile(regexPattern);
	    Matcher matcher = pattern.matcher(textField);
	    
	    //Delete comments to check if regex worked
	    //TODO implement dialog if regex is rejected
	    
	    /*boolean matchFound = matcher.find();
	    if(matchFound) {
	      System.out.println("Match " + textField + " found");
	    } else {
	      System.out.println("Match " + textField + " not found");
	    }*/
	}
	
}