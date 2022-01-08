package view.dialogue.edit;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import view.TabbedPane.TablePanel;

public class EditStudentTabbedPane extends JTabbedPane {
	
	private EditStudentInfo infoTab;
	private EditStudentUnpassed unpassedExams;
	
	public EditStudentTabbedPane(EditStudentDialogue editDialogue, TablePanel tp) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		infoTab = new EditStudentInfo(editDialogue, tp);
		unpassedExams = new EditStudentUnpassed(editDialogue, tp);
		
		addTab("Info", infoTab);
		addTab("Unpassed", unpassedExams);
	}
}
