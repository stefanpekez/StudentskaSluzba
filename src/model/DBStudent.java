package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
		
		//students.add(new Student("Pekez", "Stefan", LocalDate.parse("2000-11-27"), new Address("nme","","",""), "0645871486", 
			//	"stefanpekez00@gmail.com", "ra-179-2019", 2019, 3, StudentStatus.B, 9.13));
		//students.add(new Student("Milosevic", "Filip", LocalDate.parse("2001-01-29"), new Address("nme","","",""), "00000000000", 
				//"milosevicfilip@gmail.com", "ra-193-2019", 2019, 3, StudentStatus.B, 10.0));
		try {
			students = convertExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		students.get(students.size() - 1).addRemainingExam(new Subject("39","Analiza","1","1",4));
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
		students.get(student).removeUnpassedExam(sub);;
		
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
    			} else if (rowNumber == 27) break;
    			
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
						stud.setHomeAddress(new Address("BB"));
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
	
}
