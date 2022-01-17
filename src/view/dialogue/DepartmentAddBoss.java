package view.dialogue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.DepartmentController;
import controller.ProfessorController;

public class DepartmentAddBoss {
	
	private JList<String> professors;
	private JButton plus;
	private JButton minus;
	
	public DepartmentAddBoss(DepartmentListDialogue parent, JPanel listsPanel, JList<String> departments) {
		
		parent.setSize(650, 275);
		parent.setLocationRelativeTo(parent);
		
		//	Shows current head of department right after pressing ADD BOSS
		if(DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).getDepartmentHead() == null)
			parent.setCurrenDHText("There is no department head assigned for selected department");
		else
			parent.setCurrenDHText(DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).getDepartmentHead().toString());
		
		//	Updates info about current head of department when selecting different departments
		departments.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).getDepartmentHead() == null) {
					minus.setEnabled(false);
					parent.setCurrenDHText("There is no department head assigned for selected department");
				}
				else {
					minus.setEnabled(true);
					parent.setCurrenDHText(DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).getDepartmentHead().toString());
				}
				
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
		
		professors = new JList<String>(ProfessorController.getInstance().getProfessorsOverWorkingYearLimit());
		JScrollPane professorScroller = new JScrollPane(professors);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
		//	Sets new head for selected department
		plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	-1 means no professor is selected
				if(professors.getSelectedIndex() != -1) {
					if(DepartmentController.getInstance().setDepartmentBoss(departments.getSelectedIndex(), professors.getSelectedIndex())) {
						minus.setEnabled(true);
						parent.setCurrenDHText(DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).getDepartmentHead().toString());
					}
				} else JOptionPane.showMessageDialog(parent, "Professor not selected", "Error", 0);
			}
		});
		
		minus = new JButton("-");
		
		//	Turns off minus button if there already is a head for the selected department
		if(DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).getDepartmentHead() == null)
			minus.setEnabled(false);
		else
			minus.setEnabled(true);
		
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DepartmentController.getInstance().getSelectedDepartment(departments.getSelectedIndex()).setDepartmentHead(null);
				parent.setCurrenDHText("There is no department head assigned for selected department");
				minus.setEnabled(false);
			}
		});
		
		buttonsPanel.add(plus);
		buttonsPanel.add(minus);
		
		listsPanel.add(buttonsPanel);
		listsPanel.add(professorScroller);
	}

}
