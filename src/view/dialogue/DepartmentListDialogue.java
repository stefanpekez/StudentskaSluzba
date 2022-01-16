package view.dialogue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.DBDepartments;
import view.MainFrame;

public class DepartmentListDialogue extends JDialog {

	private static final long serialVersionUID = -8392889665106099265L;

	private JList<String> departments;
	private JButton addBoss;
	
	public DepartmentListDialogue(MainFrame parent) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(500, 250);
		setLocationRelativeTo(parent);
		setTitle("Departments");
		
		JPanel buttonz = new JPanel();
		
		addBoss = new JButton("ADD BOSS");
		buttonz.add(addBoss);
		
		
		departments = new JList<String>(DBDepartments.getInstance().getList());
		departments.setBorder(new EmptyBorder(10, 10, 10 ,10));
		
		JScrollPane departmentScroller = new JScrollPane(departments); 
		departmentScroller.setBorder(new EmptyBorder(10,10,10,10));
		
		add(buttonz);
		add(departmentScroller);
		
	}
}
