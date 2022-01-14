package model;

import java.time.LocalDate;

public class Grade {
	private Student student;
	private Subject subject;
	private int gradeValue;
	private LocalDate dateOfPassingExam;
	
	public Grade(Student student, Subject subject, int gradeValue, LocalDate dateOfPassingExam) {
		super();
		this.student = student;
		this.subject = subject;
		if (gradeValue >= 6 && gradeValue <= 10)
			this.gradeValue = gradeValue;
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

	public int getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(int gradeValue) {
		if (gradeValue >= 6 && gradeValue <= 10)
			this.gradeValue = gradeValue;
	}

	public LocalDate getDateOfPassingExam() {
		return dateOfPassingExam;
	}

	public void setDateOfPassingExam(LocalDate dateOfPassingExam) {
		this.dateOfPassingExam = dateOfPassingExam;
	}

	@Override
	public String toString() {
		return "Grade [student=" + student + ", subject=" + subject + ", gradeValue=" + gradeValue
				+ ", dateOfPassingExam=" + dateOfPassingExam.toString() + "]";
	}
	
	
}
