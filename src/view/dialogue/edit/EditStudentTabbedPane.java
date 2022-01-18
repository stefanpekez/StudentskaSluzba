package view.dialogue.edit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controller.AbstractTableModelExams;
import controller.AbstractTableModelExamsPassed;
import controller.LanguageController;
import model.DBExamsPassed;
import view.TabbedPane.TablePanel;

public class EditStudentTabbedPane extends JTabbedPane {
	
	private EditStudentInfo infoTab;
	private EditStudentPassed passedExams;
	private EditStudentUnpassed unpassedExams;
	private TablePanel tp;
	
	public EditStudentTabbedPane(EditStudentDialogue editDialogue, TablePanel tp) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		this.tp = tp;
		
		infoTab = new EditStudentInfo(editDialogue, tp);
		passedExams = new EditStudentPassed(editDialogue, tp, this);
		unpassedExams = new EditStudentUnpassed(editDialogue, tp, this, passedExams);
		
		addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
            	   switch(getSelectedIndex()) {
               	case 0:
               		break;
               	case 1:
               		updateView(1);
               		break;
               	case 2:
               		updateView(2);
               		break;
               }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });
		
		addTab(LanguageController.getInstance().getResourceBundle().getString("InfoTab"), infoTab);
		addTab(LanguageController.getInstance().getResourceBundle().getString("PassedTab"), passedExams);
		addTab(LanguageController.getInstance().getResourceBundle().getString("UnpassedTab"), unpassedExams);
	}
	
	public void updateView(int index) {
		
		if(index == 1) {
			//update passed exams
			AbstractTableModelExamsPassed examsModelPassed = (AbstractTableModelExamsPassed) passedExams.getTable().getModel();
			examsModelPassed.fireTableDataChanged();
			validate();
			
	   		passedExams.getDynAverage().setText(Double.toString(DBExamsPassed.getInstance().getAvgGrade(tp.getTable().convertRowIndexToModel(tp.getTable().getSelectedRow()))));
	   		
	   		passedExams.getDynESPB().setText(Integer.toString(DBExamsPassed.getInstance().getESPB()));
		} else {
			// update unpassed
			AbstractTableModelExams model = (AbstractTableModelExams) unpassedExams.getTable().getModel();
			model.fireTableDataChanged();
			validate();
		}
		
	}
	
}
