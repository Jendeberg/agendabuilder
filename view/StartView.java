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
	private AgendaModel model;

	// UI components
	public JButton addActivity;
	public JButton detailedPlan;
	public JList temporary;
	public JTable agenda;
	
	

	/**
	 * Initalizes the Starting view of the AgendaBuilder.
	 * 
	 * @param model
	 */
	public StartView(AgendaModel model) {
		this.model = model;
		
		addActivity = new JButton("Add Activity");
		addActivity.setBounds(0,0,300,50);
		addActivity.setBackground(Color.GREEN);
		addActivity.setOpaque(true);
		this.add(addActivity);
		this.pack();
		//this.setBounds(0,0,250,300);
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
