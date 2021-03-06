package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Professor {

		private int primaryId;
	
		private String surname;
		private String name;
		private LocalDate dateOfBirth;
		private Address homeAdress;
		private String phoneNumber;
		private String emailAdress;
		private Address officeAdress;
		private String idNumber;
		private String title;
		private int workingYears;
		private ArrayList<Subject> subjects;
		
		int departmentID = -1;
		
		public Professor(String surname, String name, LocalDate dateOfBirth, Address homeAdress, String phoneNumber,
				String emailAdress, Address officeAdress, String idNumber, String title, int workingYears) {
			super();
			this.surname = surname;
			this.name = name;
			this.dateOfBirth = dateOfBirth;
			this.homeAdress = homeAdress;
			this.phoneNumber = phoneNumber;
			this.emailAdress = emailAdress;
			this.officeAdress = officeAdress;
			this.idNumber = idNumber;
			this.title = title;
			this.workingYears = workingYears;
			this.subjects = new ArrayList<Subject>();
		}
		
		public Professor() {
			this.subjects = new ArrayList<Subject>();
		}
		
		
		public Professor(String name, String surname, String title) {
			this.name = name;
			this.surname = surname;
			this.title = title;
		}
		
		

		public int getPrimaryId() {
			return primaryId;
		}

		public void setPrimaryId(int primaryId) {
			this.primaryId = primaryId;
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

		public Address getHomeAdress() {
			return homeAdress;
		}

		public void setHomeAdress(Address homeAdress) {
			this.homeAdress = homeAdress;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getEmailAdress() {
			return emailAdress;
		}

		public void setEmailAdress(String emailAdress) {
			this.emailAdress = emailAdress;
		}

		public Address getOfficeAdress() {
			return officeAdress;
		}

		public void setOfficeAdress(Address officeAdress) {
			this.officeAdress = officeAdress;
		}

		public String getIdNumber() {
			return idNumber;
		}

		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getWorkingYears() {
			return workingYears;
		}

		public void setWorkingYears(int workingYears) {
			this.workingYears = workingYears;
		}

		public ArrayList<Subject> getSubjects() {
			return subjects;
		}

		public void setSubjects(ArrayList<Subject> subjects) {
			this.subjects = subjects;
		}
		
		public void addSubject(Subject subject) {
			this.subjects.add(subject);
		}
		
		public void removeTaughtSubject(Subject sub) {
			this.subjects.remove(sub);
		}
		
		
		public int getDepartmentID() {
			return departmentID;
		}

		public Professor setDepartmentID(int departmentID) {
			this.departmentID = departmentID;
			return this;
		}

		@Override
		public String toString() {
			return this.idNumber + "," + this.name + " " + this.surname + "," + this.title;
		}
			
}
