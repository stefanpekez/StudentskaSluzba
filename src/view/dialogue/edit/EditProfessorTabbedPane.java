package view.dialogue.edit;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controller.AbstractTableModelTeaches;
import controller.LanguageController;
import view.TabbedPane.TablePanel;

public class EditProfessorTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 6081671041252027973L;
	
	private EditProfessorInfo info;
	private EditProfessorTeaches teaches;
	
	public EditProfessorTabbedPane(EditProfessorDialogue dialogue, TablePanel panel) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		info = new EditProfessorInfo(dialogue, panel);
		teaches = new EditProfessorTeaches(dialogue, panel, this);
		
		
		add(LanguageController.getInstance().getResourceBundle().getString("InfoTab"), info);
		add(LanguageController.getInstance().getResourceBundle().getString("TeachesTab"), teaches);
	}
	
	public void updateView() {
		AbstractTableModelTeaches teachesModel = (AbstractTableModelTeaches) teaches.getTable().getModel();
		teachesModel.fireTableDataChanged();
		validate();
	}
}
