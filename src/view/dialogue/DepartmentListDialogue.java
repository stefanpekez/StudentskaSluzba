package view.dialogue;

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
import view.MainFrame;

public class DepartmentListDialogue extends JDialog {

	private static final long serialVersionUID = -8392889665106099265L;

	private JList<String> departments;
	private JButton addBoss;
	private JLabel currentDH = new JLabel();
	
	public DepartmentListDialogue(MainFrame parent) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(500, 275);
		setLocationRelativeTo(parent);
		setTitle("Departments");
		
		JPanel buttonPanel = new JPanel();
		JPanel bossLabelPanel = new JPanel();
		JPanel listsPanel = new JPanel();
		
		DepartmentListDialogue thisDialog = this;
		
		departments = new JList<String>(DepartmentController.getInstance().getList());
		JScrollPane departmentScroller = new JScrollPane(departments); 
		
		addBoss = new JButton("ADD BOSS");
		addBoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(departments.getSelectedIndex() != -1) {
					new DepartmentAddBoss(thisDialog, listsPanel, departments);
					addBoss.setEnabled(false);
				}
				else
					JOptionPane.showMessageDialog(parent, "Department not selected", "Error", 0);
			}
		});
		
		
		buttonPanel.add(addBoss);
		bossLabelPanel.add(currentDH);
		
		add(buttonPanel);
		add(bossLabelPanel);
		listsPanel.add(departmentScroller);
		add(listsPanel);
		
		setResizable(false);
		
	}
	
	public void setCurrenDHText(String newText) {
		currentDH.setText(newText);
	}
}
