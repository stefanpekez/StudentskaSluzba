package model;

import java.time.LocalDate;

public class PassedGradeRelation {
	private int studentId;
	private int subjectId;
	private int grade;
	private LocalDate date;
	
	public PassedGradeRelation(int studentId, int subjectId, int grade, LocalDate date) {
		super();
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.grade = grade;
		this.date = date;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
