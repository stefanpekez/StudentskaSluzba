package view.dialogue.edit;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.LanguageController;
import controller.SubjectController;
import view.TabbedPane.TablePanel;

public class EditSubjectDialogue extends JDialog {
	
	private EditTF idPanel;
	private EditTF subjectNamePanel;
	private EditTF espbPanel;
	private EditCB yearPanel;
	private EditCB currentSemesterPanel;
	private JPanel buttonsPanel;
	private JButton accept;
	private JButton exit;
	
	private SetSubjectProfessor professor;
	
	public EditSubjectDialogue(Container mainframe, TablePanel tp) {
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		
		setSize(500, 330);
		setLocationRelativeTo(mainframe);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("EditSubjectTitle"));
		setLayout(boxLayout);
		
		String[] currentSemester = { "ZIMSKI", "LETNJI"};
		String[] year = { "1", "2", "3", "4"};
		
		int selectedSubjectIdx = tp.getTable().convertRowIndexToModel(tp.getTable().getSelectedRow());
		
		idPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Id"), this, SubjectController.getInstance().getID(selectedSubjectIdx));
		subjectNamePanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Name"), this, SubjectController.getInstance().getName(selectedSubjectIdx));
		espbPanel = new EditTF(LanguageController.getInstance().getResourceBundle().getString("Espb"), this, SubjectController.getInstance().getESPB(selectedSubjectIdx));
		yearPanel = new EditCB(LanguageController.getInstance().getResourceBundle().getString("Year"), year, SubjectController.getInstance().getYear(selectedSubjectIdx), "year");
		currentSemesterPanel = new EditCB(LanguageController.getInstance().getResourceBundle().getString("Semester"), currentSemester, SubjectController.getInstance().getCurrentSemester(selectedSubjectIdx), "currentS");
		
		professor = new SetSubjectProfessor(selectedSubjectIdx, this);
		
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton(LanguageController.getInstance().getResourceBundle().getString("AcceptButton"));
		accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SubjectController.getInstance().editSubject(selectedSubjectIdx, idPanel, subjectNamePanel, espbPanel, yearPanel, currentSemesterPanel);
				SubjectController.getInstance().editSubjectProfessor(selectedSubjectIdx, professor.getSelectedProfessor());
				tp.updateView();
				dispose();
			}
		});
		
		//exit button
		exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
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
		add(professor);
		add(buttonsPanel);
		
		setResizable(false);
		setVisible(true);
	}

}
