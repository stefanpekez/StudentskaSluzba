package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import controller.AbstractTableModelProfessor;
import controller.AbstractTableModelStudent;
import controller.AbstractTableModelSubject;

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
		private TypeOfTab type;
		
		
		public TablePanel(TypeOfTab type) {
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.type = type;
			

			switch (type) {
			case SUBJECT:
				table = new SubjectTable();
				add(new JScrollPane(table), BorderLayout.CENTER);
				TableRowSorter<AbstractTableModelSubject> sortersub = new TableRowSorter(table.getModel());
				
				//sorting for id column
				sortersub.setComparator(0, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						String o1Letters = o1.replaceAll("[0-9]+", "");
						String o2Letters = o2.replaceAll("[0-9]+", "");
						
						String o1Numbers = o1.replaceAll("[a-z]+", "");
						String o2Numbers = o2.replaceAll("[a-z]+", "");
						
						
						if(!o1Letters.equals(o2Letters)) {
							return o1Letters.compareTo(o2Letters);
						} else {
							int num1 = Integer.parseInt(o1Numbers);
							int num2 = Integer.parseInt(o2Numbers);
							return Integer.compare(num1, num2);
						}
						
					}
				});
				
				//sorting for espb column
				sortersub.setComparator(2, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						int num1 = Integer.parseInt(o1);
						int num2 = Integer.parseInt(o2);
						
						return Integer.compare(num1, num2);
					}
					
				});
				
				table.setRowSorter(sortersub);
				break;
			case PROFESSOR:
				//TODO add professor table
				table = new ProfessorTable();
				add(new JScrollPane(table), BorderLayout.CENTER);
				TableRowSorter<AbstractTableModelProfessor> sorterprof = new TableRowSorter(table.getModel());
				table.setRowSorter(sorterprof);
				break;
			case STUDENT:
				//TODO add student table
				table = new StudentTable();
				add(new JScrollPane(table), BorderLayout.CENTER);
				TableRowSorter<AbstractTableModelStudent> sorterStudent = new TableRowSorter(table.getModel());
				
				sorterStudent.setComparator(0, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						String letters1 = o1.replaceAll("\\W[0-9]+", "");
						String letters2 = o2.replaceAll("\\W[0-9]+", "");
						
						String ordinal1 = o1.replaceAll("[A-Z][A-Z]\\W", "").replaceAll("/", "");
						String ordinal2 = o2.replaceAll("[A-Z][A-Z]\\W", "").replaceAll("/", "");
						
						if(!letters1.equals(letters2)) {
							return letters1.compareTo(letters2);
						} else {
							int ordinal1_int = Integer.parseInt(ordinal1);
							int ordinal2_int = Integer.parseInt(ordinal2);
							return Integer.compare(ordinal1_int, ordinal2_int);
						}
						
					}
				});
				
				sorterStudent.setComparator(5, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						double avg1 = Double.parseDouble(o1);
						double avg2 = Double.parseDouble(o2);
						return Double.compare(avg1, avg2);
					}
				});
				
				
				table.setRowSorter(sorterStudent);
				break;
			default:
				System.out.println("Something Went Wrong");
			}
			
		}
		
		public JTable getTable() {
			return table;
		}
		
		public void updateView() {
			if(type == TypeOfTab.PROFESSOR) {
				AbstractTableModelProfessor model = (AbstractTableModelProfessor) table.getModel();
				model.fireTableDataChanged();
				validate();
			} else if(type == TypeOfTab.STUDENT) {
				AbstractTableModelStudent studentModel = (AbstractTableModelStudent) table.getModel();
				studentModel.fireTableDataChanged();
				validate();
			} else {
				AbstractTableModelSubject subjectModel = (AbstractTableModelSubject) table.getModel();
				subjectModel.fireTableDataChanged();
				validate();
			}
		}
		
	}

	public StatusBar getStatusBar() {
		return this.statusbar;
	}
	
}
