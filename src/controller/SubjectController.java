package controller;

import model.DBSubject;

public class SubjectController {
	
	private static SubjectController instance = null;
	
	public static SubjectController getInstance() {
		if(instance == null) {
			instance = new SubjectController();
		}
		
		return instance;
	}
	
	public void deleteSubject(int row) {
		DBSubject.getInstance().deleteSubject(row);
	}
}
