package view.dialogue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class HelpDialogue extends JDialog {
	
	public HelpDialogue() {
		
		setSize(500, 350);
		setLocationRelativeTo(getParent());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Help");
		
		JEditorPane text = new JEditorPane();
		text.setEditable(false);
		
		text.setText("Mnemonics and accelerators:"
				+ "\n--------------------------------------------------------------------------------------------"
				+ "\n\tFile Menu: ALT-F"
				+ "\n\t\tNew: ALT-N | (ctrl+n)"
				+ "\n\t\tSave: ALT-S | (ctrl+s)"
				+ "\n\t\tOpen Menu: ALT-O"
				+ "\n\t\t\tStudents: ALT-1 | (ctrl+1)"
				+ "\n\t\t\tProfessors: ALT-2 | (ctrl+2)"
				+ "\n\t\t\tSubjects: ALT-3 | (ctrl+3)"
				+ "\n\t\t\tDepartments: ALT-4 | (ctrl+4)"
				+ "\n\t\tClose: ALT-C | (ctrl+c)"
				+ "\n--------------------------------------------------------------------------------------------"
				+ "\n\tEdit Menu: ALT-E"
				+ "\n\t\tEdit: ALT-E | (ctrl+e)"
				+ "\n\t\tDelete: ALT-D | (ctrl+d)"
				+ "\n--------------------------------------------------------------------------------------------"
				+ "\n\tHelp Menu: ALT-H"
				+ "\n\t\tHelp: ALT-H | (shift+h)"
				+ "\n\t\tAbout: ALT-A | (shift+a)"
				+ "\n--------------------------------------------------------------------------------------------");
		
		add(text);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
	}

}
