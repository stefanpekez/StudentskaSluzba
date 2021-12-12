package main;

import com.formdev.flatlaf.FlatLightLaf;

import model.DBSubject;
import view.MainFrame;

public class MyApp {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		MainFrame mainWindow = new MainFrame();	
	}
	
}
