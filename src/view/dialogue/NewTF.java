package view.dialogue;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewTF extends JPanel {

	private JLabel name;
	private JTextField field;
	private NewProfessorDialogue dg;
	private boolean gainedFocusOnce;
	
	public NewTF(String name, NewProfessorDialogue dialogue, String preset) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new EmptyBorder(10, 60, 10, 60));
		
		dg = dialogue;
		gainedFocusOnce = false;
		
		this.name = new JLabel(name);
		field = new JTextField(preset,15);
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
		field.setSize(20, 20);
		
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
