package model;

import java.util.ArrayList;
import java.util.List;

import controller.LanguageController;

public class DBTeaches {
	
	private static DBTeaches instance;
	
	public static DBTeaches getInstance() {
		if(instance == null) instance = new DBTeaches();
		
		return instance;
	}
	
	private ArrayList<Subject> subjectTeaches;
	private ArrayList<String> columns;
	
	public DBTeaches() {
		columns = new ArrayList<String>();
		columns.add(LanguageController.getInstance().getResourceBundle().getString("PassedSubjectId"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectName"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectYear"));
		columns.add(LanguageController.getInstance().getResourceBundle().getString("SubjectSemester"));
	}
	
	public void init(ArrayList<Subject> subjects) {
		subjectTeaches = subjects;
		
		//subjectTeaches.add(new Subject("testID", "Nuklearna Fizika", 9, "3", "ZIMSKI"));
	}
	
	public void del() {
		subjectTeaches.clear();
	}
	public int getRowCount() {
		return subjectTeaches.size();
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public String getColumnName(int column) {
		return columns.get(column);
	}
	
	public String getValueAt(int row, int column) {
		Subject sub = subjectTeaches.get(row);
		
		switch (column) {
		case 0:
			return sub.getSubjectID();
		case 1:
			return sub.getSubjectName();
		case 2:
			return sub.getYear();
		case 3:
			return sub.getCurrentSemester();
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + column);
		}
	}
	
	public void addSubjectTeach(List<String> selectedSubjects, int selectedStudent) {
		
		for(String s: selectedSubjects) {
			String[] parse = s.split(",");
			
			Subject subject = DBSubject.getInstance().getSelectedSubject(parse[0].replace("\\W", ""));
			if(subject != null) {
				DBProfessor.getInstance().getProfessor(selectedStudent).addSubject(subject);
			}
		
		}
	}
}
