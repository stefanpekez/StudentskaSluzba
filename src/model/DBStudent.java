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
		
		students.add(new Student("Pekez", "Stefan", LocalDate.parse("2000-11-27"), new Address("nme","","",""), "0645871486", 
				"stefanpekez00@gmail.com", "ra-179-2019", 2019, 3, StudentStatus.B, 9.13));
		students.add(new Student("Milosevic", "Filip", LocalDate.parse("2001-01-29"), new Address("nme","","",""), "00000000000", 
				"milosevicfilip@gmail.com", "ra-193-2019", 2019, 3, StudentStatus.B, 10.0));
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
	
	public ArrayList<Student> getStudents() {
		return students;
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
			if(stud.getStatus() == 0) {
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
	
	public void editStudent(int selectedStudentIdx, String name, String surname, LocalDate date, String homeAdress, String phoneNumber, 
			String emailAddress, String index, int yearOfEnrollment, int currentYearOfStudy, int status) {
		
		Student student = DBStudent.getInstance().getSelectedStudent(selectedStudentIdx);
		
		student.setName(name);
		student.setSurname(surname);
		student.setDateOfBirth(date);
		student.getHomeAddress().setStreet(homeAdress);
		student.setPhoneNumber(phoneNumber);
		student.setEmailAddress(emailAddress);
		student.setIndexNum(index);
		student.setYearOfEnrollment(yearOfEnrollment);
		student.setCurrentYearOfStudy(currentYearOfStudy);
		student.setStatus(status);
		
	}
	
	public void deleteStudent(int deleteIndex) {
		students.remove(deleteIndex);
	}
	
	public Student getSelectedStudent(int row) {
		return students.get(row);
	}
	
}
