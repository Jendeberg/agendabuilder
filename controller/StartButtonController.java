package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;
import se.kth.csc.iprog.agendabuilder.view.DetailedPlan;
import se.kth.csc.iprog.agendabuilder.view.StartView;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class StartButtonController implements ActionListener{
	private StartView view;
	private AgendaModel model;
	
	/**
	 * Constructor, starts the actionlistener on the buttons.
	 * 
	 */
	public StartButtonController(AgendaModel model, StartView view){
		this.view = view;
		this.model = model;
		
		view.addActivity.addActionListener(this);
		view.detailedPlan.addActionListener(this);
		view.addDay.addActionListener(this);
	}
	
	/**
	 * Checks where the ActionEvent comes from and then decides what to do with it.
	 * 
	 * @param ae The ActionEvent that came from one of the buttons.
	 * 
	 */
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == view.addActivity){
			System.out.println("Create new Activity");
			new AddActivityButtonController( model, new AddActivity(model) );
		}else if(ae.getSource() == view.detailedPlan){
			System.out.println("Show detailed plan");
			new DetailedPlan();
		}else if(ae.getSource() == view.addDay){
			System.out.println("Adding a new Day");
		}
	}

}
