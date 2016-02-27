package se.kth.csc.iprog.agendabuilder.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;

import se.kth.csc.iprog.agendabuilder.model.*;

/**
 * This is the starting view for the AgendaBuilder.
 * 
 * @author Daniel
 * 
 */
public class StartView extends JFrame implements Observer {
	private final int margin = 10;
	private AgendaModel model;

	// private UI components
	private JScrollPane dayViewer = new JScrollPane();
	
	// public UI components
	public JButton addActivity;
	public JButton detailedPlan;
	public JButton addDay;
	public JList<Activity> activities;
	
	public ArrayList<JList> days;

	/**
	 * Initalizes the Starting view of the AgendaBuilder.
	 * 
	 * @param model
	 */
	public StartView(AgendaModel model) {
		this.model = model;
		this.setLayout(null);

		addActivity = new JButton("Add Activity");
		addActivity.setBounds(margin, 25, 200, 50);
		addActivity.setBackground(Color.GREEN);
		addActivity.setOpaque(true);
		this.add(addActivity);

		detailedPlan = new JButton("Show Detailed Plan");
		detailedPlan.setBounds(1350, 400, 150, 300);
		this.add(detailedPlan);

		addDay = new JButton("Add Day");
		addDay.setBounds(1350, 100, 150, 300);
		this.add(addDay);

		activities = new JList<Activity>();
		updateActivityList();
		JScrollPane parked = new JScrollPane();
		parked.setBounds(margin, 100, 200, 600);
		parked.add(activities);
		this.add(parked);

		dayViewer.setBounds(300, 100, 1000, 600);
		this.add(dayViewer);
		
		this.setBounds(0, 0, 1600, 900);
		this.setVisible(true);
	}

	private void updateActivityList() {
		activities.setListData((new Vector<Activity>(model.parkedActivites)));
	}

	private void drawDays() {
		
	}
	
	private void updateDays() {
		
	}

	/**
	 * Updates from the model updates different parts of the view.
	 * 
	 * @param arg0
	 *            This is the model that is observed.
	 * @param arg1
	 *            This is the Object (String) that tells us what was updated.
	 */
	public void update(Observable arg0, Object arg1) {
		String updated = (String) arg1;

		if (updated.equals("AddedDay")) {
			// The number of days is updated.
			updateDays();
			drawDays();
		} else if (updated.equals("ActivityParked")) {
			// A new activity was parked.
			updateActivityList();
		}

	}

}
