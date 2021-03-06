package view.dialogue.edit;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.LanguageController;
import controller.ProfessorController;

public class SetSubjectProfessorAdd extends JDialog{

	private static final long serialVersionUID = -611281363202888941L;
	
	
	private JButton ok;
	private JButton exit;
	private JList<String> professors;
	
	public SetSubjectProfessorAdd(SetSubjectProfessor panel, EditSubjectDialogue parent) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(350, 250);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("ChooseProfessorTitle"));
		setLocationRelativeTo(parent);
		
		professors = new JList<String>(ProfessorController.getInstance().getProfessorList());
		
		JScrollPane professorsScrollable = new JScrollPane(professors);
		professorsScrollable.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel buttonz = new JPanel();
		ok = new JButton(LanguageController.getInstance().getResourceBundle().getString("OkButton"));
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(professors.getSelectedIndex() != -1) {
					panel.setFieldText(professors.getSelectedValue());
					panel.setSelectedProfessor(professors.getSelectedIndex());
					panel.settAddFalseRemoveTrue();
					dispose();
				} else {
					System.out.println("Please select a professor");
				}
			}
			
		});
		
		exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		
		buttonz.add(ok);
		buttonz.add(exit);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		
		add(professorsScrollable);
		add(buttonz);
		
	}
}
