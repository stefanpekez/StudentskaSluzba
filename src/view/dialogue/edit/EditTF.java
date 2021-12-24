package view.dialogue.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditTF extends JPanel {
	
	private JLabel name;
	private JTextField field;
	
	public EditTF(String name, String preset, EditProfessorInfo infopanel) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 50, 10, 50));
		
		this.name = new JLabel(name);
		
		field = new JTextField(preset,15);
		field.setMaximumSize(new Dimension(1000, 40));
		Border bordergray = BorderFactory.createLineBorder(Color.GRAY, 1);
		field.setBorder(bordergray);
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				infopanel.checkAllFields();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				infopanel.checkAllFields();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				infopanel.checkAllFields();
			}
		});
		
		add(this.name);
		add(Box.createHorizontalGlue());
		add(field);
	}
	
	public JTextField getTextField() {
		return field;
	}
	
	public boolean checkField() {
		if(field.getText().replaceAll("\\W", "").equals("")) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkField(String regex) {
		if(field.getText().replaceAll("\\W", "").equals("")) {
			Border borderred = BorderFactory.createLineBorder(Color.RED, 1);
			field.setBorder(borderred);
			return false;
		}
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(field.getText());
		boolean matchfound = matcher.find();
		
		if(matchfound){
			Border bordergray = BorderFactory.createLineBorder(Color.GRAY, 1);
			field.setBorder(bordergray);
		} else {
			Border borderred = BorderFactory.createLineBorder(Color.RED, 1);
			field.setBorder(borderred);
		}
		return matchfound;
	}
}
