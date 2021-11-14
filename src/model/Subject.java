package model;

import java.util.ArrayList;

public class Subject {
	
	private String subjectID;
	private String subjectName;
	private String currentSemester;
	private String year;
	private Profesor subjectProfessor;
	private int ESPB;
	//ArrayList<Student> studentsPASSED;
	//ArrayList<Student> studentsFAILED;
	
	public Subject(String subjectID, String subjectName, String currentSemester, String year, Profesor subjectProfessor,
			int eSPB) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.currentSemester = currentSemester;
		this.year = year;
		this.subjectProfessor = subjectProfessor;
		ESPB = eSPB;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(String currentSemester) {
		this.currentSemester = currentSemester;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Profesor getSubjectProfessor() {
		return subjectProfessor;
	}

	public void setSubjectProfessor(Profesor subjectProfessor) {
		this.subjectProfessor = subjectProfessor;
	}

	public int getESPB() {
		return ESPB;
	}

	public void setESPB(int eSPB) {
		ESPB = eSPB;
	}
	
	
}
