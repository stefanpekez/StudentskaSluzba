package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
	
	
	public TabbedPane() {
		
		setBorder(new EmptyBorder(20, 50, 20, 50));
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("kliknut " + getSelectedIndex());
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		tabStudent = new TablePanel("Tabela studenata", TypeOfTab.STUDENT);
		addTab("Students", tabStudent);
		
		tabProfessor = new TablePanel("Tabela profesora", TypeOfTab.PROFESSOR);
		addTab("Professors", tabProfessor);
		
		tabSubject = new TablePanel("Tabela predmeta", TypeOfTab.SUBJECT);
		addTab("Subjects", tabSubject);
	}
	
	public class TablePanel extends JPanel {
		private static final long serialVersionUID = 2825309443790400810L;
		
		private JLabel tableName;
		private JTable table;
		
		public TablePanel(String name, TypeOfTab type) {
			tableName = new JLabel(name);
			
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			
			add(tableName);

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
				break;
			default:
				System.out.println("Something Went Wrong");
			}
			
		}
		
		public void updateView() {
			//TODO update view when on table changed
		}
		
	}
}
