package view.dialogue;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.DepartmentController;
import controller.LanguageController;
import view.MainFrame;

public class DepartmentListDialogue extends JDialog {

	private static final long serialVersionUID = -8392889665106099265L;

	private JList<String> departments;
	private JButton addBoss;
	private JButton addProfessor;
	private JLabel currentDH = new JLabel();
	
	private DepartmentAddBoss addBossDialogue = null;
	private boolean addBossOpen = false;
	
	public DepartmentListDialogue(MainFrame parent) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(500, 275);
		setLocationRelativeTo(parent);
		setTitle(LanguageController.getInstance().getResourceBundle().getString("DepartmentsTab"));
		
		JPanel buttonPanel = new JPanel();
		JPanel bossLabelPanel = new JPanel();
		JPanel listsPanel = new JPanel();
		
		DepartmentListDialogue thisDialog = this;
		
		departments = new JList<String>(DepartmentController.getInstance().getList());
		JScrollPane departmentScroller = new JScrollPane(departments); 
		
		addBoss = new JButton(LanguageController.getInstance().getResourceBundle().getString("AddBoss"));
		addBoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(departments.getSelectedIndex() != -1) {
					addBossDialogue = new DepartmentAddBoss(thisDialog, listsPanel, departments, currentDH);
					addBoss.setEnabled(false);
					addBossOpen = true;
				}
				else
					JOptionPane.showMessageDialog(parent, LanguageController.getInstance().getResourceBundle().getString("DepartmentNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
			}
		});
		
		addProfessor = new JButton(LanguageController.getInstance().getResourceBundle().getString("AddProfessor"));
		
		addProfessor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(departments.getSelectedIndex() != -1) {
					new DepartmentAddProfessor(thisDialog, departments.getSelectedIndex(), addBossDialogue);
					
					
				} else {
					JOptionPane.showMessageDialog(parent, LanguageController.getInstance().getResourceBundle().getString("DepartmentNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
				}
			}
			
		});
		
		
		buttonPanel.add(addBoss);
		buttonPanel.add(addProfessor);
		
		bossLabelPanel.add(currentDH);
		
		add(buttonPanel);
		add(bossLabelPanel);
		listsPanel.add(departmentScroller);
		add(listsPanel);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		
	}
	
	public void setCurrenDHText(String newText) {
		currentDH.setText(newText);
	}
}
