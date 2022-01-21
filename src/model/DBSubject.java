package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	private static int generatedId = 0;
	
	public int generateNextID() {
		return generatedId++;
	}
	
	private DBSubject() {
		columns = new ArrayList<String>();
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectId"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectName"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectEspb"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectYear"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectSemester"));
		
		initSubjects();
		
	}
	
	private void initSubjects() {
		subjects = new ArrayList<Subject>();
		originalSubjects = new ArrayList<Subject>();
		
		//subjects.add(new Subject("MA", "Matematicka Analiza I", "1", "1", 9));
		//subjects.add(new Subject("AR", "Arhitektura Racunara", "2", "1", 9));
		try {
			subjects = deserialize();
			//subjects = convertExcel();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		originalSubjects = subjects;
	}
	
	public Subject findByPrimaryId(int id) {
		for(Subject s: subjects) {
			if(s.getPrimaryId() == id) return s;
		}
		
		return null;
	}
	
	public ArrayList<Subject> getAllSubjects(){
		return originalSubjects;
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

		originalSubjects.add(new Subject(subjectID, subjectName, espb, year, semester));
		originalSubjects.get(originalSubjects.size() - 1).setPrimaryId(generateNextID());
		
		subjects = originalSubjects;
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
		
		ArrayList<Subject> possibleUnpassedExams = new ArrayList<Subject>();
		int currentYearOfStudent = DBStudent.getInstance().getSelectedStudent(selectedStudent).getCurrentYearOfStudy();
		
		for(Subject s: subjects)
			if(Integer.parseInt(s.getYear()) <= currentYearOfStudent)
				possibleUnpassedExams.add(s);
		
		ArrayList<Subject> remainingExams = DBStudent.getInstance().getSelectedStudent(selectedStudent).getRemainingExams();
		ArrayList<Grade> passedExamsGrade = DBStudent.getInstance().getSelectedStudent(selectedStudent).getPassedExams();
		ArrayList<Subject> passedExamsSubject = new ArrayList<Subject>();
		ArrayList<Subject> subjectsFinal = new ArrayList<Subject>();
		
		for(Grade g: passedExamsGrade)
			passedExamsSubject.add(g.getSubject());
		
		for(Subject s: possibleUnpassedExams)
			if((!remainingExams.contains(s)) && (!passedExamsSubject.contains(s)))
				subjectsFinal.add(s);
		
		String[] subjectArray = new String[subjectsFinal.size()];
		
		int i = 0;
		for(Subject s: subjectsFinal) {
			subjectArray[i] = s.toString();
			++i;
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
		Subject s = subjects.remove(row);
		
		for(Student stud: DBStudent.getInstance().getStudents()) {
			for(Grade g: stud.getPassedExams()) {
				if(g.getSubject().getSubjectID().equals(s.getSubjectID())) {
					stud.getPassedExams().remove(g);
					break;
				}
			}
			
			for(Subject sub: stud.getRemainingExams()) {
				if(sub.getSubjectID().equals(s.getSubjectID())) {
					stud.getRemainingExams().remove(sub);
					break;
				}
			}
		}
		
		for(Professor p: DBProfessor.getInstance().getProfessors()) {
			for(Subject sub: p.getSubjects()) {
				if(sub.getSubjectID() == s.getSubjectID()) {
					p.getSubjects().remove(sub);
					break;
				}
			}
		}
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
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);
			
			makeHeadSerialize();
			
			String s = xs.toXML(subjects);
			xs.toXML(subjects, os);
		} finally {
			os.close();
		}
	}
	
	private void makeHeadSerialize() {
		for(Subject s: subjects) {
			if(s.getSubjectProfessor() != null) {
				SubjectProfessorSerialization.getInstance().addHead(new SubjectHeadRelation(s.getPrimaryId(), s.getSubjectProfessor().getPrimaryId()));
				s.setSubjectProfessor(null);
			}
			s.getStudentsPASSED().clear();
			s.getStudentsFAILED().clear();
		}
	}
	
	public ArrayList<Subject> deserialize() throws IOException {
		FileInputStream f = new FileInputStream("saves\\subjects.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			subjects = (ArrayList<Subject>) xstream.fromXML(f);
			
			setupHeadsAndTeachers();
			
			return subjects;
			
			}
		finally {
			f.close();
		}
	}
	
	private void setupHeadsAndTeachers() {
		for(SubjectHeadRelation shrel: SubjectProfessorSerialization.getInstance().getHeadRelations()) {
			findByPrimaryId(shrel.getSubjectId()).setSubjectProfessor(DBProfessor.getInstance().findByPrimaryId(shrel.getProfessorId()));
		}
		
		SubjectProfessorSerialization.getInstance().flushHead();
		
		for(SubjectTeachersRelation strel: SubjectProfessorSerialization.getInstance().getTeachersRelation()) {
			DBProfessor.getInstance().findByPrimaryId(strel.getTeachersId()).addSubject(findByPrimaryId(strel.getSubjectId()));
		}
		
		SubjectProfessorSerialization.getInstance().flushTeachers();
	}
	
	
	public void initComponents(TablePanel tp) {
		for(int i = 0; i < columns.size(); ++i) {
			switch(i) {
				case 0:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("SubjectId"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 1:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("SubjectName"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 2:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("SubjectEspb"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 3:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("SubjectYear"));
					tp.getTable().getTableHeader().repaint();
					break;
				case 4:
					tp.getTable().getColumnModel().getColumn(i).setHeaderValue(LanguageController.getInstance().getResourceBundle().getString("SubjectSemester"));
					tp.getTable().getTableHeader().repaint();
					break;
				default:
					break;
			}
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
    			} else if (rowNumber == 31) break;
    			
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
							Professor prof = DBProfessor.getInstance().getProfessor((int) currentCell.getNumericCellValue() - 1);
							subj.setSubjectProfessor(prof);
							prof.addSubject(subj);
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
    			subj.setPrimaryId(generateNextID());
    			
    			list.add(subj);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
}
