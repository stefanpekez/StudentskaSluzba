package view.dialogue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

public class NewTF extends JPanel {

	private JLabel name;
	private JTextField field;
	private NewProfessorDialogue dg;
	private NewStudentDialogue dgs;
	private boolean gainedFocusOnce;
	
	public NewTF(String name, NewProfessorDialogue dialogue, String preset) {

		dg = dialogue;
		this.name = new JLabel(name);
		field = new JTextField(15);
		field.setToolTipText(tooltip);
		
		Border borderred = BorderFactory.createLineBorder(Color.RED, 1);
		Border bordergray = BorderFactory.createLineBorder(Color.GRAY, 1);
		field.setBorder(borderred);
		
		init();
		
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				dg.checkAllFields();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				dg.checkAllFields();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				dg.checkAllFields();
			}
		});
		
		add();
	}
	
	public NewTF(String name, NewStudentDialogue dialogue, String tooltipText) {
		
		dgs = dialogue;
		this.name = new JLabel(name);
		
		field = new JTextField(15);
		field.setToolTipText(tooltipText);
		
		init();
		
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				dgs.checkAllFields();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				dgs.checkAllFields();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				dgs.checkAllFields();
			}
		});
		
		add();
		
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
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 60, 10, 60));
		
		gainedFocusOnce = false;
		
		field.setMaximumSize(new Dimension(1000, 40));
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(!gainedFocusOnce) {
					field.setText("");
					gainedFocusOnce = true;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
			
		});
	}
	
	private void add() {
		add(this.name);
		add(Box.createHorizontalGlue());
		add(field);
	}
}
