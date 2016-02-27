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

	/**
	 * Initalizes the Starting view of the AgendaBuilder.
	 * 
	 * @param model
	 */
	public StartView(AgendaModel model) {
		this.model = model;
		this.model.addObserver(this);
		
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
		parked.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		parked.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(parked);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 10000, 10000);
		dayViewer = new JScrollPane(panel);
		dayViewer.setBounds(300, 100, 1000, 600);
		dayViewer.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
		dayViewer.setWheelScrollingEnabled(true);
		this.add(dayViewer);
		
		this.setTitle("Agenda Builder");
		this.setBounds(0, 0, 1600, 900);
		this.setVisible(true);
	}

	private void updateActivityList() {
		activities.setListData((new Vector<Activity>(model.parkedActivites)));
	}

	/**
	 * Draws each day on the view.
	 */
	private void drawDays() {
		int i = 1;
		for(Day day : model.days){
			JPanel p = new JPanel();
			p.setLayout(null);
			p.setBounds(i,0,200,dayViewer.getHeight());
			p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			// add info on top.
			JSpinner hour = new JSpinner();
			
			JLabel info = new JLabel();
			
			JLabel picture = new JLabel();
			
			// add ScrollPane with JList of Activities for the day.
			JScrollPane jsp = new JScrollPane();
			jsp.setBounds(0,200, p.getWidth(), p.getHeight()-200);
			jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			jsp.setWheelScrollingEnabled(true);
			jsp.setVerticalScrollBar(new JScrollBar());
			jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			p.add(jsp);
			dayViewer.getViewport().add(p);
			i+=200;
		}
		dayViewer.getViewport().updateUI();
		dayViewer.getViewport().repaint();
		dayViewer.updateUI();
		dayViewer.repaint();
	}
	
	/**
	 * Removes the current days from the view.
	 */
	private void removeDays(){
		dayViewer.removeAll();
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
			// The number of days is updated.'
			removeDays();
			drawDays();
		} else if (updated.equals("ActivityParked")) {
			// A new activity was parked.
			updateActivityList();
		}

	}

}
