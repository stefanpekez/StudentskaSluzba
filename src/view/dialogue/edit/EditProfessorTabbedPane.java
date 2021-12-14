package view.dialogue.edit;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import view.TabbedPane.TablePanel;

public class EditProfessorTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 6081671041252027973L;
	
	private EditProfessorInfo info;
	
	public EditProfessorTabbedPane(EditProfessorDialogue dialogue, TablePanel panel) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		info = new EditProfessorInfo(dialogue, panel);
		
		add("Info", info);
	}
}
