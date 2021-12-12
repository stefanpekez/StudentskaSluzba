package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;

public class DynamicClock{
	
	private Timer timer;
	public DynamicClock(JLabel clock) {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("HH:mm");
				clock.setText(dateFormat.format(date));
			}
		};
		timer = new Timer(1000, actionListener);
		timer.setInitialDelay(0);
		timer.start();
	}

}
