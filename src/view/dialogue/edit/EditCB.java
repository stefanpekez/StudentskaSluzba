package view.dialogue.edit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditCB extends JPanel {
	
	private JLabel name;
	private JComboBox cb;
	
	public EditCB(String name, String[] list, String selectedOption, String temp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 50, 10, 50));
		
		this.name = new JLabel(name);
		add(this.name);
		
		add(Box.createHorizontalGlue());
		
		cb = new JComboBox<>(list);
		
		if(temp.equals("cyos")) {
			switch(selectedOption) {
				case "1":
					cb.setSelectedIndex(0);
					break;
				case "2":
					cb.setSelectedIndex(1);
					break;
				case "3":
					cb.setSelectedIndex(2);
					break;
				case "4":
					cb.setSelectedIndex(3);
					break;
			}
		} else if(temp.equals("b")){
			switch(selectedOption) {
			case "0":
				cb.setSelectedIndex(0);
				break;
			case "1":
				cb.setSelectedIndex(1);
				break;
			}
		} else if(temp.equals("year")) {
			switch(selectedOption) {
			case "1":
				cb.setSelectedIndex(0);
				break;
			case "2":
				cb.setSelectedIndex(1);
				break;
			case "3":
				cb.setSelectedIndex(2);
				break;
			case "4":
				cb.setSelectedIndex(3);
				break;
			}
		} else {
			switch(selectedOption) {
			case "ZIMSKI":
				cb.setSelectedIndex(0);
				break;
			case "LETNJI":
				cb.setSelectedIndex(1);
				break;
			}
		}
		
		add(cb);
	}
	
	public JComboBox getComboBox() {
		return this.cb;
	}

}
