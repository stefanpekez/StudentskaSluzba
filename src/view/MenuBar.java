package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {
	
	public MenuBar() {
		//File drop-down menu
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		//New option
		JMenuItem file_new = new JMenuItem("New");
		file_new.setIcon(new ImageIcon("images/new.png"));
		file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		//Save option
		JMenuItem file_save = new JMenuItem("Save");
		file_save.setIcon(new ImageIcon("images/save.png"));
		file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		//Open menu
		JMenu file_open = new JMenu("Open");
		file_open.setIcon(new ImageIcon("images/open.png"));
		
		//Show student tab
		JMenuItem students = new JMenuItem("Students");
		students.setIcon(new ImageIcon("images/students.png"));
		
		//Show subject tab
		JMenuItem subjects = new JMenuItem("Subjects");
		subjects.setIcon(new ImageIcon("images/subjects.png"));
		
		//Show professor tab
		JMenuItem professors = new JMenuItem("Professors");
		professors.setIcon(new ImageIcon("images/professors.png"));
		
		//Show department tab
		JMenuItem departments = new JMenuItem("Departments");
		departments.setIcon(new ImageIcon("images/departments.png"));
		
		
		file_open.add(students);
		file_open.addSeparator();
		file_open.add(subjects);
		file_open.addSeparator();
		file_open.add(professors);
		file_open.addSeparator();
		file_open.add(departments);
		
		//Close option
		JMenuItem file_close = new JMenuItem("Close");
		file_close.setIcon(new ImageIcon("images/close.png"));
		file_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		file.add(file_new);
		file.addSeparator();
		file.add(file_save);
		file.addSeparator();
		file.add(file_open);
		file.addSeparator();
		file.add(file_close);
		
		//Edit drop-down menu
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		//Edit option
		JMenuItem edit_edit = new JMenuItem("Edit");
		edit_edit.setIcon(new ImageIcon("images/menubar_edit.png"));
		edit_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		//Delete option
		JMenuItem edit_delete = new JMenuItem("Delete");
		edit_delete.setIcon(new ImageIcon("images/delete.png"));
		edit_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		
		edit.add(edit_edit);
		edit.addSeparator();
		edit.add(edit_delete);
		
		//Help drop-down menu
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		//Help option
		JMenuItem help_help = new JMenuItem("Help");
		help_help.setIcon(new ImageIcon("images/help.png"));
		help_help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		
		//About option
		JMenuItem help_about = new JMenuItem("About");
		help_about.setIcon(new ImageIcon("images/about.png"));
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
		
		help.add(help_help);
		help.addSeparator();
		help.add(help_about);
		
		add(file);
		add(edit);
		add(help);
		
	}
}
