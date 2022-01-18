package view.dialogue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.DepartmentController;
import controller.ProfessorController;
import model.DBProfessor;

public class DepartmentAddBoss {
	
	private JList<String> professors;
	private JButton plus;
	private JButton minus;
	
	public DepartmentAddBoss(DepartmentListDialogue parent, JPanel listsPanel, JList<String> departments, JLabel label) {
		
		parent.setSize(650, 275);
		parent.setLocationRelativeTo(parent);
		
		//	Shows current head of department right after pressing ADD BOSS
		if(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()) == null)
			parent.setCurrenDHText("There is no department head assigned for selected department");
		else
			parent.setCurrenDHText(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()).toString());
		
		//	Updates info about current head of department when selecting different departments
		departments.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()) == null) {
					plus.setEnabled(true);
					minus.setEnabled(false);
					parent.setCurrenDHText("There is no department head assigned for selected department");
				}
				else {
					plus.setEnabled(false);
					minus.setEnabled(true);
					parent.setCurrenDHText(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()).toString());
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
		
		if(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()) == null)
			plus.setEnabled(true);
		else
			plus.setEnabled(false);
		
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//	-1 means no professor is selected
				if(professors.getSelectedIndex() != -1) {
					if(DepartmentController.getInstance().setDepartmentBoss(departments.getSelectedIndex(), professors.getSelectedValue())) {
						minus.setEnabled(true);
						plus.setEnabled(false);
						parent.setCurrenDHText(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()).toString());
					} else 
						JOptionPane.showMessageDialog(parent, "Selected professor is already head of another department", "Error", 0);
				} else JOptionPane.showMessageDialog(parent, "Professor not selected", "Error", 0);
			}
		});
		
		minus = new JButton("-");
		
		//	Turns off minus button if there already is a head for the selected department
		if(DepartmentController.getInstance().getSelectedDepartmentHead(departments.getSelectedIndex()) == null)
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
				plus.setEnabled(true);
			}
		});
		
		buttonsPanel.add(plus);
		buttonsPanel.add(minus);
		
		listsPanel.add(buttonsPanel);
		listsPanel.add(professorScroller);
	}

}
