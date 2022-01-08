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
		columns.add("EMAIL");

		initProfessors();
	}
	
	private void initProfessors() {
		professors = new ArrayList<Professor>();
		
		professors.add(new Professor("Ralevic", "Nebojsa", LocalDate.parse("1970-01-11"), new Address("BB","","",""), "21839264", "rale@uns.ac.rs", new Address("BB","","",""), "01A", "Redovni Profesor", 4));
		professors.add(new Professor("Rapajic", "Milos", LocalDate.parse("1970-01-11"), new Address("BB","","",""), "21839264", "rapa@uns.ac.rs", new Address("BB","","",""), "02B", "Redovni Profesor", 4));
		
	}
	
	public int getRowCount() {
		return professors.size();
	}
	
	public int getColumnCount() {
		return 4;
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
		case 3:
			return prof.getEmailAdress();
		default:
			throw new IllegalArgumentException("Unexpected value: " + column);
		}
	}
	
	public boolean addNewProfessor(String surname, String name, LocalDate dateOfBirth, Address homeAdress, String phoneNumber, 
								String emailAdress, Address officeAdress, String idNumber, String title, int workingYears) {
		
		for(Professor s: professors) {
			if(s.getIdNumber().equals(idNumber)) return false;
		}
		
		professors.add(new Professor(surname, name, dateOfBirth,homeAdress, phoneNumber, emailAdress, officeAdress,idNumber, title, workingYears));
		
		return true;
	}
	
	public boolean editProfessor(int row, String surname, String name, LocalDate dateOfBirth, Address address, String phoneNumber, String emailAdress, 
								   Address officeAdress, String idNumber, String title, int workingYears) {
		Professor edit = professors.get(row);
		for(Professor s: professors) {
			if(s != edit && s.getIdNumber().equals(idNumber)) return false;
		}
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
		
		return true;
	}
	
	public void deleteProfessor(int row) {
		professors.remove(row);
	}
	
	public Professor getProfessor(int row) {
		return professors.get(row);
	}
}
