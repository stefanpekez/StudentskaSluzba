package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	
	public String[] getSubjectListForStudent(int selectedStudent){
		
		ArrayList<Subject> possibleUnpassedExams = subjects;
		ArrayList<Subject> remainingExams = DBStudent.getInstance().getSelectedStudent(selectedStudent).getRemainingExams();
		ArrayList<Grade> passedExamsGrade = DBStudent.getInstance().getSelectedStudent(selectedStudent).getPassedExams();
		ArrayList<Subject> passedExamsSubject = new ArrayList<Subject>();
		
		// TODO Hash seemingly doesn't do anything, should check
		HashMap<String, Subject> hashSubjects = new HashMap<String, Subject>();
		
		for(Grade g: passedExamsGrade)
			passedExamsSubject.add(g.getSubject());
		
		String[] subjectArray = new String[possibleUnpassedExams.size()-(remainingExams.size()+passedExamsSubject.size())];
		
		int i = 0;
		for(Subject s: possibleUnpassedExams) {
			if((!remainingExams.contains(s)) && (!passedExamsSubject.contains(s))) {
				hashSubjects.put(s.getSubjectID(), s);
				subjectArray[i] = s.toString();
				++i;
			}
		}
		
		return subjectArray;
	}
	
	public String[] getSubjectListForProfessor(int selectedProfessor){
		
		ArrayList<Subject> possibleTeachableSubjects = subjects;
		ArrayList<Subject> alreadyTeaching = DBProfessor.getInstance().getProfessor(selectedProfessor).getSubjects();
		
		String[] subjectArray = new String[possibleTeachableSubjects.size()-alreadyTeaching.size()];
		
		int i = 0;
		for(Subject s: possibleTeachableSubjects) {
			if(!alreadyTeaching.contains(s)) {
				subjectArray[i] = s.toString();
				++i;
			}
		}
		return subjectArray;
		
		}
		
	
	public void deleteUnpassedExam(int indexStudent, String indexSubject) {
		Subject subject = DBSubject.getInstance().getSelectedSubject(indexSubject);
		
		DBStudent.getInstance().getSelectedStudent(indexStudent).removeUnpassedExam(subject);
	}
	
	public void deleteTaughtSubject(int indexProfessor, String[] indexSubject) {
		
		for(String s: indexSubject) {
			Subject subject = DBSubject.getInstance().getSelectedSubject(s);
			DBProfessor.getInstance().getProfessor(indexProfessor).removeTaughtSubject(subject);
		}
		
	}
	
	public void deleteSubject(int row) {
		subjects.remove(row);
	}
	
	public Subject getSelectedSubject(int index) {
		return subjects.get(index);
	}
	
	public Subject getSelectedSubject(String id) {
		for(Subject s: subjects) {
			if(s.getSubjectID().equals(id))
				return s;
		}
		return null;
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
	
	public void serialize() throws IOException {
		File f = new File("saves\\subjects.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.addPermission(AnyTypePermission.ANY);
			String s = xs.toXML(subjects);
			xs.toXML(subjects, os);
		} finally {
			os.close();
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
						try {
							subj.setSubjectProfessor(DBProfessor.getInstance().getProfessor((int) currentCell.getNumericCellValue() - 1));
						} catch(IllegalStateException e) {
							subj.setSubjectProfessor(null);
						}
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
