package model;

import java.util.ArrayList;

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
		
		subjects.add(new Subject("MA", "Matematicka Analiza I", "1", "1", 9));
		subjects.add(new Subject("AR", "Arhitektura Racunara", "2", "1", 9));
		
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
}
