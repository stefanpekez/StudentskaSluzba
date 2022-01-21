package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.AbstractTableModelStudent;
import controller.LanguageController;
import controller.SerializationController;
import model.DBAddress;
import model.DBDepartments;
import model.DBExams;
import model.DBExamsPassed;
import model.DBProfessor;
import model.DBStudent;
import model.DBSubject;
import model.DepartmentHeadSerialization;
import model.PassedGradeRelation;
import model.PassedGradeSerialization;
import model.SubjectProfessorSerialization;
import model.UnpassedSerialization;

public class MainFrame extends JFrame {
	
	private MenuBar menubar;
	private StatusBar statusbar;
	private TabbedPane tp;
	private ToolBar toolbar;

	public MainFrame() {
		super();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension d = kit.getScreenSize();
		
		setSize(3*d.width / 4,3* d.height / 4);
		setLocationRelativeTo(null);
		
		setTitle(LanguageController.getInstance().getResourceBundle().getString("StudentService"));
		
		Image img = kit.getImage("images" + File.separator + "ftn.png");
		setIconImage(img);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Making the StatusBar
		statusbar = new StatusBar();
		add(statusbar, BorderLayout.SOUTH);
		
		tp = new TabbedPane(statusbar);
		add(tp, BorderLayout.CENTER);
		
		//Making the MenuBar
		menubar = new MenuBar(tp, this);
		this.setJMenuBar(menubar);
		
		//Making Toolbar for this frame
		toolbar = new ToolBar(tp);
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
			/*
			DBProfessor.getInstance().serialize();
			DBStudent.getInstance().serialize();
			DBSubject.getInstance().serialize();
			DBDepartments.getInstance().serialize();
			DBAddress.getInstance().serialize();
			UnpassedSerialization.getInstance().serialize();
			PassedGradeSerialization.getInstance().serialize();
			SubjectProfessorSerialization.getInstance().serializeHead();
			SubjectProfessorSerialization.getInstance().serializeTeachers();
			DepartmentHeadSerialization.getInstance().serialize();
			*/
			SerializationController.getInstaince().serialize();
			System.out.println("Serialized!");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong serializing!");
			e1.printStackTrace();
		}
	}
	
	public void initGUI() {
		setTitle(LanguageController.getInstance().getResourceBundle().getString("StudentService"));
		menubar.initComponents();
		statusbar.initComponents();
		toolbar.initComponents();
		tp.initComponents();
		DBStudent.getInstance().initComponents(tp.getStudentTab());
		DBProfessor.getInstance().initComponents(tp.getProfessorTab());
		DBSubject.getInstance().initComponents(tp.getSubjectTab());
		//DBExams.getInstance().initComponents(toolbar.getEDS().getEditStudentTabbedPane().getPassedTab());
	}
}
