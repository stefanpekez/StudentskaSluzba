package view.dialogue;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.DepartmentController;
import controller.LanguageController;
import controller.ProfessorController;

public class DepartmentAddProfessor extends JDialog{
	
	private JList<String> professors;
	private JButton ok;
	private JButton exit;

	DepartmentAddProfessor(DepartmentListDialogue parent, int selectedDepartment, DepartmentAddBoss bossesList) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(350, 250);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("ChooseProfessorTitle"));
		setLocationRelativeTo(parent);
		
		String[] list = ProfessorController.getInstance().getProfessorDepartmentList();
		
		professors = new JList<String>(list);
		
		
		JScrollPane professorsScrollable = new JScrollPane(professors);
		professorsScrollable.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel buttonz = new JPanel();
		ok = new JButton(LanguageController.getInstance().getResourceBundle().getString("OkButton"));
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(professors.getSelectedIndex() != -1) {
					if(DepartmentController.getInstance().addProfessor(selectedDepartment, professors.getSelectedValue()) < 5) {
						JOptionPane.showMessageDialog(parent, "Professor added but will not be shown on boss selection list because his years are sub 5", "Not pushin P", 1);
					}
					if(bossesList != null) {
						bossesList.updateList(selectedDepartment);
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(parent, "Please select a professor", "Error", 0);
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
		setVisible(true);
	}
}
