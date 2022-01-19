package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import controller.AbstractTableModelStudent;
import controller.LanguageController;
import view.TabbedPane;
import view.TabbedPane.TablePanel;

public class DBStudent {
	
	private static DBStudent instance = null;
	
	public static DBStudent getInstance() {
		if(instance == null) {
			instance = new DBStudent();
		}
		return instance;
	}
	
	private ArrayList<Student> students;
	private ArrayList<Student> startingStudents;
	private ArrayList<String> columns;
	
	private DBStudent() {
		columns = new ArrayList<String>();
		columns.add(LanguageController.getInstance().getResourceBundle().getString("StudentIndex"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("StudentName"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("StudentSurname"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("StudentYear"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("StudentStatus"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("StudentAvg"));
		
		initStudents();
		
	}
	
	private void initStudents() {
		students = new ArrayList<Student>();
		
		//students.add(new Student("Pekez", "Stefan", LocalDate.parse("2000-11-27"), new Address("nme","","",""), "0645871486", 
			//	"stefanpekez00@gmail.com", "ra-179-2019", 2019, 3, StudentStatus.B, 9.13));
		//students.add(new Student("Milosevic", "Filip", LocalDate.parse("2001-01-29"), new Address("nme","","",""), "00000000000", 
				//"milosevicfilip@gmail.com", "ra-193-2019", 2019, 3, StudentStatus.B, 10.0));
		try {
			//students = deserialize();
			students = convertExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		startingStudents = students;
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
	
	public void removeUnpassedExam(int student, Subject sub) {
		students.get(student).removeUnpassedExam(sub);
		
	}
	
	public void removePassedExam(Student student, Grade grade) {
		for(Student s: students) {
			if(s.equals(student)) s.removePassedExam(grade);
		}
	}
	
	public void addGrade(Grade g, int student) {
		students.get(student).addGrade(g);
	}
	
	public void addUnpassedExam(Student student, Subject subject) {
		for(Student s: students) {
			if(s.equals(student)) s.addUnpassedexam(subject);;
		}
	}
	
	public void searchOneWord(String search) {
		ArrayList<Student> foundStudents = new ArrayList<Student>();
		
		if(search.isEmpty()) {
			students = startingStudents;
			return;
		}
		
		for(Student s: students)
				if(s.getSurname().toLowerCase().contains(search.toLowerCase()))
					foundStudents.add(s);
		
		students = foundStudents;
	}
	
	public void searchTwoWords(String search) {
		ArrayList<Student> foundStudents = new ArrayList<Student>();
		
		if(search.isEmpty()) {
			students = startingStudents;
			return;
		}
		
		String[] searchParts = search.split(",");
		
		for(Student s: students)
				if(s.getSurname().toLowerCase().contains(searchParts[0].toLowerCase().replaceAll("\\W", "")) && 
						s.getName().toLowerCase().contains(searchParts[1].toLowerCase().replaceAll("\\W", "")))
					foundStudents.add(s);
		
		students = foundStudents;
	}
	
	public void searchThreeWords(String search) {
		ArrayList<Student> foundStudents = new ArrayList<Student>();
		
		if(search.isEmpty()) {
			students = startingStudents;
			return;
		}
		
		String[] searchParts = search.split(",");
		
		for(Student s: students)
				if(s.getIndexNum().toLowerCase().contains(searchParts[0].toLowerCase()) && 
						s.getName().toLowerCase().contains(searchParts[1].toLowerCase().replaceAll("\\W", "")) &&
						s.getSurname().toLowerCase().contains(searchParts[2].toLowerCase().replaceAll("\\W", "")))
					foundStudents.add(s);
		
		students = foundStudents;
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\students.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.addPermission(AnyTypePermission.ANY);
			String s = xs.toXML(students);
			xs.toXML(students, os);
		} finally {
			os.close();
		}
	}
  
	public ArrayList<Student> deserialize() throws IOException {
		FileInputStream f = new FileInputStream("saves\\students.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			students = (ArrayList<Student>) xstream.fromXML(f);
			return students;
			}
		finally {
		}
	}
	
	public void initComponents(TablePanel tp) {
		for(int i = 0; i < columns.size(); ++i) {
			switch(i) {
				case 0:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("StudentIndex"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 1:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("StudentName"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 2:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("StudentSurname"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 3:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("StudentYear"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 4:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("StudentStatus"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 5:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("StudentAvg"));
					tp.getTable().getTableHeader().repaint();
					break;
				default:
					break;
			}
		}
	}
	
	private ArrayList<Student> convertExcel() throws IOException{
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Studenti");
			Iterator<Row> rows = sheet.iterator();
			
			ArrayList<Student> list = new ArrayList<Student>();
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 28) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			Student stud = new Student();
    			
    			int cellIndex = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 1:
						stud.setIndexNum(currentCell.getStringCellValue());
						break;
					case 2:
						stud.setName(currentCell.getStringCellValue());
						break;
					case 3:
						stud.setSurname(currentCell.getStringCellValue());
						break;
					case 4:
						stud.setCurrentYearOfStudy((int) currentCell.getNumericCellValue());
						break;
					case 5:
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						stud.setDateOfBirth(LocalDate.parse(currentCell.getStringCellValue(), formatter));
						break;
					case 6:
						try {
							stud.setHomeAddress(DBAddress.getInstance().getAddress((int)currentCell.getNumericCellValue()));
						} catch(IllegalStateException e) {
							stud.setHomeAddress(DBAddress.getInstance().getAddress(-1));
						}
						break;
					case 7:
						stud.setPhoneNumber(currentCell.getStringCellValue());
						break;
					case 8:
						stud.setEmailAddress(currentCell.getStringCellValue());
						break;
					case 9:
						stud.setStatus(currentCell.getStringCellValue().equals("B") ? 0 : 1 );
						break;
					case 10:
						stud.setYearOfEnrollment((int) currentCell.getNumericCellValue());
						break;
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			list.add(stud);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
	
	public void setupUnpassed() throws IOException {
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Nepoloženi predmeti");
			Iterator<Row> rows = sheet.iterator();
			
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 13) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			int cellIndex = 0;
    			
    			int studentIndex = 0;
    			int subjectIndex = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 0:
						studentIndex = (int) currentCell.getNumericCellValue() - 1;
						break;
					case 1:
						subjectIndex = (int) currentCell.getNumericCellValue() - 1;
						break;
					default:
						break;
					}
    				System.out.println(String.format("%d -> %d", studentIndex, subjectIndex));
    				cellIndex++;
    			}
    			
    			addUnpassedExam(getSelectedStudent(studentIndex), DBSubject.getInstance().getSelectedSubject(subjectIndex));
    			
    			rowNumber++;
    		}
    		
    		workbook.close();
    		
		} finally {
			
		}
	}
	
	public void setupPassed() throws IOException{
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Ocene");
			Iterator<Row> rows = sheet.iterator();
			
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 13) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			int cellIndex = 0;
    			
    			Grade grade = new Grade();
    			int student = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 0:
						student = (int) currentCell.getNumericCellValue() - 1;
						grade.setStudent(getSelectedStudent(student));
						break;
					case 1:
						grade.setSubject(DBSubject.getInstance().getSelectedSubject((int) currentCell.getNumericCellValue() - 1));
						break;
					case 2:
						grade.setGradeValue((int)currentCell.getNumericCellValue());
						break;
					case 3:
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						grade.setDateOfPassingExam(LocalDate.parse(currentCell.getStringCellValue(), formatter));
						break;
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			addGrade(grade, student);
    			
    			rowNumber++;
    		}
    		
    		workbook.close();
    		
		} finally {
			
		}
	}
	
}
