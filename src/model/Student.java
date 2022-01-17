package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


enum StudentStatus {
	B, // budget
	S; // self-financing
}

public class Student {

	private String surname;
	private String name;
	private LocalDate dateOfBirth;
	private Address homeAddress;
	private String phoneNumber;
	private String emailAddress;
	private String indexNum;
	private int yearOfEnrollment;
	private int currentYearOfStudy;
	private StudentStatus status;
	private double avgGrade;
	private ArrayList<Grade> passedExams;
	private ArrayList<Subject> remainingExams;
	
	public Student(String surname, String name, LocalDate dateOfBirth, Address homeAddress, String phoneNumber,
			String emailAddress, String indexNum, int yearOfEnrollment, int currentYearOfStudy, StudentStatus status,
			double avgGrade) {
		super();
		this.surname = surname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.indexNum = indexNum;
		this.yearOfEnrollment = yearOfEnrollment;
		this.currentYearOfStudy = currentYearOfStudy;
		this.status = status;
		this.avgGrade = avgGrade;
		this.passedExams = new ArrayList<Grade>();
		this.remainingExams = new ArrayList<Subject>();
	}
	
	public Student (String name, String surname, LocalDate date, Address homeAdress, String phoneNumber, String emailAddress, 
			String index, int yearOfEnrollment, int currentYearOfStudy, int status, double avgGrade) {
		super();
		this.name = name;
		this.surname = surname;
		
		this.dateOfBirth = date;
		this.homeAddress.setStreet(homeAdress.getStreet());
		
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.indexNum = index;
		this.yearOfEnrollment = yearOfEnrollment;
		this.currentYearOfStudy = currentYearOfStudy;
		if(status == 0)
			this.status = StudentStatus.B;
		else
			this.status = StudentStatus.S;
		
		this.avgGrade = avgGrade;
		//this.passedExams = new ArrayList<Grade>();
		//this.remainingExams = new ArrayList<Grade>();
	}
	
	public Student (String name, String surname, LocalDate date, String homeAdress, String phoneNumber, String emailAddress, 
			String index, int yearOfEnrollment, int currentYearOfStudy, int status) {
		super();
		this.name = name;
		this.surname = surname;
		
		this.dateOfBirth = date;
		this.homeAddress = new Address(homeAdress, "", "", "");
		
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.indexNum = index;
		this.yearOfEnrollment = yearOfEnrollment;
		this.currentYearOfStudy = currentYearOfStudy;
		if(status == 0)
			this.status = StudentStatus.B;
		else
			this.status = StudentStatus.S;
		
		//this.avgGrade = avgGrade;
		this.passedExams = new ArrayList<Grade>();
		this.remainingExams = new ArrayList<Subject>();
	}
	
	public Student() {
		super();
		this.passedExams = new ArrayList<Grade>();
		this.remainingExams = new ArrayList<Subject>();
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}

	public int getYearOfEnrollment() {
		return yearOfEnrollment;
	}

	public void setYearOfEnrollment(int yearOfEnrollment) {
		this.yearOfEnrollment = yearOfEnrollment;
	}

	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public int getStatus() {
		return this.status == StudentStatus.B ? 0 : 1;
	}

	public void setStatus(int status) {
		this.status = status == 0 ? StudentStatus.B : StudentStatus.S;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}

	public ArrayList<Grade> getPassedExams() {
		return passedExams;
	}

	public void setPassedExams(ArrayList<Grade> passedExams) {
		this.passedExams = passedExams;
	}

	public ArrayList<Subject> getRemainingExams() {
		return remainingExams;
	}

	public void setRemainingExams(ArrayList<Subject> remainingExams) {
		this.remainingExams = remainingExams;
	}
	
	public void addRemainingExam(Subject exam) {
		this.remainingExams.add(exam);
	}
	
	/*public void removeUnpassedExam(Subject sub) {
		int i = 0;
		for(Subject s: remainingExams) {
			if(sub.equals(s)) {
				remainingExams.remove(i);
			}
			i++;
		}
	}*/
	
	public void removeUnpassedExam(Subject sub) {
		this.remainingExams.remove(sub);
	}
	
	public void removePassedExam(Grade grade) {
		passedExams.remove(grade);
	}
	
	public void addGrade(Grade g) {
		passedExams.add(g);
	}
	
	public void addUnpassedexam(Subject s) {
		remainingExams.add(s);
	}
	
	/*public void removeUnpassedExam(Subject subject) {
		remainingExams.remove(subject);
	}*/
}
