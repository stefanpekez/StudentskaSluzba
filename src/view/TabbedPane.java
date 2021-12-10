package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class TabbedPane extends JTabbedPane {
	
	public TabbedPane() {
		setBorder(new EmptyBorder(20, 50, 20, 50));
		addTab("Studenti", new TablePanel("Tabela studenata"));
		addTab("Profesori", new TablePanel("Tabela profesora"));
		addTab("Predmeti", new TablePanel("Tabela predmeta"));
	}
	
	public class TablePanel extends JPanel {
		private JLabel tableName;
		private JTable table;
		
		public TablePanel(String name) {
			tableName = new JLabel(name);
			table = new JTable();
			
			add(tableName);
		}
	}
}
