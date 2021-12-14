package view.dialogue.edit;

import java.awt.Component;

import javax.swing.JDialog;

import view.TabbedPane.TablePanel;

public class EditProfessorDialogue extends JDialog{

	private static final long serialVersionUID = 6255201422606173526L;
	
	private EditProfessorTabbedPane tabs;
	
	public EditProfessorDialogue(Component parent, TablePanel panel) {
		setTitle("Edit Professor");
		setSize(500, 550);
		setLocationRelativeTo(parent);
		
		tabs = new EditProfessorTabbedPane(this, panel);
		
		
		add(tabs);
		setVisible(true);
	}
}
