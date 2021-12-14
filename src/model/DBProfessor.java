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
		
		professors.add(new Professor("Ralevic", "Nebojsa", LocalDate.parse("1970-01-11"), new Address("nme",0,"",""), "21839264", "nema", new Address("nme",0,"",""), "217361287461", "Redovni profesor", 4));
		professors.add(new Professor("Rapajic", "Milos", LocalDate.parse("1970-01-11"), new Address("nme",0,"",""), "21839264", "nema", new Address("nme",0,"",""), "217361287461", "Redovni profesor", 4));
		
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
	
	public void editProfessor(int row, String surname, String name, LocalDate dateOfBirth, Address address, String phoneNumber, String emailAdress, 
								   Address officeAdress, String idNumber, String title, int workingYears) {
		Professor edit = professors.get(row);
		edit.setSurname(surname);
		edit.setName(name);
		edit.setDateOfBirth(dateOfBirth);
		edit.setHomeAdress(address);
		edit.setPhoneNumber(phoneNumber);
		edit.setEmailAdress(emailAdress);
		edit.setOfficeAdress(officeAdress);
		edit.setIdNumber(idNumber);
		edit.setTitle(title);
		edit.setWorkingYears(workingYears);
	}
	
	public void deleteProfessor(int row) {
		professors.remove(row);
	}
	
	public Professor getProfessor(int row) {
		return professors.get(row);
	}
}
