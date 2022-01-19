package controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
	
	private static LanguageController instance;
	
	public static LanguageController getInstance() {
		if(instance == null) instance = new LanguageController();
		
		return instance;
	}
	
	private ResourceBundle resourceBundle;
	
	private LanguageController() {
		Locale.setDefault(new Locale("en", "US"));
		resourceBundle = ResourceBundle.getBundle("controller.MessageResources.MessageResources", Locale.getDefault());
	}
	
	public void changeLanguage() {
		resourceBundle = ResourceBundle.getBundle("controller.MessageResources.MessageResources", Locale.getDefault());
		
		//TODO add calls for static metods for classes that are initiated imediately
		
	}
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
