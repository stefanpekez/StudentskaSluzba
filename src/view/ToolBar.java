package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar {
	
	JButton btnNew;
	JButton btnWrite;
	JButton btnTrash;
	JButton btnSearch;
	JTextField txtF;
	TabbedPane tables;
	
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
					break;
				case 1:
					System.out.println("Professor");
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
		add(btnWrite);
		
		addSeparator();
		
		btnTrash = new JButton();
		btnTrash.setToolTipText("Delete");
		btnTrash.setIcon(new ImageIcon("images/garbage.png"));
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
