package model;

import java.util.ArrayList;

public class Department {
	private String serialCode;
	private String name;
	private Professor departmentHead;
	private ArrayList<Professor> professors;
	
	public Department(String serialCode, String name, Professor departmentHead) {
		super();
		this.serialCode = serialCode;
		this.name = name;
		this.departmentHead = departmentHead;
		this.professors = new ArrayList<Professor>();
	}
	
	public Department() {
		this.professors = new ArrayList<Professor>();
	}

	@Override
	public String toString() {
		return serialCode + " --- " + name;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Professor getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(Professor departmentHead) {
		this.departmentHead = departmentHead;
	}

	public ArrayList<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(ArrayList<Professor> professors) {
		this.professors = professors;
	}
	
	public void addProfessor(Professor prof) {
		professors.add(prof);
	}
	
	
	
}
