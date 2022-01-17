package view.dialogue;

import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NewCB extends JPanel {
	
	private JLabel name;
	private JComboBox cb;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public NewCB(String name, String[] list) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 60, 10, 60));
		
		this.name = new JLabel(name);
		add(this.name);
		
		add(Box.createHorizontalGlue());
		
		cb = new JComboBox<>(list);
		cb.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
		add(cb);
		
		
		//setVisible(true);
	}
	
	/*public NewCB(String name, String[] list, String subject) {
		setLayout(new GridBagLayout());
		setBorder(new EmptyBorder(10, 60, 10, 60));
		
		this.name = new JLabel(name);
		add(this.name);
		
		add(Box.createHorizontalGlue());
		
		cb = new JComboBox<>(list);
		cb.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXX");
		add(cb);
		
		
		//setVisible(true);
	}*/
	
	
	public JComboBox getComboBox() {
		return this.cb;
	}

}
