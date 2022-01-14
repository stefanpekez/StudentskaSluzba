package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.ProfessorController;
import controller.SubjectController;
import view.dialogue.DeleteProfessorDialogue;
import view.dialogue.DeleteSubjectDialogue;
import view.dialogue.DeleteStudentDialogue;
import view.dialogue.NewProfessorDialogue;
import view.dialogue.NewStudentDialogue;
import view.dialogue.edit.EditProfessorDialogue;
import view.dialogue.edit.EditStudentDialogue;

public class ToolBar extends JToolBar {
	
	private JButton btnNew;
	private JButton btnWrite;
	private JButton btnTrash;
	private JButton btnSearch;
	private JTextField txtF;
	private TabbedPane tables;
	
	private boolean searchClickedOnce = false;
	
	public ToolBar(TabbedPane tables) {
		super(SwingConstants.HORIZONTAL);
		
		this.tables = tables;
		
		btnNew = new JButton();
		btnNew.setToolTipText("New");
		btnNew.setIcon(new ImageIcon("images/upload.png"));
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
		btnWrite.setToolTipText("Write");
		btnWrite.setIcon(new ImageIcon("images/edit.png"));
		btnWrite.addActionListener(new ActionListener() {

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
		btnWrite.setMnemonic(KeyEvent.VK_F2);
		add(btnWrite);
		
		addSeparator();
		
		btnTrash = new JButton();
		btnTrash.setToolTipText("Delete");
		btnTrash.setIcon(new ImageIcon("images/garbage.png"));
		btnTrash.addActionListener(new ActionListener() {

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
		btnTrash.setMnemonic(KeyEvent.VK_F3);
		add(btnTrash);
		
		add(Box.createHorizontalGlue());
		
		txtF = new JTextField("Search...", 15);
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
		btnSearch.setToolTipText("Search");
		btnSearch.setIcon(new ImageIcon("images/magnifying-glass.png"));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch (tables.getSelectedIndex()) {
				case 0:
					System.out.println("Student search");
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
	
}
