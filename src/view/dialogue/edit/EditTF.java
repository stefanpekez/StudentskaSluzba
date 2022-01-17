package view.dialogue.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	
	public EditTF(String name, EditStudentInfo editSI, String preset) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 50, 10, 50));
		
		this.name = new JLabel(name);
		
		field = new JTextField(preset, 15);
		field.setMaximumSize(new Dimension(1000, 40));
		
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				editSI.checkAllFields();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				editSI.checkAllFields();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				editSI.checkAllFields();
			}
		});
		
		add(this.name);
		add(Box.createHorizontalGlue());
		add(field);
		
	}
	
	public EditTF(String name, EditSubjectDialogue editSI, String preset) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 50, 10, 50));
		
		this.name = new JLabel(name);
		
		field = new JTextField(preset, 15);
		field.setMaximumSize(new Dimension(1000, 40));
		
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				//editSI.checkAllFields();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				//editSI.checkAllFields();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				//editSI.checkAllFields();
			}
		});
		
		add(this.name);
		add(Box.createHorizontalGlue());
		add(field);
		
	}
	
	public EditTF(String name, String preset) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 60, 10, 60));
		
		this.name = new JLabel(name);
		
		field = new JTextField(preset,15);
		field.setMaximumSize(new Dimension(1000, 40));
		
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
			name.setForeground(Color.red);
			return false;
		}
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(field.getText());
		boolean matchfound = matcher.find();
		
		if(matchfound){
			name.setForeground(Color.black);
		} else {
			name.setForeground(Color.red);
		}
		return matchfound;
	}
}
