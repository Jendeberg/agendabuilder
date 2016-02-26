package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;
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
	public StartButtonController(StartView view, AgendaModel model){
		this.view = view;
		this.model = model;
		
		view.addActivity.addActionListener(this);
	}
	
	/**
	 * Checks where the ActionEvent comes from and then decides what to do with it.
	 * 
	 * @param ae The ActionEvent that came from one of the buttons.
	 * 
	 */
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == view.addActivity){
			new AddActivity(model);
		}
	}

}
