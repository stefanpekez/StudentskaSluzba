package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.LanguageController;
import controller.ProfessorController;
import controller.StudentController;
import controller.SubjectController;
import view.dialogue.DeleteProfessorDialogue;
import view.dialogue.DeleteSubjectDialogue;
import view.dialogue.DeleteStudentDialogue;
import view.dialogue.NewProfessorDialogue;
import view.dialogue.NewStudentDialogue;
import view.dialogue.NewSubjectDialogue;
import view.dialogue.edit.EditProfessorDialogue;
import view.dialogue.edit.EditStudentDialogue;
import view.dialogue.edit.EditSubjectDialogue;

public class ToolBar extends JToolBar {
	
	private JButton btnNew;
	private JButton btnWrite;
	private JButton btnTrash;
	private JButton btnSearch;
	private JTextField txtF;
	private TabbedPane tables;
	
	private EditStudentDialogue eds;
	
	private boolean searchClickedOnce = false;
	
	public ToolBar(TabbedPane tables) {
		super(SwingConstants.HORIZONTAL);
		
		this.tables = tables;
		
		btnNew = new JButton();
		btnNew.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("FileNew"));
		btnNew.setIcon(new ImageIcon("images" + File.separator + "upload.png"));
		btnNew.addActionListener(new ActionListener() {

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
		btnNew.setMnemonic(KeyEvent.VK_F1);
		add(btnNew);
		
		addSeparator();
		
		btnWrite = new JButton();
		btnWrite.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("Edit"));
		btnWrite.setIcon(new ImageIcon("images" + File.separator + "edit.png"));
		btnWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (tables.getSelectedIndex()) {
				case 0: 
					if(tables.getStudentTab().getTable().getSelectedRow() != -1)
						eds = new EditStudentDialogue(getParent(), tables.getStudentTab());
					else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("StudentNotSelectedEdit"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
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
					if(tables.getSubjectTab().getTable().getSelectedRow() != -1)
						new EditSubjectDialogue(getParent(), tables.getSubjectTab());
					else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("EditSubjectMessage"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		btnWrite.setMnemonic(KeyEvent.VK_F2);
		add(btnWrite);
		
		addSeparator();
		
		btnTrash = new JButton();
		btnTrash.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("Delete"));
		btnTrash.setIcon(new ImageIcon("images" + File.separator + "garbage.png"));
		btnTrash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Brisanje");
				switch (tables.getSelectedIndex()) {
				case 0: 
					if(tables.getStudentTab().getTable().getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("DeleteStudentNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					} else {
						DeleteStudentDialogue deleteStudent = new DeleteStudentDialogue(getParent(), tables.getStudentTab());
					}
					break;
				case 1:
					if(tables.getProfessorTab().getTable().getSelectedRow() != -1) {
						new DeleteProfessorDialogue(getParent(), tables.getProfessorTab()).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("DeleteProfessorNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				case 2:
					if(tables.getSubjectTab().getTable().getSelectedRow() != -1) {
						new DeleteSubjectDialogue(getParent(), tables.getSubjectTab()).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(getParent(), LanguageController.getInstance().getResourceBundle().getString("DeleteSubjectNotSelected"), LanguageController.getInstance().getResourceBundle().getString("ErrorMessageTitle"), 0);
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + tables.getSelectedIndex());
				}
			}
			
		});
		btnTrash.setMnemonic(KeyEvent.VK_F3);
		add(btnTrash);
		
		add(Box.createHorizontalGlue());
		
		txtF = new JTextField(LanguageController.getInstance().getResourceBundle().getString("SearchPlaceholder"), 15);
		txtF.setMaximumSize(new Dimension(0, 25));
		txtF.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Kliknut search bar.");
				if(!searchClickedOnce) {
					txtF.setText("");
					searchClickedOnce = true;
				}
			}
		});
		add(txtF);
		
		btnSearch = new JButton();
		btnSearch.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("SearchToolTip"));
		btnSearch.setIcon(new ImageIcon("images" + File.separator + "magnifying-glass.png"));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch (tables.getSelectedIndex()) {
				case 0:
					System.out.println("Student search");
					StudentController.getInstance().searchStudent(txtF.getText());
					tables.getStudentTab().updateView();
					break;
				case 1:
					System.out.println("Professor search");
					//call controller and send him the text in search field
					ProfessorController.getInstance().searchProfessor(txtF.getText());
					tables.getProfessorTab().updateView();
					break;
				case 2:
					System.out.println("Subject search");
					SubjectController.getInstance().searchSubject(txtF.getText());
					tables.getSubjectTab().updateView();
					break;
				default:
					break;
				}
			}
			
		});
		add(btnSearch);
		
		setFloatable(false);	
		
	}
	
	public void initComponents() {
		btnNew.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("FileNew"));
		btnWrite.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("Edit"));
		btnTrash.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("Delete"));
		btnSearch.setToolTipText(LanguageController.getInstance().getResourceBundle().getString("SearchToolTip"));
		txtF.setText(LanguageController.getInstance().getResourceBundle().getString("SearchPlaceholder"));
		//administration
	}
	
	public EditStudentDialogue getEDS() {
		return this.eds;
	}
	
}
