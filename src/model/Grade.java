package model;

import java.time.LocalDate;

public class Grade {
	private Student student;
	private Subject subject;
	private int grade;
	private LocalDate dateOfPassingExam;
	
	public Grade(Student student, Subject subject, int grade, LocalDate dateOfPassingExam) {
		super();
		this.student = student;
		this.subject = subject;
		if (grade >= 6 && grade <= 10)
			this.grade = grade;
		this.dateOfPassingExam = dateOfPassingExam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public LocalDate getDateOfPassingExam() {
		return dateOfPassingExam;
	}

	public void setDateOfPassingExam(LocalDate dateOfPassingExam) {
		this.dateOfPassingExam = dateOfPassingExam;
	}
	
	
}
