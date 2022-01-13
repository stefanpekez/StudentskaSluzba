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
	
	private ArrayList<Grade> examsPassed;
	private ArrayList<String> columns;
	
	public DBExamsPassed() {
		columns = new ArrayList<String>();
		columns.add("SUBJECT ID");
		columns.add("NAME");
		columns.add("ESPB");
		columns.add("GRADE");
		columns.add("DATE");
		
		init();
		
	}
	
	private void init() {
		examsPassed = new ArrayList<>();
	}
	
	public void initPassedExams(ArrayList<Grade> grades) {
		/*examsPassed.add(new Grade(DBStudent.getInstance().getSelectedStudent(0), new Subject("MA", "Matematicka Analiza I", "1", "1", 9), 
				8, LocalDate.parse("2000-11-27")));*/
		
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
	/* 
	public String getAvgGrade(int index) {
		
		int selectedStudent = index;
		
		double sum = 0;
		
		for(Grade g: DBStudent.getInstance().getSelectedStudent(selectedStudent).getPassedExams())
			sum += g.getGradeValue();
		
		double avg = sum / examsPassed.size();
		
		if(DBStudent.getInstance().getSelectedStudent(selectedStudent).getPassedExams().size() == 0)
			return "0.00";
		
		return Double.toString(avg);
	}
	*/
}
