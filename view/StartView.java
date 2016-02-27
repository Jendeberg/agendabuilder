package se.kth.csc.iprog.agendabuilder.view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

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
	
	// public UI components
	public JButton addActivity;
	public JButton detailedPlan;
	public JButton addDay;
	public JList activities;
	public JTable agenda;
	
	

	/**
	 * Initalizes the Starting view of the AgendaBuilder.
	 * 
	 * @param model
	 */
	public StartView(AgendaModel model) {
		this.model = model;
		this.setLayout(null);
		
		addActivity = new JButton("Add Activity");
		addActivity.setBounds(margin,0,150,50);
		addActivity.setBackground(Color.GREEN);
		addActivity.setOpaque(true);
		this.add(addActivity);

		
		detailedPlan = new JButton("Show Detailed Plan");
		detailedPlan.setBounds(margin,375,150,300);
		this.add(detailedPlan);
		
		addDay = new JButton("Add Day");
		addDay.setBounds(margin,75,150,300);
		this.add(addDay);
		
		
		
		this.setBounds(0,0,1600,900);
		this.setVisible(true);
	}
	
	
	private void createAgendaList(){
		
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

		if (arg1.equals("Days")) {// The number of days is updated.

		} else if (arg1.equals("Agenda")) {// The agenda was updated.

		}

	}

}
