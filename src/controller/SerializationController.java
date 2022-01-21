package controller;

import java.io.IOException;

import model.DBAddress;
import model.DBDepartments;
import model.DBProfessor;
import model.DBStudent;
import model.DBSubject;
import model.DepartmentHeadSerialization;
import model.PassedGradeSerialization;
import model.SubjectProfessorSerialization;
import model.UnpassedSerialization;

public class SerializationController {
	private static SerializationController instance;
	
	public static SerializationController getInstaince() {
		if(instance == null) instance = new SerializationController();
		
		return instance;
	}
	
	public void serialize() throws IOException {
		DBProfessor.getInstance().serialize();
		DBStudent.getInstance().serialize();
		DBSubject.getInstance().serialize();
		DBDepartments.getInstance().serialize();
		DBAddress.getInstance().serialize();
		UnpassedSerialization.getInstance().serialize();
		PassedGradeSerialization.getInstance().serialize();
		SubjectProfessorSerialization.getInstance().serializeHead();
		SubjectProfessorSerialization.getInstance().serializeTeachers();
		DepartmentHeadSerialization.getInstance().serialize();
	}
	
	public void deserialize() {
		try {
			PassedGradeSerialization.getInstance().deserialize();
			DepartmentHeadSerialization.getInstance().deserialize();
			UnpassedSerialization.getInstance().deserialize();
			SubjectProfessorSerialization.getInstance().deserializeHead();
			SubjectProfessorSerialization.getInstance().deserializeTeachers();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	
		DBProfessor.getInstance();
		DBSubject.getInstance();
		DBStudent.getInstance();
		DBDepartments.getInstance();
	}
}
