package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DBProfessor {
	
	private static DBProfessor instance = null;
	
	public static DBProfessor getInstance() {
		if(instance == null) {
			instance = new DBProfessor();
		}
		return instance;
	}
	
	private ArrayList<Professor> professors;
	private ArrayList<String> columns;
	
	public DBProfessor() {
		columns = new ArrayList<String>();
		columns.add("NAME");
		columns.add("SURNAME");
		columns.add("TITLE");
		
		initProfessors();
	}
	
	private void initProfessors() {
		professors = new ArrayList<Professor>();
		
		professors.add(new Professor("Nebojsa", "Ralevic", "Redovni profesor"));
		professors.add(new Professor("Milos", "Rapajic", "GOAT"));
		
	}
	
	public int getRowCount() {
		return professors.size();
	}
	
	public int getColumnCount() {
		return 3;
	}
	
	public String getColumnName(int column) {
		return columns.get(column);
	}
	
	public String getValueAt(int row, int column) {
		Professor prof = professors.get(row);
		
		switch (column) {
		case 0:
			return prof.getName();
		case 1:
			return prof.getSurname();
		case 2:
			return prof.getTitle();
		default:
			throw new IllegalArgumentException("Unexpected value: " + column);
		}
	}
	
	public void addNewProfessor(String surname, String name, LocalDate dateOfBirth, Address homeAdress, String phoneNumber, 
								String emailAdress, Address officeAdress, String idNumber, String title, int workingYears) {
		
		professors.add(new Professor(surname, name, dateOfBirth,homeAdress, phoneNumber, emailAdress, officeAdress,idNumber, title, workingYears));
	}
	
	public void deleteProfessor(int row) {
		professors.remove(row);
	}
}
