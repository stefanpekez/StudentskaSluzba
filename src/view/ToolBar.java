package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		btnNew = new JButton();
		btnNew.setToolTipText("New");
		btnNew.setIcon(new ImageIcon("images/upload.png"));
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
