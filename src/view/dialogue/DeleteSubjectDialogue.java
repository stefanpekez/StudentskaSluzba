package view.dialogue;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.SubjectController;
import view.TabbedPane.TablePanel;

public class DeleteSubjectDialogue extends JDialog {

	private static final long serialVersionUID = 4886275935152179917L;
	
	private JLabel message;

	public DeleteSubjectDialogue(Component parent, TablePanel tab) {
		message = new JLabel("Are you sure you want to delete this subject?", SwingConstants.CENTER);
		
		setTitle("Delete Subject");
		setSize(450,100);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(parent);
		
		JPanel buttons = new JPanel();
		
		JButton yes = new JButton("YES");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectController.getInstance().deleteSubject(tab.getTable().getSelectedRow());
				tab.updateView();
				dispose();
			}
			
		});
		
		JButton no = new JButton("NO");
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
