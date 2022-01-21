package model;

public class UnpassedExamRelation {
	
	private int studentId;
	private int subjectId;
	
	public UnpassedExamRelation(int stud, int sub) {
		this.studentId = stud;
		this.subjectId = sub;
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
	
}
