package view.dialogue.edit;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;

import controller.AbstractTableModelExams;
import controller.LanguageController;
import controller.StudentController;
import utilities.FormatCheckRegex;
import view.dialogue.NewCB;
import view.dialogue.NewTF;

public class GradeInputDialogue extends JDialog{
	private static final long serialVersionUID = 7675602324397307783L;
	//sifra
	private EditTF id;
	//naziv
	private EditTF name;
	//ocena
	private NewCB grade;
	//datum
	private NewTF date;
	
	private JButton ok;
	private JButton exit;

	public GradeInputDialogue(int selectedExam, int selectedStudent, EditStudentDialogue parent, JTable update, EditStudentPassed updatePassed) {
		setTitle(LanguageController.getInstance().getResourceBundle().getString("NewGradeTitle"));
		
		setSize(400, 250);
		setResizable(false);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(parent);
		
		String[] list = {"6", "7", "8", "9", "10"};
		
		//get id and name
		id = new EditTF(LanguageController.getInstance().getResourceBundle().getString("ForwardGradeId"), StudentController.getInstance().getExamID(selectedExam));
		name = new EditTF(LanguageController.getInstance().getResourceBundle().getString("ForwardGradeName"), StudentController.getInstance().getExamName(selectedExam));
		grade = new NewCB(LanguageController.getInstance().getResourceBundle().getString("ForwardGradeGrade"), list);
		date = new NewTF(LanguageController.getInstance().getResourceBundle().getString("ForwardGradeDate"), this, "dd/mm/yyyy");
		
		ok = new JButton(LanguageController.getInstance().getResourceBundle().getString("OkButton"));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean good = StudentController.getInstance().forwardGrade(selectedExam, selectedStudent, grade.getComboBox().getSelectedIndex(), date.getTextField().getText());
				if(good) {
					System.out.println("Successfull");
					//UPDATE TABLE
					//StudentController.getInstance().listPassedExams(selectedStudent);
				} else {
					System.out.println("Something went wrong");
				}
				
				AbstractTableModelExams examsModel = (AbstractTableModelExams) update.getModel();
				examsModel.fireTableDataChanged();
				validate();
				
				dispose();
			}
		});
		ok.setEnabled(false);
		
		exit = new JButton(LanguageController.getInstance().getResourceBundle().getString("ExitButton"));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		id.getTextField().setEnabled(false);
		name.getTextField().setEnabled(false);
		
		JPanel buttons = new JPanel();
		buttons.add(ok);
		buttons.add(exit);
		
		add(id);
		add(name);
		add(grade);
		add(date);
		add(buttons);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		
	}
	
	public void checkAllFields() {
		if(date.checkField(FormatCheckRegex.DATE_REG)) {
			ok.setEnabled(true);
			return;
		}
		
		ok.setEnabled(false);
		return;
	}
	

}
 