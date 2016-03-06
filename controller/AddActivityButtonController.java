package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class AddActivityButtonController implements ActionListener, Initializable {
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
	public AddActivityButtonController(AgendaModel model) {
		this.model = model;

	}
	
	public void setModel(AgendaModel model){
		this.model = model;
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
		save.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	}
}
