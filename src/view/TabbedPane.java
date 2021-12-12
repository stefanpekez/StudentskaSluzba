package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private StatusBar statusbar;
	
	public TabbedPane(StatusBar statusbar) {
		this.statusbar = statusbar;
		
		setBorder(new EmptyBorder(20, 50, 20, 50));
		
		tabStudent = new TablePanel(TypeOfTab.STUDENT);
		addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
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
		addTab("Studenti", tabStudent);
		
		tabProfessor = new TablePanel(TypeOfTab.PROFESSOR);
		addTab("Profesori", tabProfessor);
		
		tabSubject = new TablePanel(TypeOfTab.SUBJECT);
		addTab("Predmeti", tabSubject);
	}
	
	public class TablePanel extends JPanel {
		private static final long serialVersionUID = 2825309443790400810L;
		
		private JLabel tableName;
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
		}
		
	}
}
