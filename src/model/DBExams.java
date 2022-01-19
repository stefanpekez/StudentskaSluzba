package model;

import java.util.ArrayList;

import controller.LanguageController;

public class DBExams {
	private static DBExams instance;
	
	public static DBExams getInstance() {
		if(instance == null) instance = new DBExams();
		
		return instance;
	}
	
	private ArrayList<Subject> exams;
	private ArrayList<String> columns;
	
	public DBExams() {
		columns = new ArrayList<String>();
		columns.add(LanguageController.getInstance().getResourceBundle().getString("PassedSubjectId"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectName"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectEspb"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectYear"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectSemester"));
	}
	
	public void init(ArrayList<Subject> subjects) {
		exams = subjects;
	}
	
	public void del() {
		exams.clear();
	}
	public int getRowCount() {
		return exams.size();
	}
	
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int column) {
		return columns.get(column);
	}
	
	public Subject getSelectedExam(int exam) {
		return exams.get(exam);
	}
	
	public void removeExam(int index) {
		exams.remove(index);
	}
	public String getValueAt(int row, int column) {
		Subject sub = exams.get(row);
		
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
	
	public void addUnpassedExam(String selectedSubject, int selectedStudent) {
		String[] parse = selectedSubject.split(",");
		
		Subject subject = DBSubject.getInstance().getSelectedSubject(parse[0].replace("\\W", ""));
		if(subject != null) {
			DBStudent.getInstance().getSelectedStudent(selectedStudent).addRemainingExam(subject);
			//exams.add(subject);
		}
	}
	
	public ArrayList<Subject> getExamsRemaining() {
		return exams;
	}
}
