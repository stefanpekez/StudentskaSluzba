package controller;

import model.DBDepartments;
import model.Department;
import model.Professor;

public class DepartmentController {
	
	private static DepartmentController instance = null;
	
	public static DepartmentController getInstance() {
		if(instance == null) {
			instance = new DepartmentController();
		}
		
		return instance;
	}
	
	public String[] getList() {
		return DBDepartments.getInstance().getList();
	}
	
	public Professor getSelectedDepartmentHead(int index) {
		return DBDepartments.getInstance().getSelectedDepartment(index).getDepartmentHead();
	}
	
	public Department getSelectedDepartment(int index) {
		return DBDepartments.getInstance().getSelectedDepartment(index);
	}
	
	public boolean setDepartmentBoss(int selectedDepartment, String selectedProfessor) {
		return DBDepartments.getInstance().setDepartmentBoss(selectedDepartment, selectedProfessor);
	}

}
