package view;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.DynamicClock;

public class StatusBar extends JPanel {
	
	//Tabbed pane
	
	public StatusBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel StudentskaSluzba = new JLabel("  Studentska služba");
		add(StudentskaSluzba);
		
		add(Box.createHorizontalGlue());
		
		JLabel timeLabel = new JLabel();
		DynamicClock dc = new DynamicClock(timeLabel);
		
		DateFormat dateFormat = new SimpleDateFormat("  dd.MM.yyyy.  ");
		Date date = new Date();
		
		JLabel dateLabel = new JLabel(dateFormat.format(date));
		
		Font fSS = StudentskaSluzba.getFont();
		StudentskaSluzba.setFont(fSS.deriveFont(fSS.getStyle() | Font.BOLD));
		
		Font fTime = timeLabel.getFont();
		timeLabel.setFont(fTime.deriveFont(fTime.getStyle() | Font.BOLD));
		
		Font fDate = timeLabel.getFont();
		dateLabel.setFont(fDate.deriveFont(fDate.getStyle() | Font.BOLD));
		
		add(timeLabel);
		add(dateLabel);
		
		setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		setBackground(new Color(230, 230, 230));
		
	}
	
	//funnction za lebelu
	
}