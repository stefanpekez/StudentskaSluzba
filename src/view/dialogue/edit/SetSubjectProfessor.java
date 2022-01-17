package view.dialogue.edit;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.SubjectController;

public class SetSubjectProfessor extends JPanel {

	private static final long serialVersionUID = 5665242113409033294L;
	
	private JLabel label;
	private JTextField text;
	private JButton add;
	private JButton remove;
	
	private int selectedProfessor = -1;
	
	public SetSubjectProfessor(int subject, EditSubjectDialogue parent) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 50, 10, 50));
		
		label = new JLabel("Professor*");
		
		text = new JTextField(15);
		text.setMaximumSize(new Dimension(1000, 40));
		text.setEnabled(false);
		
		SetSubjectProfessor inst = this;
		
		add = new JButton("+");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SetSubjectProfessorAdd(inst, parent).setVisible(true);
			}
			
		});
		
		remove = new JButton("-");
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SetSubjectProfessorRemove(inst).setVisible(true);
				
			}
			
		});
		
		String prof = SubjectController.getInstance().getSubjectProfessor(subject);
		
		if(prof == "") {
			add.setEnabled(true);
			remove.setEnabled(false);
		} else {
			text.setText(prof);
			add.setEnabled(false);
			remove.setEnabled(true);
		}
		
		add(label);
		add(Box.createHorizontalGlue());
		add(text);
		add(add);
		add(remove);
	}
	
	public void setFieldText(String s) {
		text.setText(s);
	}
	
	public void setSelectedProfessor(int index) {
		selectedProfessor = index;
	}
	
	public int getSelectedProfessor() {
		return selectedProfessor;
	}
	
	public void setAddTrueRemoveFalse() {
		add.setEnabled(true);
		remove.setEnabled(false);
	}
	
	public void settAddFalseRemoveTrue() {
		add.setEnabled(false);
		remove.setEnabled(true);
	}
}
