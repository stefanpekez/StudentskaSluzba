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

	public static void main(String[] args) throws IOException {
		FlatLightLaf.setup();
		initializeDB();
		MainFrame mainWindow = new MainFrame();	
	}
	
	public static void initializeDB() {
		DBAddress.getInstance();
		DBSubject.getInstance();
		DBProfessor.getInstance();
		DBStudent.getInstance();
		DBDepartments.getInstance();
		try {
			DBStudent.getInstance().setupUnpassed();
			DBStudent.getInstance().setupPassed();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

