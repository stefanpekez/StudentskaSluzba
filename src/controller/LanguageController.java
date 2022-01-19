package controller;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.MainFrame;
import view.MenuBar;
import view.StatusBar;

public class LanguageController {
	
	private static LanguageController instance;
	
	public static LanguageController getInstance() {
		if(instance == null) instance = new LanguageController();
		
		return instance;
	}
	
	private MenuBar menu;
	private StatusBar statusbar;
	private ResourceBundle resourceBundle;
	
	private LanguageController() {
		Locale.setDefault(new Locale("en", "US"));
		resourceBundle = ResourceBundle.getBundle("controller.MessageResources.MessageResources", Locale.getDefault());
	}
	
	public void changeLanguage(MainFrame main) {
		resourceBundle = ResourceBundle.getBundle("controller.MessageResources.MessageResources", Locale.getDefault());
		main.initGUI();
		
		//System.out.println(LanguageController.getInstance().getResourceBundle().getString("FileMenuBar"));
		
		//TODO add calls for static metods for classes that are initiated imediately
		
	}
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
