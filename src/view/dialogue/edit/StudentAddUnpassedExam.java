package view.dialogue.edit;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.LanguageController;
import controller.SubjectController;

public class StudentAddUnpassedExam extends JDialog {
	
	private JList<String> subjects;
	private JButton add;
	private JButton cancel;
	DefaultListModel<String> model;
	
	public StudentAddUnpassedExam(EditStudentUnpassed parent, EditStudentTabbedPane stp, int selectedStudent) {
		setLayout(new FlowLayout());
		setSize(350, 275);
		setLocationRelativeTo(parent);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("AddUnpassedExamTitle"));
		
		JPanel labelPanel = new JPanel();
		JLabel label = new JLabel(LanguageController.getInstance().getResourceBundle().getString("SelectSubjectMessageUnpassed"));
		labelPanel.add(label);
		
		//	Subject list
		JPanel listPanel = new JPanel();
		
		subjects = new JList<String>(SubjectController.getInstance().getSubjectListForStudent(selectedStudent));
		subjects.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(subjects.getSelectedIndex() == -1)
					add.setEnabled(false);
				else
					add.setEnabled(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane subjectScroller = new JScrollPane(subjects);
		listPanel.add(subjectScroller);
		
		//	Buttons
		JPanel buttonPanel = new JPanel();
		
		add = new JButton(LanguageController.getInstance().getResourceBundle().getString("UnpassedAddButton"));
		add.setEnabled(false);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SubjectController.getInstance().addUnpassedExam(subjects.getSelectedValue(), selectedStudent);
				stp.updateView(2);
				dispose();
			}
		});
		cancel = new JButton(LanguageController.getInstance().getResourceBundle().getString("Cancel"));
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		buttonPanel.add(add);
		buttonPanel.add(cancel);
		
		add(labelPanel);
		add(listPanel);
		add(buttonPanel);
		
		setResizable(false);
		setVisible(true);
	}

}
