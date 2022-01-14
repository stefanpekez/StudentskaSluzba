package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DBExamsPassed {
	
private static DBExamsPassed instance = null;
	
	public static DBExamsPassed getInstance() {
		if(instance == null) {
			instance = new DBExamsPassed();
		}
		return instance;
	}
	
	private ArrayList<Grade> examsPassed = new ArrayList<Grade>();
	private ArrayList<String> columns;
	private double avg = 0.0;
	private int espb = 0;
	
	public DBExamsPassed() {
		columns = new ArrayList<String>();
		columns.add("SUBJECT ID");
		columns.add("NAME");
		columns.add("ESPB");
		columns.add("GRADE");
		columns.add("DATE");
		
	}
	
	public void init(ArrayList<Grade> grades) {
		examsPassed = grades;
		
	}
	
	public int getRowCount() {
		return examsPassed.size();
	}
	
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int column) {
		return columns.get(column);
	}
	
	public ArrayList<Grade> getPassedExams() {
		return examsPassed;
	}
	
	public String getValueAt(int row, int column) {
		Grade passed = examsPassed.get(row);
		
		switch (column) {
		case 0:
			return passed.getSubject().getSubjectID();
		case 1:
			return passed.getSubject().getSubjectName();
		case 2:
			return Integer.toString(passed.getSubject().getESPB());
		case 3:
			return Integer.toString(passed.getGradeValue());
		case 4:
			return passed.getDateOfPassingExam().toString();
		default:
			throw new IllegalArgumentException("Unexpected value: " + column);
		}
	
	}
	
	// TODO treba smisliti nacin da se update prosecna ocena nakon dodavanja entiteta u tabelu polozenih predmeta
	
	public double getAvgGrade(int selectedStudent) {
		
		double sum = 0;
		
		for(Grade g: examsPassed)
			sum += g.getGradeValue();
		
		this.avg = sum / examsPassed.size();
		
		if(examsPassed.size() == 0)
			return 0.0;
		
		DBStudent.getInstance().getSelectedStudent(selectedStudent).setAvgGrade(this.avg);
		
		return this.avg;
	}
	
	public int getESPB() {
		
		int espb = 0;
		
		for(Grade g: examsPassed)
			espb += g.getSubject().getESPB();
		
		return espb;
	}
	
}
