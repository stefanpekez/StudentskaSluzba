package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

	public MainFrame() {
		super();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		
		setSize(d.width / 2, d.height / 2);
		setLocationRelativeTo(null);
		
		setTitle("Studentska Služba");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Making the Menu Bar
		JMenuBar menubar = new JMenuBar();
		//super.add(menubar, BorderLayout.NORTH);
		
		JMenu file = new JMenu("File");
		menubar.add(file);
		JMenu edit = new JMenu("Edit");
		menubar.add(edit);
		JMenu help = new JMenu("Help");
		menubar.add(help);
		
		this.setJMenuBar(menubar);
		
		//Making Toolbar for this frame
		ToolBar toolbar = new ToolBar();
		add(toolbar, BorderLayout.NORTH);
		setVisible(true);
	}
	
}
