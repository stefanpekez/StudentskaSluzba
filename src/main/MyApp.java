package main;



import java.io.IOException;

import com.formdev.flatlaf.FlatLightLaf;

import model.DBAddress;
import model.DBDepartments;
import model.DBProfessor;
import model.DBStudent;
import model.DBSubject;
import model.DepartmentHeadSerialization;
import model.PassedGradeRelation;
import model.PassedGradeSerialization;
import model.SubjectProfessorSerialization;
import model.UnpassedSerialization;
import view.MainFrame;

public class MyApp {

	public static void main(String[] args) throws IOException {
		FlatLightLaf.setup();
		initializeDB();
		MainFrame mainWindow = new MainFrame();	
	}
	
	public static void initializeDB() {
		DBAddress.getInstance();
		
		//UCITAJ SVE RELACIJE PRE GLAVNIH ENTITETA
		
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
		
		/*
		try {
			DBStudent.getInstance().setupUnpassed();
			DBStudent.getInstance().setupPassed();
		} catch(IOException e) {
			e.printStackTrace();
		}
		*/
	}
}

