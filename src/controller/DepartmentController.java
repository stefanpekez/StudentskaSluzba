package controller;

import model.DBDepartments;
import model.Department;

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
	
	public Department getSelectedDepartment(int index) {
		return DBDepartments.getInstance().getSelectedDepartment(index);
	}
	
	public boolean setDepartmentBoss(int selectedDepartment, int selectedProfessor) {
		return DBDepartments.getInstance().setDepartmentBoss(selectedDepartment, selectedProfessor);
	}

}
