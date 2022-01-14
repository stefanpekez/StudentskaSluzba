package view.dialogue.edit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controller.AbstractTableModelExamsPassed;
import model.DBExamsPassed;
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
		
		addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
            	   switch(getSelectedIndex()) {
               	case 0:
               		break;
               	case 1:
               		AbstractTableModelExamsPassed examsModelPassed = (AbstractTableModelExamsPassed) passedExams.getTable().getModel();
    				examsModelPassed.fireTableDataChanged();
    				validate();
    				
               		passedExams.getDynAverage().setText(Double.toString(DBExamsPassed.getInstance().getAvgGrade(tp.getTable().getSelectedRow())));
               		
               		passedExams.getDynESPB().setText(Integer.toString(DBExamsPassed.getInstance().getESPB()));
               		break;
               	case 2:
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
		
		addTab("Info", infoTab);
		addTab("Passed", passedExams);
		addTab("Unpassed", unpassedExams);
	}
	
}
