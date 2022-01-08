package model;

import java.util.ArrayList;

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
		columns.add("SUBJECT ID");
		columns.add("NAME");
		columns.add("ESPB");
		columns.add("YEAR");
		columns.add("SEMESTER");
	}
	
	public void init(ArrayList<Subject> subjects) {
		exams = subjects;
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
}