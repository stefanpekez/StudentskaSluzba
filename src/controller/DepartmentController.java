package controller;

import javax.swing.JOptionPane;

import model.DBDepartments;
import model.DBProfessor;
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
	
	public int addProfessor(int department, String professor) {
		String[] split = professor.split(",");
		
		Professor prof = DBProfessor.getInstance().getProfessor(split[0]);
		
		DBDepartments.getInstance().addProfessorToDepartment(department, prof);
		
		return prof.getWorkingYears();
	}

}
