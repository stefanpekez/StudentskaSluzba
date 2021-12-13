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

import controller.ProfessorController;
import view.TabbedPane.TablePanel;

public class DeleteProfessorDialogue extends JDialog {

	private static final long serialVersionUID = -7617807751227349718L;
	
	private JLabel message;
	
	public DeleteProfessorDialogue(Component parent, TablePanel tab) {
		message = new JLabel("Are you sure you want to delete this professor?", SwingConstants.CENTER);
		
		setTitle("Delete Professor");
		setSize(450,100);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(parent);
		
		JPanel buttons = new JPanel();
		
		JButton yes = new JButton("YES");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Brisi ovog profu");
				ProfessorController.getInstance().deleteProfessor(tab.getTable().getSelectedRow());
				tab.updateView();
				setVisible(false);
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
