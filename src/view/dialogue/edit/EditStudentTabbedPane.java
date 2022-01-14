package view.dialogue.edit;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import view.TabbedPane.TablePanel;

public class EditStudentTabbedPane extends JTabbedPane {
	
	private EditStudentInfo infoTab;
	private EditStudentPassed passedExams;
	private EditStudentUnpassed unpassedExams;
	
	public EditStudentTabbedPane(EditStudentDialogue editDialogue, TablePanel tp) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		infoTab = new EditStudentInfo(editDialogue, tp);
		passedExams = new EditStudentPassed(editDialogue, tp);
		unpassedExams = new EditStudentUnpassed(editDialogue, tp, passedExams);
		
		addTab("Info", infoTab);
		addTab("Passed", passedExams);
		addTab("Unpassed", unpassedExams);
	}
	
}
