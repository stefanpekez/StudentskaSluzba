package controller;

import model.DBProfessor;
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
	
	public void searchSubject(String query) {
		String[] parsedQuery = query.split(",");
		
		switch (parsedQuery.length) {
		case 1:
			DBSubject.getInstance().subjectSearchOne(parsedQuery[0].replaceAll("\\W", ""));
			break;
		case 2:
			DBSubject.getInstance().subjectSearchTwo(parsedQuery[0].replaceAll("\\W", ""), parsedQuery[1].replaceAll("\\W", ""));
			break;
		default:
			System.out.println("Wrong query format");
			break;
		}
	}
}
