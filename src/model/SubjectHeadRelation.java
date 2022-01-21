package model;

public class SubjectHeadRelation {
	private int subjectId;
	private int professorId;
	
	public SubjectHeadRelation(int subjectId, int professorId) {
		super();
		this.subjectId = subjectId;
		this.professorId = professorId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	
}
