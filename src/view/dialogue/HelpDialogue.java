package view.dialogue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpDialogue extends JDialog {
	
	private JScrollPane scrollPane = new JScrollPane();
	
	public HelpDialogue() {
		
		setSize(500, 500);
		setLocationRelativeTo(getParent());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Help");
		
		JPanel panelMnemonics = new JPanel();
		JLabel mnemonics = new JLabel("Mnemonics:");
		
		panelMnemonics.add(mnemonics);
		add(panelMnemonics);
		
		JPanel panelFile = new JPanel();
		JLabel file  = new JLabel("File menu:");
		//file.setWrapStyleWord(true);
		//file.setLineWrap(true);
		panelFile.add(file);
		add(panelFile);
		
		//scrollPane.add(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//add(functionality8_1);
		//add(scrollPane);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
	}

}
