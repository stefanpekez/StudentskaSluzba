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

public class DBSubject {
	
	private static DBSubject instance = null;
	
	public static DBSubject getInstance() {
		if(instance == null) {
			instance = new DBSubject();
		}
		return instance;
	}
	
	private ArrayList<Subject> subjects;
	private ArrayList<String> columns;
	
	private ArrayList<Subject> originalSubjects;
	
	private DBSubject() {
		columns = new ArrayList<String>();
		columns.add("ID");
		columns.add("NAME");
		columns.add("ESPB");
		columns.add("YEAR");
		columns.add("SEMESTER");
		
		initSubjects();
		
	}
	
	private void initSubjects() {
		subjects = new ArrayList<Subject>();
		originalSubjects = new ArrayList<Subject>();
		
		//subjects.add(new Subject("MA", "Matematicka Analiza I", "1", "1", 9));
		//subjects.add(new Subject("AR", "Arhitektura Racunara", "2", "1", 9));
		try {
			subjects = convertExcel();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		originalSubjects = subjects;
	}
	
	public int getRowCount() {
		return subjects.size();
	}
	
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int column) {
		return columns.get(column);
	}
	
	public String getValueAt(int row, int column) {
		Subject sub = subjects.get(row);
		
		switch (column) {
		case 0:
			return sub.getSubjectID();
		case 1:
			return sub.getSubjectName();
		case 2:
			return Integer.toString(sub.getESPB());
		case 3:
			return sub.getYear();
		case 4:
			return sub.getCurrentSemester();
		default:
			throw new IllegalArgumentException("Unexpected value: " + column);
		}
	}
	
	public void addNewSubject(String subjectID, String subjectName, int espb, String year, String semester) {

		subjects.add(new Subject(subjectID, subjectName, espb, year, semester));
	}
	
	public void editSubject(int selectedSubject, String subjectID, String subjectName, int espb, String year, String semester) {
		Subject subject = getSelectedSubject(selectedSubject);
		
		subject.setSubjectID(subjectID);
		subject.setSubjectName(subjectName);
		subject.setESPB(espb);
		subject.setYear(year);
		subject.setCurrentSemester(semester);
	}
	
	public void subjectSearchOne(String query) {
		ArrayList<Subject> searchSubjects = new ArrayList<Subject>();
		
		if(query.isEmpty()) {
			subjects = originalSubjects;
			return;
		}
		
		for(Subject s: subjects) {
			if(s.getSubjectName().contains(query)) {
				searchSubjects.add(s);
			}
		}
		
		subjects = searchSubjects;
	}
	
	public void subjectSearchTwo(String queryOne, String queryTwo) {
		ArrayList<Subject> searchSubjects = new ArrayList<Subject>();
		
		if(queryOne.isBlank() && queryTwo.isBlank()) {
			subjects = originalSubjects;
			return;
		}
		
		for(Subject s: subjects) {
			if(s.getSubjectName().contains(queryOne) && s.getSubjectID().contains(queryTwo)) {
				searchSubjects.add(s);
			}
		}
		
		subjects = searchSubjects;
	}
	
	public void deleteSubject(int row) {
		subjects.remove(row);
	}
	
	public Subject getSelectedSubject(int index) {
		return subjects.get(index);
	}
	
	public String getSelectedSubjectProfessor(int index) {
		Professor prof = subjects.get(index).getSubjectProfessor();
		if(prof != null) return prof.getName() + " " + prof.getSurname();
		
		return "";
	}
	
	public void editSubjectProfessor(int subject, int professor) {
		if(professor == -1) {
			subjects.get(subject).setSubjectProfessor(null);
		} else {
			subjects.get(subject).setSubjectProfessor(DBProfessor.getInstance().getProfessor(professor));
		}
	}
	
	private ArrayList<Subject> convertExcel() throws IOException{
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Predmeti");
			Iterator<Row> rows = sheet.iterator();
			
			ArrayList<Subject> list = new ArrayList<Subject>();
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 30) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			Subject subj = new Subject();
    			
    			int cellIndex = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 1:
						subj.setSubjectID(currentCell.getStringCellValue());
						break;
					case 2:
						subj.setSubjectName(currentCell.getStringCellValue());
						break;
					case 3:
						subj.setYear(Integer.toString((int)currentCell.getNumericCellValue()));
						break;
					case 4:
						subj.setESPB((int) currentCell.getNumericCellValue());
						break;
					case 5:
						//
						break;
					case 6:
						subj.setCurrentSemester(currentCell.getStringCellValue());
						break;
					
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			list.add(subj);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
}
