package view.dialogue.edit;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
	
	public JTextField getTextField() {
		return field;
	}
	
	public boolean checkField() {
		if(field.getText().replaceAll("\\W", "").equals("")) {
			return false;
		}
		
		return true;
	}
}
