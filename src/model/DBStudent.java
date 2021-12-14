package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DBStudent {
	
	private static DBStudent instance = null;
	
	public static DBStudent getInstance() {
		if(instance == null) {
			instance = new DBStudent();
		}
		return instance;
	}
	
	private ArrayList<Student> students;
	private ArrayList<String> columns;
	
	private DBStudent() {
		columns = new ArrayList<String>();
		columns.add("INDEKS");
		columns.add("IME");
		columns.add("PREZIME");
		columns.add("GODINA STUDIJA");
		columns.add("STATUS");
		columns.add("PROSEK");
		
		initStudents();
		
	}
	
	private void initStudents() {
		students = new ArrayList<Student>();
		
		students.add(new Student("Pekez", "Stefan", "ra-179-2019", 3, StudentStatus.B, 9.13));
		students.add(new Student("Milosevic", "Filip", "ra-193-2019", 3, StudentStatus.B, 10.0));
	}
	
	public int getRowCount() {
		return students.size();
	}
	
	public int getColumnCount() {
		return 6;
	}
	
	public String getColumnName(int column) {
		return columns.get(column);
	}
	
	public String getValueAt(int row, int column) {
		Student stud = students.get(row);
		
		switch (column) {
		case 0:
			return stud.getIndexNum();
		case 1:
			return stud.getName();
		case 2:
			return stud.getSurname();
		case 3:
			return Integer.toString(stud.getCurrentYearOfStudy());
		case 4:
			if(stud.getStatus() == StudentStatus.B) {
				return "B";
			}
			else {
				return "S";
			}
		case 5:
			return Double.toString(stud.getAvgGrade());
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + column);
		}
	
	}
	
	public void addNewStudent(String name, String surname, LocalDate date, String homeAdress, String phoneNumber, 
			String emailAddress, String index, int yearOfEnrollment, int currentYearOfStudy, int status) {

		students.add(new Student(name, surname, date, homeAdress, phoneNumber, emailAddress, 
				index, yearOfEnrollment, currentYearOfStudy, status));
	}
	
	public void deleteStudent(int deleteIndex) {
		students.remove(deleteIndex);
	}
	
}
