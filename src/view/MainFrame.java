package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

	public MainFrame() {
		super();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		
		setSize(3*d.width / 4,3* d.height / 4);
		setLocationRelativeTo(null);
		
		setTitle("Studentska Služba");
		
		Image img = kit.getImage("images/ftn.png");
		setIconImage(img);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Making the StatusBar
		StatusBar statusbar = new StatusBar();
		add(statusbar, BorderLayout.SOUTH);
		
		TabbedPane tp = new TabbedPane(statusbar);
		add(tp, BorderLayout.CENTER);
		
		//Making the MenuBar
		MenuBar menubar = new MenuBar(tp);
		this.setJMenuBar(menubar);
		
		//Making Toolbar for this frame
		ToolBar toolbar = new ToolBar(tp);
		add(toolbar, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
}
