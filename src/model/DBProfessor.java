package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import controller.LanguageController;
import view.TabbedPane.TablePanel;

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
	
	private ArrayList<Professor> originalProfessors;
	
	public DBProfessor() {
		columns = new ArrayList<String>();
		columns.add(LanguageController.getInstance().getResourceBundle().getString("ProfessorName"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("ProfessorSurname"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("ProfessorTitle"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("ProfessorEmail"));

		initProfessors();
	}
	
	private void initProfessors() {
		professors = new ArrayList<Professor>();
		originalProfessors = new ArrayList<Professor>();
		
		//professors.add(new Professor("Ralevic", "Nebojsa", LocalDate.parse("1970-01-11"), new Address("BB","","",""), "21839264", "rale@uns.ac.rs", new Address("BB","","",""), "01A", "Redovni Profesor", 4));
		//professors.add(new Professor("Rapajic", "Milos", LocalDate.parse("1970-01-11"), new Address("BB","","",""), "21839264", "rapa@uns.ac.rs", new Address("BB","","",""), "02B", "Redovni Profesor", 4));
		
		try {
			professors = deserialize();
			//professors = convertExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		originalProfessors = professors;
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
		
		for(Professor s: originalProfessors) {
			if(s.getIdNumber().equals(idNumber)) return false;
		}
		
		originalProfessors.add(new Professor(surname, name, dateOfBirth,homeAdress, phoneNumber, emailAdress, officeAdress,idNumber, title, workingYears));
		
		professors = originalProfessors;
		
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
		Professor p = professors.get(row);
		
		for(Subject s: DBSubject.getInstance().getAllSubjects()) {
			if(s.getSubjectProfessor() == null) continue;
			
			if(s.getSubjectProfessor().equals(p)) {
				s.setSubjectProfessor(null);
			}
		}
		
		for(Department d: DBDepartments.getInstance().getAllDepartments()) {
			if(d.getDepartmentHead() != null) {
				if(d.getDepartmentHead().equals(p)) {
					d.setDepartmentHead(null);
					d.getProfessors().remove(p);
				} 
			}
			
			
			if(d.getProfessors().contains(p)) {
				d.getProfessors().remove(p);
			}
			
		}
		
		professors.remove(row);
	}
	
	public ArrayList<Professor> getProfessors() {
		return originalProfessors;
	}
	
	public void professorSearchOne(String query) {
		ArrayList<Professor> searchProfessors = new ArrayList<Professor>();
		
		if(query.isEmpty()) {
			professors = originalProfessors;
			return;
		}
		
		for(Professor s: professors) {
			if(s.getSurname().contains(query)) {
				searchProfessors.add(s);
			}
		}
		
		professors = searchProfessors;
	}
	
	public void professorSearchTwo(String queryOne, String queryTwo) {
		ArrayList<Professor> searchProfessors = new ArrayList<Professor>();
		
		if(queryOne.isBlank() && queryTwo.isBlank()) {
			professors = originalProfessors;
			return;
		}
		
		for(Professor s: professors) {
			if(s.getSurname().contains(queryOne) && s.getName().contains(queryTwo)) {
				searchProfessors.add(s);
			}
		}
		
		professors = searchProfessors;
	}
	
	public Professor getProfessor(int row) {
		return professors.get(row);
	}
	
	public Professor getProfessor(String index) {
		for(Professor p: professors) {
			if(p.getIdNumber().equals(index))
				return p;
		}
		return null;
	}
	
	public String[] getProfessorsOverWorkingYearLimit(int departmentId) {
		ArrayList<Professor> acceptableProfessors = new ArrayList<Professor>();
		Department dep = DBDepartments.getInstance().getSelectedDepartment(departmentId);
		
		
		for(Professor p: professors)
			if(p.getWorkingYears() >= 5 && dep.getProfessors().contains(p)) {
				acceptableProfessors.add(p);
				System.out.println(Integer.toString(p.getDepartmentID()));
			}
				
		
		String[] professorList = new String[acceptableProfessors.size()];
		
		int i = 0;
		for(Professor prof: acceptableProfessors) {
			professorList[i] = acceptableProfessors.get(i).toString();
			++i;
		}
		
		return professorList;
	}
	
	public String[] getProfessorDepartmentList() {
		ArrayList<Professor> profs = new ArrayList<Professor>();
		
		for(Professor p: professors) {
			if(p.getDepartmentID() == -1) {
				profs.add(p);
			}
		}
		
		String[] list = new String[profs.size()];
		
		int i = 0;
		for(Professor p: profs) {
			list[i] = p.getIdNumber() + "," + p.getName() + p.getSurname();
			++i;
		}
		
		return list;
	}
		
	public String[] getProfessorList() {
		String[] list = new String[professors.size()];
		
		for(int i = 0; i < professors.size(); ++i) {
			Professor p = professors.get(i);
			list[i] = p.getName() + " " + p.getSurname();
			System.out.println(list[i]);
		}
		
		return list;
	}
	
	public void setupTaughtSubjects(int row) {
		
		ArrayList<Subject> subjects  = getProfessor(row).getSubjects();
		
		DBTeaches.getInstance().init(subjects);
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\professors.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.addPermission(AnyTypePermission.ANY);
			String s = xs.toXML(professors);
			xs.toXML(professors, os);
		} finally {
			os.close();
		}
	}
	
	public ArrayList<Professor> deserialize() throws IOException {
		FileInputStream f = new FileInputStream("saves\\professors.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			professors = (ArrayList<Professor>) xstream.fromXML(f);
			return professors;
			
			}
		finally {
		}
	}
	
	public void initComponents(TablePanel tp) {
		for(int i = 0; i < columns.size(); ++i) {
			switch(i) {
				case 0:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("ProfessorName"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 1:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("ProfessorSurname"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 2:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("ProfessorTitle"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 3:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("ProfessorEmail"));
					tp.getTable().getTableHeader().repaint();
					break;
				default:
					break;
			}
		}
	}
	
	private ArrayList<Professor> convertExcel() throws IOException{
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Profesori");
			Iterator<Row> rows = sheet.iterator();
			
			ArrayList<Professor> list = new ArrayList<Professor>();
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 20) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			Professor profa = new Professor();
    			
    			int cellIndex = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 1:
						profa.setIdNumber(Integer.toString((int)currentCell.getNumericCellValue()));
						break;
					case 2:
						profa.setName(currentCell.getStringCellValue());
						break;
					case 3:
						profa.setSurname(currentCell.getStringCellValue());
						break;
					case 4:
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						profa.setDateOfBirth(LocalDate.parse(currentCell.getStringCellValue(), formatter));
						break;
					case 5:
						profa.setHomeAdress(DBAddress.getInstance().getAddress((int)currentCell.getNumericCellValue()));
						break;
					case 6:
						profa.setPhoneNumber(currentCell.getStringCellValue());
						break;
					case 7:
						profa.setEmailAdress(currentCell.getStringCellValue());
						break;
					case 8:
						profa.setOfficeAdress(DBAddress.getInstance().getAddress((int)currentCell.getNumericCellValue()));
						break;
					case 9:
						profa.setWorkingYears((int)currentCell.getNumericCellValue());
						break;
					case 10:
						profa.setTitle(currentCell.getStringCellValue());
						break;
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			list.add(profa);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
}
