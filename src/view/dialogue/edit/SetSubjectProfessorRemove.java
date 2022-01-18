package view.dialogue.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LanguageController;

public class SetSubjectProfessorRemove extends JDialog {
	
	
	private JLabel message;
	
	public SetSubjectProfessorRemove(SetSubjectProfessor panel) {
		message = new JLabel(LanguageController.getInstance().getResourceBundle().getString("RemoveProfessorMessage"), SwingConstants.CENTER);
		
		setTitle(LanguageController.getInstance().getResourceBundle().getString("RemoveProfessorTitle"));
		setSize(450,100);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(getParent());
		
		JPanel buttons = new JPanel();
		
		JButton yes = new JButton(LanguageController.getInstance().getResourceBundle().getString("Yes"));
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setFieldText("");
				panel.setSelectedProfessor(-1);
				panel.setAddTrueRemoveFalse();
				dispose();
			}
			
		});
		
		JButton no = new JButton(LanguageController.getInstance().getResourceBundle().getString("No"));
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		buttons.add(yes);
		buttons.add(no);
		
		add(message);
		add(buttons);
	}
}
