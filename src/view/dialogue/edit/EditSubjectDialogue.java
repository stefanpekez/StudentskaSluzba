package view.dialogue.edit;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.StudentController;
import controller.SubjectController;
import view.TabbedPane.TablePanel;
import view.dialogue.NewCB;
import view.dialogue.NewTF;

public class EditSubjectDialogue extends JDialog {
	
	private EditTF idPanel;
	private EditTF subjectNamePanel;
	private EditTF espbPanel;
	private EditCB yearPanel;
	private EditCB currentSemesterPanel;
	private JPanel buttonsPanel;
	private JButton accept;
	private JButton exit;
	
	public EditSubjectDialogue(Container mainframe, TablePanel tp) {
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		
		setSize(500, 300);
		setLocationRelativeTo(mainframe);
		setTitle("Edit Subject");
		setLayout(boxLayout);
		
		String[] currentSemester = { "ZIMSKI", "LETNJI"};
		String[] year = { "1", "2", "3", "4"};
		
		int selectedSubjectIdx = tp.getTable().convertRowIndexToModel(tp.getTable().getSelectedRow());
		
		idPanel = new EditTF("ID*", this, SubjectController.getInstance().getID(selectedSubjectIdx));
		subjectNamePanel = new EditTF("Subject Name*", this, SubjectController.getInstance().getName(selectedSubjectIdx));
		espbPanel = new EditTF("ESPB*", this, SubjectController.getInstance().getESPB(selectedSubjectIdx));
		yearPanel = new EditCB("Year*", year, SubjectController.getInstance().getYear(selectedSubjectIdx), "year");
		currentSemesterPanel = new EditCB("Semester*", currentSemester, SubjectController.getInstance().getCurrentSemester(selectedSubjectIdx), "currentS");
		
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton("ACCEPT");
		accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SubjectController.getInstance().editSubject(selectedSubjectIdx, idPanel, subjectNamePanel, espbPanel, yearPanel, currentSemesterPanel);
				tp.updateView();
				dispose();
			}
		});
		
		//exit button
		exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				dispose();
			} 
		});
		
		buttonsPanel.add(accept);
		buttonsPanel.add(exit);
		
		add(idPanel);
		add(subjectNamePanel);
		add(espbPanel);
		add(yearPanel);
		add(currentSemesterPanel);
		add(buttonsPanel);
		
		setResizable(false);
		setVisible(true);
	}

}
