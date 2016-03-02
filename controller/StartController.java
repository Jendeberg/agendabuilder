package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class StartController implements Initializable {
	private AgendaModel model;
	
	@FXML
	private Button addActivity;
	@FXML
	private Button addDay;
	@FXML
	private Button detailedPlan;
	
	public StartController(){
		
	}
	
	/**
	 * Constructor, starts the actionlistener on the buttons.
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
}
