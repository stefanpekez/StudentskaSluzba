package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar {
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		JButton btnNew = new JButton();
		btnNew.setToolTipText("New");
		btnNew.setIcon(new ImageIcon("images/addiconresize.png"));
		add(btnNew);
		
		addSeparator();
		
		JButton btnWrite = new JButton();
		btnWrite.setToolTipText("Write");
		btnWrite.setIcon(new ImageIcon("images/writeiconresize.png"));
		add(btnWrite);
		
		addSeparator();
		
		JButton btnTrash = new JButton();
		btnTrash.setToolTipText("Delete");
		btnTrash.setIcon(new ImageIcon("images/trashiconresize.png"));
		add(btnTrash);
		
		setFloatable(false);
		
	}
}
