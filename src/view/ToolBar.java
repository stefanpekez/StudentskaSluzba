package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar {
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		JButton btnNew = new JButton();
		btnNew.setToolTipText("New");
		btnNew.setIcon(new ImageIcon("images/upload.png"));
		add(btnNew);
		
		addSeparator();
		
		JButton btnWrite = new JButton();
		btnWrite.setToolTipText("Write");
		btnWrite.setIcon(new ImageIcon("images/edit.png"));
		add(btnWrite);
		
		addSeparator();
		
		JButton btnTrash = new JButton();
		btnTrash.setToolTipText("Delete");
		btnTrash.setIcon(new ImageIcon("images/garbage.png"));
		add(btnTrash);
		
		//addSeparator(new Dimension(620, 0));
		add(Box.createHorizontalGlue());
		
		JTextField txtF = new JTextField("Search...", 15);
		txtF.setMaximumSize(txtF.getPreferredSize());
		add(txtF);
		
		addSeparator();
		
		JButton btnSearch = new JButton();
		btnSearch.setToolTipText("Search");
		btnSearch.setIcon(new ImageIcon("images/magnifying-glass.png"));
		add(btnSearch);
		
		setFloatable(false);	
	}
}
