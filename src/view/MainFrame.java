package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.LanguageController;
import model.DBAddress;
import model.DBDepartments;
import model.DBProfessor;
import model.DBStudent;
import model.DBSubject;

public class MainFrame extends JFrame {

	public MainFrame() {
		super();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		
		setSize(3*d.width / 4,3* d.height / 4);
		setLocationRelativeTo(null);
		
		setTitle(LanguageController.getInstance().getResourceBundle().getString("StudentService"));
		
		Image img = kit.getImage("images/ftn.png");
		setIconImage(img);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Making the StatusBar
		StatusBar statusbar = new StatusBar();
		add(statusbar, BorderLayout.SOUTH);
		
		TabbedPane tp = new TabbedPane(statusbar);
		add(tp, BorderLayout.CENTER);
		
		//Making the MenuBar
		MenuBar menubar = new MenuBar(tp, this);
		this.setJMenuBar(menubar);
		
		//Making Toolbar for this frame
		ToolBar toolbar = new ToolBar(tp);
		add(toolbar, BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				serialize();
			}
		});
		
		setVisible(true);
	}
	
	public void serialize() {
		try {
			DBProfessor.getInstance().serialize();
			DBStudent.getInstance().serialize();
			DBSubject.getInstance().serialize();
			DBDepartments.getInstance().serialize();
			DBAddress.getInstance().serialize();
			System.out.println("Serialized!");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong serializing!");
			e1.printStackTrace();
		}
	}
}
