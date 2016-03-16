package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class EditActivityController implements Initializable {
	private AgendaModel model;
	private Activity activity;
		
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private TextField name;
	@FXML
	private TextField length;
	@FXML
	private TextField desc;
	@FXML
	private ChoiceBox<String> type;

	/**
	 * Constructor, starts the actionlistener on the buttons.
	 * 
	 */
	public EditActivityController(){
	}
	public EditActivityController(AgendaModel model, Activity activity) {
		this.model = model;
		this.activity = activity;
	}
	
	public void setModel(AgendaModel model){
		this.model = model;
	}
	
	public void setActivity(Activity activity){
		this.activity = activity;
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
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(activity.getDescription());
		length.setText(Integer.toString(activity.getLength()));
		desc.setText(activity.getDescription());
		name.setText(activity.getName());
		type.getSelectionModel().select(activity.getType());
		
		save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int typeVal = 0;
            	switch(type.getValue()){
            		case "Presentation":typeVal=1;
            		break;
            		case "Group Work":typeVal=2;
            		break;
            		case "Discussion":typeVal=3;
            		break;
            		case "Break":typeVal=4;
            		break;
            	}
            	model.addParkedActivity(new Activity(name.getText(), desc.getText(), Integer.parseInt(length.getText()), typeVal));
            	Node  source = (Node)  event.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
		cancel.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
            	Node  source = (Node)  event.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
			}
			
		});
		
		type.setItems(FXCollections.observableArrayList("Presentation", "Group Work", "Discussion", "Break"));
	}

}