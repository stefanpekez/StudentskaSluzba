package model;

import java.util.ArrayList;

public class Subject {
	
	private int primaryId;
	
	private String subjectID;
	private String subjectName;
	private String currentSemester;
	private String year;
	private Professor subjectProfessor;
	private int ESPB;
	private ArrayList<Student> studentsPASSED;
	private ArrayList<Student> studentsFAILED;

	public Subject(String subjectID, String subjectName, String currentSemester, String year,
			/*Professor subjectProfessor,*/ int eSPB) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.currentSemester = currentSemester;
		this.year = year;
		//this.subjectProfessor = subjectProfessor;
		ESPB = eSPB;
		this.studentsPASSED = new ArrayList<Student>();
		this.studentsFAILED = new ArrayList<Student>();
	}
	
	public Subject(String subjectID, String subjectName, int ESPB, String year, String currentSemester) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.ESPB = ESPB;
		this.year = year;
		this.currentSemester = currentSemester;
	}
	
	public Subject() {
		this.studentsPASSED = new ArrayList<Student>();
		this.studentsFAILED = new ArrayList<Student>();
	}
	
	

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
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

	public Professor getSubjectProfessor() {
		return subjectProfessor;
	}

	public void setSubjectProfessor(Professor subjectProfessor) {
		this.subjectProfessor = subjectProfessor;
	}

	public int getESPB() {
		return ESPB;
	}

	public void setESPB(int eSPB) {
		ESPB = eSPB;
	}

	public ArrayList<Student> getStudentsPASSED() {
		return studentsPASSED;
	}

	public void setStudentsPASSED(ArrayList<Student> studentsPASSED) {
		this.studentsPASSED = studentsPASSED;
	}
	
	public void addPassedStudent(Student student) {
		this.studentsPASSED.add(student);
	}
	
	public void removePassedStudent(Student student) {
		this.studentsPASSED.remove(student);
	}

	public ArrayList<Student> getStudentsFAILED() {
		return studentsFAILED;
	}

	public void setStudentsFAILED(ArrayList<Student> studentsFAILED) {
		this.studentsFAILED = studentsFAILED;
	}
	
	public void addUnpassedStudent(Student student) {
		this.studentsFAILED.add(student);
	}
	
	@Override
	public String toString() {
		return this.subjectID + "," + this.subjectName;
	}
	
}
