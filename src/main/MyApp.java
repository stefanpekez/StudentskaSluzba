package main;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import view.MainFrame;

public class MyApp {

	public static void main(String[] args) {
		FlatDarkLaf.setup();
		MainFrame mainWindow = new MainFrame();
		
	}
	
}
