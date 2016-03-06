package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;
import se.kth.csc.iprog.agendabuilder.view.DetailedPlan;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class StartController implements Initializable,Observer {
	private AgendaModel model;
	
	@FXML
	private Button addActivity;
	@FXML
	private Button addDay;
	@FXML
	private Button detailedPlan;
	@FXML
	private ListView<String> activityList;
	
	public StartController(){
		
	}
	
	/**
	 * 
	 */
	public StartController(AgendaModel model){
		this.model = model;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(addActivity == null || detailedPlan == null || addDay == null){
			System.out.println("buttons not initialized");
		}
		addActivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Create new Activity");
            	new AddActivityButtonController(model, new AddActivity(model));
            }
        });
		detailedPlan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Show detailed plan");
            }
        });
		addDay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
    			System.out.println("Adding a new Day");
            }
        });
	}

	/**
	 * Sets the model for the controller.
	 * 
	 * @param model The model to be used.
	 */
	public void setModel(AgendaModel model){
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) {
		String s = (String) arg;
		if(s.equals("ActivityParked")){
			activityList = new ListView<String>();
			ObservableList<String> listItems = FXCollections.observableArrayList("Add Items here");
			for(Activity act : model.parkedActivites){
				listItems.add(act.toString());
			}
			activityList.setItems(listItems);
		} 
		
	}
}
