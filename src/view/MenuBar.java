package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import view.dialogue.DeleteProfessorDialogue;
import view.dialogue.DeleteStudentDialogue;
import view.dialogue.DeleteSubjectDialogue;
import view.dialogue.NewProfessorDialogue;
import view.dialogue.NewStudentDialogue;
import view.dialogue.edit.EditProfessorDialogue;
import view.dialogue.edit.EditStudentDialogue;

public class MenuBar extends JMenuBar {
	
	private TabbedPane tables;
	
	public MenuBar(TabbedPane tables, MainFrame parent) {
		
		this.tables = tables;
		
		//File drop-down menu
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		//New option
		JMenuItem file_new = new JMenuItem("New");
		file_new.setIcon(new ImageIcon("images/new.png"));
		file_new.setMnemonic(KeyEvent.VK_N);
		file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		file_new.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (tables.getSelectedIndex()) {
				case 0: 
					System.out.println("Student");
					new NewStudentDialogue(getParent(), tables.getStudentTab());
					break;
				case 1:
					System.out.println("Professor");
					//open dialogue
					new NewProfessorDialogue(getParent(), tables.getProfessorTab()).setVisible(true);;
					break;
				case 2:
					System.out.println("Subject");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		
		//Save option
		JMenuItem file_save = new JMenuItem("Save");
		file_save.setIcon(new ImageIcon("images/save.png"));
		file_save.setMnemonic(KeyEvent.VK_S);
		file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		file_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parent.serialize();
			}
			
		});
		
		//Open menu
		JMenu file_open = new JMenu("Open");
		file_open.setMnemonic(KeyEvent.VK_O);
		file_open.setIcon(new ImageIcon("images/open.png"));
		
		//Show student tab
		JMenuItem students = new JMenuItem("Students");
		students.setIcon(new ImageIcon("images/students.png"));
		students.setMnemonic(KeyEvent.VK_1);
		students.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		students.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tables.getStatusBar().getOpenTab().setText("Student");
				tables.setSelectedIndex(0);
			}
			
		});
		
		//Show professor tab
		JMenuItem professors = new JMenuItem("Professors");
		professors.setIcon(new ImageIcon("images/professors.png"));
		professors.setMnemonic(KeyEvent.VK_2);
		professors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		professors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tables.getStatusBar().getOpenTab().setText("Professor");
				tables.setSelectedIndex(1);
			}
					
		});
		
		//Show subject tab
		JMenuItem subjects = new JMenuItem("Subjects");
		subjects.setIcon(new ImageIcon("images/subjects.png"));
		subjects.setMnemonic(KeyEvent.VK_3);
		subjects.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		subjects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tables.getStatusBar().getOpenTab().setText("Subject");
				tables.setSelectedIndex(2);
			}
			
		});
		
		//Show department tab
		JMenuItem departments = new JMenuItem("Departments");
		departments.setMnemonic(KeyEvent.VK_4);
		departments.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK));
		departments.setIcon(new ImageIcon("images/departments.png"));
		
		file_open.add(students);
		file_open.addSeparator();
		file_open.add(professors);
		file_open.addSeparator();
		file_open.add(subjects);
		file_open.addSeparator();
		file_open.add(departments);
		
		//Close option
		JMenuItem file_close = new JMenuItem("Close");
		file_close.setIcon(new ImageIcon("images/close.png"));
		file_close.setMnemonic(KeyEvent.VK_C);
		file_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		file_close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
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
		edit_edit.setMnemonic(KeyEvent.VK_E);
		edit_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		edit_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (tables.getSelectedIndex()) {
				case 0: 
					new EditStudentDialogue(getParent(), tables.getStudentTab());
					break;
				case 1:
					if(tables.getProfessorTab().getTable().getSelectedRow() != -1) {
						new EditProfessorDialogue(getParent(), tables.getProfessorTab());
					} else {
						System.out.println("Please select a row to edit");
					}
					break;
				case 2:
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		
		//Delete option
		JMenuItem edit_delete = new JMenuItem("Delete");
		edit_delete.setIcon(new ImageIcon("images/delete.png"));
		edit_delete.setMnemonic(KeyEvent.VK_D);
		edit_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		
		edit_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Brisanje");
				switch (tables.getSelectedIndex()) {
				case 0: 
					if(tables.getStudentTab().getTable().getSelectedRow() == -1) {
						System.out.println("Select a student");
					} else {
						DeleteStudentDialogue deleteStudent = new DeleteStudentDialogue(getParent(), tables.getStudentTab());
					}
					break;
				case 1:
					if(tables.getProfessorTab().getTable().getSelectedRow() != -1) {
						new DeleteProfessorDialogue(getParent(), tables.getProfessorTab()).setVisible(true);
					} else {
						System.out.println("Please select a row to delete");
					}
					break;
				case 2:
					if(tables.getSubjectTab().getTable().getSelectedRow() != -1) {
						new DeleteSubjectDialogue(getParent(), tables.getSubjectTab()).setVisible(true);
					} else {
						System.out.println("Please select a row to delete");
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		
		edit.add(edit_edit);
		edit.addSeparator();
		edit.add(edit_delete);
		
		//Help drop-down menu
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		//Help option
		JMenuItem help_help = new JMenuItem("Help");
		help_help.setIcon(new ImageIcon("images/help.png"));
		help_help.setMnemonic(KeyEvent.VK_H);
		help_help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		
		//About option
		JMenuItem help_about = new JMenuItem("About");
		help_about.setIcon(new ImageIcon("images/about.png"));
		help_about.setMnemonic(KeyEvent.VK_A);
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
		
		help.add(help_help);
		help.addSeparator();
		help.add(help_about);
		
		add(file);
		add(edit);
		add(help);
		
	}
}
