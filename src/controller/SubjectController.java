package controller;

import java.util.ArrayList;

import model.DBExams;
import model.DBSubject;
import model.Subject;
import view.dialogue.NewCB;
import view.dialogue.NewTF;
import view.dialogue.edit.EditCB;
import view.dialogue.edit.EditTF;

public class SubjectController {
	
	private static SubjectController instance = null;
	
	public static SubjectController getInstance() {
		if(instance == null) {
			instance = new SubjectController();
		}
		
		return instance;
	}
	
	public void deleteSubject(int row) {
		DBSubject.getInstance().deleteSubject(row);
	}
	
	public void searchSubject(String query) {
		String[] parsedQuery = query.split(",");
		
		switch (parsedQuery.length) {
		case 1:
			DBSubject.getInstance().subjectSearchOne(parsedQuery[0].replaceAll("\\W", ""));
			break;
		case 2:
			DBSubject.getInstance().subjectSearchTwo(parsedQuery[0].replaceAll("\\W", ""), parsedQuery[1].replaceAll("\\W", ""));
			break;
		default:
			System.out.println("Wrong query format");
			break;
		}
	}
	
	public void addNewSubject(NewTF idPanel, NewTF subjectNamePanel, NewTF espbPanel, NewCB yearPanel, NewCB currentSemesterPanel) {
		
		// TODO regex parsing
		
		String subjectID = idPanel.getTextField().getText();
		
		String subjectName = subjectNamePanel.getTextField().getText();
		
		int espb = Integer.parseInt(espbPanel.getTextField().getText());
		
		String year = Integer.toString(1 + yearPanel.getComboBox().getSelectedIndex());
		
		String semester = null;
		if(currentSemesterPanel.getComboBox().getSelectedIndex() == 0)
			semester = "ZIMSKI";
		else
			semester = "LETNJI";
		
		DBSubject.getInstance().addNewSubject(subjectID, subjectName, espb, year, semester);
	}
	
	public void editSubject(int selectedSubject, EditTF idPanel, EditTF subjectNamePanel, EditTF espbPanel, EditCB yearPanel, EditCB currentSemesterPanel) {
		
		int selectedSubjectIdx = selectedSubject;
		
		String subjectID = idPanel.getTextField().getText();
		
		String subjectName = subjectNamePanel.getTextField().getText();
		
		int espb = Integer.parseInt(espbPanel.getTextField().getText());
		
		String year = Integer.toString(1 + yearPanel.getComboBox().getSelectedIndex());
		
		String semester = null;
		if(currentSemesterPanel.getComboBox().getSelectedIndex() == 0)
			semester = "ZIMSKI";
		else
			semester = "LETNJI";
		
		DBSubject.getInstance().editSubject(selectedSubjectIdx, subjectID, subjectName, espb, year, semester);
	}
	
	public void editSubjectProfessor(int subject, int professor) {
		DBSubject.getInstance().editSubjectProfessor(subject, professor);
	}
	
	public String getSubjectProfessor(int index) {
		return DBSubject.getInstance().getSelectedSubjectProfessor(index);
	}
	
	public String getID(int row){
		return DBSubject.getInstance().getSelectedSubject(row).getSubjectID();
	}
	
	public String getName(int row){
		return DBSubject.getInstance().getSelectedSubject(row).getSubjectName();
	}
	
	public String getESPB(int row){
		return Integer.toString(DBSubject.getInstance().getSelectedSubject(row).getESPB());
	}
	
	public String getYear(int row){
		return DBSubject.getInstance().getSelectedSubject(row).getYear();
	}
	
	public String getCurrentSemester(int row) {
		return DBSubject.getInstance().getSelectedSubject(row).getCurrentSemester();
	}
	
	public void addUnpassedExam(String selectedSubject, int selectedStudent) {
		DBExams.getInstance().addUnpassedExam(selectedSubject, selectedStudent);
	}
	
	public String[] getSubjectListForStudent(int selectedStudent) {
		return DBSubject.getInstance().getSubjectListForStudent(selectedStudent);
	}
	
	public String[] getSubjectListForProfessor(int selectedProfessor) {
		return DBSubject.getInstance().getSubjectListForProfessor(selectedProfessor);
	}
	
	public void deleteUnpassedExam(int indexStudent, String indexSubject) {
		DBSubject.getInstance().deleteUnpassedExam(indexStudent, indexSubject);
	}
	
	public void deleteTaughtSubject(int indexProfessor, String[] indexSubject) {
		DBSubject.getInstance().deleteTaughtSubject(indexProfessor, indexSubject);
	}
	
	public ArrayList<Subject> getExamsRemaining() {
		return DBExams.getInstance().getExamsRemaining();
	}
}
