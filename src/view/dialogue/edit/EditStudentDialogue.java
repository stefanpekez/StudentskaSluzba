package view.dialogue.edit;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import view.TabbedPane.TablePanel;

public class EditStudentDialogue extends JDialog{
	
	public EditStudentDialogue(Container mainframe, TablePanel tp) {
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		
		setSize(500, 550);
		setLocationRelativeTo(mainframe);
		setTitle("Edit Student");
		setLayout(boxLayout);
		
		EditStudentTabbedPane studentTP = new EditStudentTabbedPane(this, tp);
		add(studentTP);
		
		setResizable(false);
		setVisible(true);
	}

}




















/*
namePanel = new EditTF("Ime*", this);
surnamePanel = new EditTF("Prezime*", this);
dateOfBirthPanel = new EditTF("Datum rodjenja*", this);
homeAddressPanel = new EditTF("Adresa*", this);
phoneNumberPanel = new EditTF("Broj telefona*", this);
emailAddressPanel = new EditTF("E-mail*", this);
indexPanel = new EditTF("Index*", this);
yoePanel = new EditTF("Godina upisa*", this);
cyosPanel = new EditCB("Trenutna godina studija*      ", currentYearOfStudy);
budgetPanel = new EditCB("Nacin finansiranja*                ", financing);

//Button Panel
buttonsPanel = new JPanel();

//accept button
accept = new JButton("ACCEPT");
accept.setEnabled(false);
accept.addActionListener(new ActionListener() { 
	public void actionPerformed(ActionEvent e) { 
		//TODO implement function to add an object of Student class to DBStudent
		StudentController.getInstance().addNewStudent(namePanel, surnamePanel, dateOfBirthPanel, homeAddressPanel, 
				phoneNumberPanel, emailAddressPanel, indexPanel, yoePanel, cyosPanel, budgetPanel);
		tp.updateView();
		exitDialog();
	} 
});

//exit button
JButton exit = new JButton("EXIT");
exit.addActionListener(new ActionListener() { 
	public void actionPerformed(ActionEvent e) { 
		exitDialog();
	} 
});

buttonsPanel.add(accept);
buttonsPanel.add(exit);

add(namePanel);
add(surnamePanel);
add(dateOfBirthPanel);
add(homeAddressPanel);
add(phoneNumberPanel);
add(emailAddressPanel);
add(indexPanel);
add(yoePanel);
add(cyosPanel);
add(budgetPanel);
add(buttonsPanel);
*/
