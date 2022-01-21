package model;

public class DepartmentHeadRelation {
	private int departmentId;
	private int professorId;
	
	public DepartmentHeadRelation(int departmentId, int professorId) {
		super();
		this.departmentId = departmentId;
		this.professorId = professorId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	
	
}
