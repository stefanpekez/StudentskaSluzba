package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DynamicClock;
import controller.LanguageController;

public class StatusBar extends JPanel {


	private JLabel opentab;
	private JLabel StudentskaSluzba;
	private JLabel timeLabel;
	private JLabel dateLabel;
	private DateFormat dateFormat;
	private Date date;

	public StatusBar() {
		opentab = new JLabel(LanguageController.getInstance().getResourceBundle().getString("StudentsTab"));
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		StudentskaSluzba = new JLabel("   " + LanguageController.getInstance().getResourceBundle().getString("StatusBarStudentService"));
		add(StudentskaSluzba);
		add(opentab);
		
		add(Box.createHorizontalGlue());
		
		timeLabel = new JLabel();
		DynamicClock dc = new DynamicClock(timeLabel);
		
		dateFormat = new SimpleDateFormat(LanguageController.getInstance().getResourceBundle().getString("StatusBarDate"));
		date = new Date();
		
		dateLabel = new JLabel(dateFormat.format(date));
		dateLabel.setBorder(new EmptyBorder(0, 10, 0, 5));
		
		add(timeLabel);
		add(dateLabel);
		
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		setBackground(new Color(230, 230, 230));
		
	}
	
	public JLabel getOpenTab() {
		return opentab;
	}
	
	public void initComponents() {
		opentab.setText(LanguageController.getInstance().getResourceBundle().getString("StudentsTab"));
		StudentskaSluzba.setText("   " + LanguageController.getInstance().getResourceBundle().getString("StatusBarStudentService"));
		dateFormat = new SimpleDateFormat(LanguageController.getInstance().getResourceBundle().getString("StatusBarDate"));
		dateLabel.setText(dateFormat.format(date));
	}

	
}