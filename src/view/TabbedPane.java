package view;

import java.awt.BorderLayout;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

enum TypeOfTab{
	STUDENT,
	PROFESSOR,
	SUBJECT;
}

public class TabbedPane extends JTabbedPane {
	
	private TablePanel tabStudent;
	private TablePanel tabProfessor;
	private TablePanel tabSubject;
	private StatusBar statusbar;
	
	public TabbedPane(StatusBar statusbar) {
		this.statusbar = statusbar;
		
		setBorder(new EmptyBorder(20, 50, 20, 50));
		
		
		addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
            	   switch(getSelectedIndex()) {
               	case 0:
               		statusbar.getOpenTab().setText("Student");
             
               		break;
               	case 1:
               		statusbar.getOpenTab().setText("Professor");
               		break;
               	case 2:
               		statusbar.getOpenTab().setText("Subject");
               		break;
               }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });
		
		tabStudent = new TablePanel(TypeOfTab.STUDENT);
		addTab("Students", tabStudent);
		
		tabProfessor = new TablePanel(TypeOfTab.PROFESSOR);
		addTab("Professors", tabProfessor);
		
		tabSubject = new TablePanel(TypeOfTab.SUBJECT);
		addTab("Subjects", tabSubject);
	}
	
	public TablePanel getStudentTab() {
		return tabStudent;
	}
	
	public TablePanel getProfessorTab() {
		return tabProfessor;
	}
	
	public TablePanel getSubjectTab() {
		return tabSubject;
	}
	
	public class TablePanel extends JPanel {
		private static final long serialVersionUID = 2825309443790400810L;
		
		private JTable table;
		
		
		public TablePanel(TypeOfTab type) {
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			

			switch (type) {
			case SUBJECT:
				table = new SubjectTable();
				add(new JScrollPane(table), BorderLayout.CENTER);
				break;
			case PROFESSOR:
				//TODO add professor table
				table = new ProfessorTable();
				add(new JScrollPane(table), BorderLayout.CENTER);
				break;
			case STUDENT:
				//TODO add student table
				table = new StudentTable();
				add(new JScrollPane(table), BorderLayout.CENTER);
				break;
			default:
				System.out.println("Something Went Wrong");
			}
			
		}
		public void updateView() {
			//TODO update view when on table changed
			System.out.println("Niggas");
		}
		
	}
}
