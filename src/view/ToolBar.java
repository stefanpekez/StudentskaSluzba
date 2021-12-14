package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import view.dialogue.DeleteProfessorDialogue;
import view.dialogue.DeleteSubjectDialogue;
import view.dialogue.NewProfessorDialogue;
import view.dialogue.NewStudentDialogue;
import view.dialogue.edit.EditProfessorDialogue;

public class ToolBar extends JToolBar {
	
	private JButton btnNew;
	private JButton btnWrite;
	private JButton btnTrash;
	private JButton btnSearch;
	private JTextField txtF;
	private TabbedPane tables;
	
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
					NewStudentDialogue newStudent = new NewStudentDialogue(getParent(), tables.getStudentTab());
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
				txtF.setText("");
			}
		});
		add(txtF);
		
		btnSearch = new JButton();
		btnSearch.setToolTipText("Search");
		btnSearch.setIcon(new ImageIcon("images/magnifying-glass.png"));
		btnSearch.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Kliknuto search dugme");
				//funkcija za pretragu
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		add(btnSearch);
		
		setFloatable(false);	
		
	}
	
}
