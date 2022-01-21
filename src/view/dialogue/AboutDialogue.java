package view.dialogue;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class AboutDialogue extends JDialog{
	
	public AboutDialogue() {
		
		setSize(500, 200);
		setLocationRelativeTo(getParent());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("About");
		
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		text.setText("Program radili:"
				+ "\n\tFilip Milošević i Stefan Pekez"
				+ "\n"
				+ "\nStefan Pekez je rođen 2000. godine. Završio Gimnaziju \"Svetozar Marković\" u Novom Sadu i trenutno "
				+ "studira Računarstvo i Automatiku na FTN-u."
				+ "\n"
				+ "\nFilip Milošević je rođen 2001. godine. Završio je Gimnaziju \"Vuk Karadžić\" u Loznici i "
				+ "trenutno studira Računarstvo i Automatiku na FTN-u.");
		
		add(text);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
	}
}
