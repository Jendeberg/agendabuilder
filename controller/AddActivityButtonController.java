package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import se.kth.csc.iprog.agendabuilder.model.Activity;
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
public class AddActivityButtonController implements ActionListener {
	private AddActivity view;
	private AgendaModel model;

	/**
	 * Constructor, starts the actionlistener on the buttons.
	 * 
	 */
	public AddActivityButtonController(AgendaModel model, AddActivity view) {
		this.view = view;
		this.model = model;

		view.cancel.addActionListener(this);
		view.save.addActionListener(this);
	}

	/**
	 * Checks where the ActionEvent comes from and then decides what to do with
	 * it.
	 * 
	 * @param ae
	 *            The ActionEvent that came from one of the buttons.
	 * 
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == view.save) {
			// Save the activity
			model.addParkedActivity(new Activity(null, null, 0, 0));
		}
		// Close view.
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
	}
}
