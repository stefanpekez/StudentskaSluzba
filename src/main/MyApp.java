package main;



import java.io.IOException;

import com.formdev.flatlaf.FlatLightLaf;

import model.DBAddress;
import model.DBDepartments;
import model.DBProfessor;
import model.DBStudent;
import model.DBSubject;
import view.MainFrame;

public class MyApp {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		initializeDB();
		MainFrame mainWindow = new MainFrame();	
	}
	
	public static void initializeDB() {
		DBAddress.getInstance();
		DBProfessor.getInstance();
		DBStudent.getInstance();
		DBSubject.getInstance();
		DBDepartments.getInstance();
		try {
			DBStudent.getInstance().setupUnpassed();
			DBStudent.getInstance().setupPassed();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}

