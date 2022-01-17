package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.DBExams;
import model.DBExamsPassed;
import model.DBStudent;
import model.Grade;
import model.Student;
import model.Subject;
import view.dialogue.NewCB;
import view.dialogue.NewTF;
import view.dialogue.edit.EditCB;
import view.dialogue.edit.EditTF;

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
		
		String studentSurname = surnamePanel.getTextField().getText();
		
		String studentBirth = dateOfBirthPanel.getTextField().getText();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate date;
		String pdateOfBirth = dateOfBirthPanel.getTextField().getText();
		
		date = LocalDate.parse(pdateOfBirth, formatter);
		
		String studentHomeAddress = homeAddressPanel.getTextField().getText();
		
		String studentPhoneNumber = phoneNumberPanel.getTextField().getText();
		
		String studentEmail = emailAddressPanel.getTextField().getText();
		
		String studentIndex = indexPanel.getTextField().getText();
		
		int studentYOE = Integer.parseInt(yoePanel.getTextField().getText());
		
		int studentCYOS = 1 + cyosPanel.getComboBox().getSelectedIndex();
		
		int studentFinansing = budgetPanel.getComboBox().getSelectedIndex();
		
		DBStudent.getInstance().addNewStudent(studentName, studentSurname, date, studentHomeAddress, studentPhoneNumber, 
				studentEmail, studentIndex, studentYOE, studentCYOS, studentFinansing);
	}

	public void editStudent(int selectedStudentIdx, EditTF namePanel, EditTF surnamePanel, EditTF dateOfBirthPanel, EditTF homeAddressPanel, 
			EditTF phoneNumberPanel, EditTF emailAddressPanel, EditTF indexPanel, EditTF yoePanel, EditCB cyosPanel, EditCB budgetPanel) {
		
		String studentName = namePanel.getTextField().getText();
		
		String studentSurname = surnamePanel.getTextField().getText();
		
		String studentBirth = dateOfBirthPanel.getTextField().getText();
		
		LocalDate date = LocalDate.parse(studentBirth);
		
		String studentHomeAddress = homeAddressPanel.getTextField().getText();
		
		String studentPhoneNumber = phoneNumberPanel.getTextField().getText();
		
		String studentEmail = emailAddressPanel.getTextField().getText();
		
		String studentIndex = indexPanel.getTextField().getText();
		
		int studentYOE = Integer.parseInt(yoePanel.getTextField().getText());
		
		int studentCYOS = 1 + cyosPanel.getComboBox().getSelectedIndex();
		
		int studentFinansing = budgetPanel.getComboBox().getSelectedIndex();
		
		DBStudent.getInstance().editStudent(selectedStudentIdx, studentName, studentSurname, date, studentHomeAddress, studentPhoneNumber, 
				studentEmail, studentIndex, studentYOE, studentCYOS, studentFinansing);
		
	}
	
	public void setupCurrentExamsDB(int row) {
		
		ArrayList<Subject> subjects  = DBStudent.getInstance().getSelectedStudent(row).getRemainingExams();
		
		DBExams.getInstance().init(subjects);
	}
	
	/*public void setupPassedExamsDB(int index) {
		ArrayList<Grade> grades = DBStudent.getInstance().getSelectedStudent(index).getPassedExams();
		
		DBExamsPassed.getInstance().initPassedExams(grades);
	}*/
	
	public void flushExamsDB() {
		DBExams.getInstance().del();
	}
	
	public boolean forwardGrade(int subject, int student, int grade, String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate datee;
		datee = LocalDate.parse(date, formatter);
		
		//get subject
		Subject subj = DBExams.getInstance().getSelectedExam(subject);
		//get student
		Student stud = DBStudent.getInstance().getSelectedStudent(student);
		
		//make grade
		Grade gra = new Grade(stud, subj, grade + 6, datee);
		
		//add grade to the student
		DBStudent.getInstance().addGrade(gra, student);
		
		//delete the exam from unpassed list
		DBExams.getInstance().removeExam(subject);
		DBStudent.getInstance().removeUnpassedExam(student, subj);
		
		return true;
	}
	
	public void searchStudent(String search) {
		
		String[] searchParts = search.split(",");
		
		switch(searchParts.length) {
			case 1:
				DBStudent.getInstance().searchOneWord(search);
				break;
			case 2:
				DBStudent.getInstance().searchTwoWords(search);
				break;
			case 3:
				DBStudent.getInstance().searchThreeWords(search);
				break;
			default:
				System.out.println("Wrong search format");
				break;
		}
		
		
	}
	
	public boolean cancelGrade(int grade) {
		Grade cancel = DBExamsPassed.getInstance().getSelectedGrade(grade);
		DBExamsPassed.getInstance().removeGrade(grade);
		
		// remove from student passed exam
		DBStudent.getInstance().removePassedExam(cancel.getStudent(), cancel);
		// add exam to student
		DBStudent.getInstance().addUnpassedExam(cancel.getStudent(), cancel.getSubject());
		return true;
	}
	
	public void setupPassedExamsDB(int index) {
		ArrayList<Grade> grades  = DBStudent.getInstance().getSelectedStudent(index).getPassedExams();
		
		DBExamsPassed.getInstance().init(grades);
	}
	
	public void listPassedExams(int student) {
		for(Grade g: DBStudent.getInstance().getSelectedStudent(student).getPassedExams()) {
			System.out.println(g.toString());
		}
	}
	
	public void deleteStudent(int index) {
		DBStudent.getInstance().deleteStudent(index);
	}
	
	public String getExamID(int row) {
		return DBExams.getInstance().getValueAt(row, 0);
	}
	
	public String getExamName(int row) {
		return DBExams.getInstance().getValueAt(row, 1);
	}
	
	public String getName(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getName();
	}
	
	public String getSurname(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getSurname();
	}
	
	public String getDateOfBirth(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getDateOfBirth().toString();
	}
	
	public String getHomeAddress(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getHomeAddress().toString();
	}
	
	public String getPhoneNumber(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getPhoneNumber();
	}
	
	public String getEmailAddress(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getEmailAddress();
	}
	
	public String getIndexNumber(int row){
		return DBStudent.getInstance().getSelectedStudent(row).getIndexNum();
	}
	
	public String getYearOfEnrollment(int row){
		return Integer.toString(DBStudent.getInstance().getSelectedStudent(row).getYearOfEnrollment());
	}
	
	public String getCurrentYearOfStudy(int row){
		return Integer.toString(DBStudent.getInstance().getSelectedStudent(row).getCurrentYearOfStudy());
	}
	
	public String getBudgetStatus(int row) {
		return Integer.toString(DBStudent.getInstance().getSelectedStudent(row).getStatus());
	}
	
}
