package main;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import view.MainFrame;

public class MyApp {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		MainFrame mainWindow = new MainFrame();
		
	}
	
}
