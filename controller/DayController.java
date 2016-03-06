package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.model.Day;

public class DayController implements Initializable, Observer {
	private Day day;
	private AgendaModel model;

	@FXML
	private Label startTime;
	@FXML
	private Label endTime;
	@FXML
	private Label label;
	@FXML
	private Label colorLabel;
	@FXML
	private ListView<String> list;
	@FXML 
	private Button removeButton;

	@Override
	public void update(java.util.Observable o, Object arg) {
		String s = (String) arg;
		if (s.equals("StartChanged")) {

		} else if (s.equals("ActivityAdded")) {
			updateActivities();
		} else if (s.equals("ActivityRemoved")) {
			updateActivities();
		} else if (s.equals("ActivityMoved")) {
			updateActivities();
		}
	}

	public void setDay(Day d) {
		this.day = d;
		updateActivities();
	}
	
	public void setModel(AgendaModel model){
		this.model = model;
	}
	
	private void updateActivities(){
		for(Activity act : day.activities){
			
		}
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Empty?
	}
}
