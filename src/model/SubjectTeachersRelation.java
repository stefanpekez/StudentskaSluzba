package model;

public class SubjectTeachersRelation {
	private int teachersId;
	private int subjectId;
	
	
	public SubjectTeachersRelation(int teachersId, int subjectId) {
		super();
		this.teachersId = teachersId;
		this.subjectId = subjectId;
	}
	
	public int getTeachersId() {
		return teachersId;
	}
	public void setTeachersId(int teachersId) {
		this.teachersId = teachersId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
