package view.dialogue.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ProfessorController;
import controller.StudentController;

public class DeleteGradeDialogue extends JDialog {

	private static final long serialVersionUID = -1603489017555725062L;
	
	private JLabel message;
	
	public DeleteGradeDialogue(int grade, EditStudentTabbedPane tabbedpane) {
		message = new JLabel("Are you sure you want to cancel this grade?", SwingConstants.CENTER);
		
		setTitle("Cancel Grade");
		setSize(450,100);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(tabbedpane);
		
		JPanel buttons = new JPanel();
		
		JButton yes = new JButton("YES");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentController.getInstance().cancelGrade(grade);
				tabbedpane.updateView(1);
				dispose();
			}
			
		});
		
		JButton no = new JButton("NO");
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		
		buttons.add(yes);
		buttons.add(no);
		
		add(message);
		add(buttons);
	}
}
