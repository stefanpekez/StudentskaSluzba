package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.LanguageController;
import view.dialogue.AboutDialogue;
import view.dialogue.DeleteProfessorDialogue;
import view.dialogue.DeleteStudentDialogue;
import view.dialogue.DeleteSubjectDialogue;
import view.dialogue.DepartmentListDialogue;
import view.dialogue.HelpDialogue;
import view.dialogue.NewProfessorDialogue;
import view.dialogue.NewStudentDialogue;
import view.dialogue.NewSubjectDialogue;
import view.dialogue.edit.EditProfessorDialogue;
import view.dialogue.edit.EditStudentDialogue;
import view.dialogue.edit.EditSubjectDialogue;

public class MenuBar extends JMenuBar {
	
	private TabbedPane tables;
	
	private JMenu file;
	private JMenuItem file_new;
	private JMenuItem file_save;
	private JMenu file_open;
	private JMenuItem students;
	private JMenuItem professors;
	private JMenuItem subjects;
	private JMenuItem departments;
	private JMenuItem file_close;
	private JMenu edit;
	private JMenuItem edit_edit;
	private JMenuItem edit_delete;
	private JMenu help;
	private JMenuItem help_help;
	private JMenuItem help_about;
	private JMenu administration;
	private JMenuItem english;
	private JMenuItem serbian;
	
	public MenuBar(TabbedPane tables, MainFrame parent) {
		
		this.tables = tables;
		
		//File drop-down menu
		file = new JMenu(LanguageController.getInstance().getResourceBundle().getString("FileMenuBar"));
		file.setMnemonic(KeyEvent.VK_F);
		
		//New option
		file_new = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("FileNew"));
		file_new.setIcon(new ImageIcon("images" + File.separator + "new.png"));
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
					new NewSubjectDialogue(getParent(), tables.getSubjectTab());
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		
		//Save option
		file_save = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("FileSave"));
		file_save.setIcon(new ImageIcon("images" + File.separator + "save.png"));
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
		file_open = new JMenu(LanguageController.getInstance().getResourceBundle().getString("FileOpen"));
		file_open.setMnemonic(KeyEvent.VK_O);
		file_open.setIcon(new ImageIcon("images" + File.separator + "open.png"));
		
		//Show student tab
		students = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("StudentsTab"));
		students.setIcon(new ImageIcon("images" + File.separator + "students.png"));
		students.setMnemonic(KeyEvent.VK_1);
		students.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		students.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tables.getStatusBar().getOpenTab().setText(LanguageController.getInstance().getResourceBundle().getString("StudentsTab"));
				tables.setSelectedIndex(0);
			}
			
		});
		
		//Show professor tab
		professors = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("ProfessorsTab"));
		professors.setIcon(new ImageIcon("images" + File.separator + "professors.png"));
		professors.setMnemonic(KeyEvent.VK_2);
		professors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		professors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tables.getStatusBar().getOpenTab().setText(LanguageController.getInstance().getResourceBundle().getString("ProfessorsTab"));
				tables.setSelectedIndex(1);
			}
					
		});
		
		//Show subject tab
		subjects = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("SubjectsTab"));
		subjects.setIcon(new ImageIcon("images" + File.separator + "subjects.png"));
		subjects.setMnemonic(KeyEvent.VK_3);
		subjects.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		subjects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tables.getStatusBar().getOpenTab().setText(LanguageController.getInstance().getResourceBundle().getString("SubjectsTab"));
				tables.setSelectedIndex(2);
			}
			
		});
		
		//Show department tab
		departments = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("DepartmentsTab"));
		departments.setMnemonic(KeyEvent.VK_4);
		departments.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK));
		departments.setIcon(new ImageIcon("images" + File.separator + "departments.png"));
		departments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DepartmentListDialogue(parent).setVisible(true);;
			}
			
		});
		
		file_open.add(students);
		file_open.addSeparator();
		file_open.add(professors);
		file_open.addSeparator();
		file_open.add(subjects);
		file_open.addSeparator();
		file_open.add(departments);
		
		//Close option
		file_close = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("FileClose"));
		file_close.setIcon(new ImageIcon("images" + File.separator + "close.png"));
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
		edit = new JMenu(LanguageController.getInstance().getResourceBundle().getString("Edit"));
		edit.setMnemonic(KeyEvent.VK_E);
		
		//Edit option
		edit_edit = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("Edit"));
		edit_edit.setIcon(new ImageIcon("images" + File.separator + "menubar_edit.png"));
		edit_edit.setMnemonic(KeyEvent.VK_E);
		edit_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		edit_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (tables.getSelectedIndex()) {
				case 0:
					if(tables.getStudentTab().getTable().getSelectedRow() != -1) {
						new EditStudentDialogue(getParent(), tables.getStudentTab());
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("StudentNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				case 1:
					if(tables.getProfessorTab().getTable().getSelectedRow() != -1) {
						new EditProfessorDialogue(getParent(), tables.getProfessorTab());
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("ProfessorNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				case 2:
					if(tables.getSubjectTab().getTable().getSelectedRow() != -1) {
						new EditSubjectDialogue(getParent(), tables.getSubjectTab());
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("SubjectNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		
		//Delete option
		edit_delete = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("Delete"));
		edit_delete.setIcon(new ImageIcon("images" + File.separator + "delete.png"));
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
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("StudentNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					} else {
						new DeleteStudentDialogue(getParent(), tables.getStudentTab());
					}
					break;
				case 1:
					if(tables.getProfessorTab().getTable().getSelectedRow() != -1) {
						new DeleteProfessorDialogue(getParent(), tables.getProfessorTab()).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("ProfessorNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				case 2:
					if(tables.getSubjectTab().getTable().getSelectedRow() != -1) {
						new DeleteSubjectDialogue(getParent(), tables.getSubjectTab()).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("SubjectNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
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
		help = new JMenu(LanguageController.getInstance().getResourceBundle().getString("Help"));
		help.setMnemonic(KeyEvent.VK_H);
		
		//Help option
		help_help = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("Help"));
		help_help.setIcon(new ImageIcon("images" + File.separator + "help.png"));
		help_help.setMnemonic(KeyEvent.VK_H);
		help_help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.SHIFT_MASK));
		help_help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HelpDialogue();
			}
		});
		
		//About option
		help_about = new JMenuItem(LanguageController.getInstance().getResourceBundle().getString("About"));
		help_about.setIcon(new ImageIcon("images" + File.separator + "about.png"));
		help_about.setMnemonic(KeyEvent.VK_A);
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
		help_about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AboutDialogue();
			}
		});
		
		help.add(help_help);
		help.addSeparator();
		help.add(help_about);
		
		//Administration
		administration = new JMenu("Administration");
		english = new JMenuItem("English");
		english.setIcon(new ImageIcon("images/usa.png"));
		english.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale.setDefault(new Locale("en", "US"));
				LanguageController.getInstance().changeLanguage(parent);
			}
		});
		serbian = new JMenuItem("Serbian");
		serbian.setIcon(new ImageIcon("images" + File.separator + "serbia.png"));
		serbian.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Locale.setDefault(new Locale("sr", "RS"));
				LanguageController.getInstance().changeLanguage(parent);
			}
		});
		
		administration.add(english);
		administration.add(serbian);
		
		add(file);
		add(edit);
		add(help);
		add(administration);
	}
	
	public void initComponents() {
		file.setText(LanguageController.getInstance().getResourceBundle().getString("FileMenuBar"));
		file_new.setText(LanguageController.getInstance().getResourceBundle().getString("FileNew"));
		file_save.setText(LanguageController.getInstance().getResourceBundle().getString("FileSave"));
		file_open.setText(LanguageController.getInstance().getResourceBundle().getString("FileOpen"));
		students.setText(LanguageController.getInstance().getResourceBundle().getString("StudentsTab"));
		professors.setText(LanguageController.getInstance().getResourceBundle().getString("ProfessorsTab"));
		subjects.setText(LanguageController.getInstance().getResourceBundle().getString("SubjectsTab"));
		departments.setText(LanguageController.getInstance().getResourceBundle().getString("DepartmentsTab"));
		file_close.setText(LanguageController.getInstance().getResourceBundle().getString("FileClose"));
		edit.setText(LanguageController.getInstance().getResourceBundle().getString("Edit"));
		edit_edit.setText(LanguageController.getInstance().getResourceBundle().getString("Edit"));
		edit_delete.setText(LanguageController.getInstance().getResourceBundle().getString("Delete"));
		help.setText(LanguageController.getInstance().getResourceBundle().getString("Help"));
		help_help.setText(LanguageController.getInstance().getResourceBundle().getString("Help"));
		help_about.setText(LanguageController.getInstance().getResourceBundle().getString("About"));
		administration.setText(LanguageController.getInstance().getResourceBundle().getString("Administration"));
		english.setText(LanguageController.getInstance().getResourceBundle().getString("English"));
		serbian.setText(LanguageController.getInstance().getResourceBundle().getString("Serbian"));
	}
}
