package view.dialogue.edit;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LanguageController;
import controller.StudentController;

public class DeleteGradeDialogue extends JDialog {

	private static final long serialVersionUID = -1603489017555725062L;
	
	private JLabel message;
	
	public DeleteGradeDialogue(int grade, EditStudentTabbedPane tabbedpane) {
		message = new JLabel(LanguageController.getInstance().getResourceBundle().getString("AreYouSureGrade"), SwingConstants.CENTER);
		
		setTitle(LanguageController.getInstance().getResourceBundle().getString("CancelGradeTitle"));
		setSize(450,100);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(tabbedpane);
		
		JPanel buttons = new JPanel();
		
		JButton yes = new JButton(LanguageController.getInstance().getResourceBundle().getString("Yes"));
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentController.getInstance().cancelGrade(grade);
				tabbedpane.updateView(1);
				dispose();
			}
			
		});
		
		JButton no = new JButton(LanguageController.getInstance().getResourceBundle().getString("No"));
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		
		buttons.add(yes);
		buttons.add(no);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		add(message);
		add(buttons);
	}
}
