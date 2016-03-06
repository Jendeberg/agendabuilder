package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.WindowEvent;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class AddActivityButtonController implements Initializable {
	private AgendaModel model;
		
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
	public AddActivityButtonController(){
		type.setItems(FXCollections.observableArrayList("Presentation", "Group Work", "Discussion", "Break"));
	}
	public AddActivityButtonController(AgendaModel model) {
		this.model = model;
		type.setItems(FXCollections.observableArrayList("Presentation", "Group Work", "Discussion", "Break"));
	}
	
	public void setModel(AgendaModel model){
		this.model = model;
		ObservableList<String> listItems = FXCollections.observableArrayList();
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
		
	}
}
