package view.dialogue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StudentController;
import view.TabbedPane.TablePanel;

public class DeleteStudentDialogue extends JDialog {
	
	private JLabel dialogueText;
	private JPanel questionPanel;
	private JPanel buttonsPanel;
	private JButton accept;
	private JButton exit;
	
	public DeleteStudentDialogue(Container mainframe, TablePanel tp) {
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		
		setTitle("Delete student");
		setSize(400, 100);
		setLocationRelativeTo(mainframe);
		setLayout(boxLayout);
		
		dialogueText = new JLabel("Do you want to delete this student?");
		
		questionPanel = new JPanel();
		questionPanel.add(dialogueText);
		
		buttonsPanel = new JPanel();
		
		//accept button
		accept = new JButton("ACCEPT");
		accept.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				//TODO implement function to add an object of Student class to DBStudent
				StudentController.getInstance().deleteStudent(tp.getTable().getSelectedRow());
				tp.updateView();
				exitDialog();
			} 
		});
				
		//exit button
		exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				exitDialog();
			} 
		});
				
		buttonsPanel.add(accept);
		buttonsPanel.add(exit);
		
		add(questionPanel);
		add(buttonsPanel);
		
		setVisible(true);
	}
	
	public void exitDialog() {
		dispose();
	}

}
