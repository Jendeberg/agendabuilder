package se.kth.csc.iprog.agendabuilder.view;

import java.io.IOException;
import java.util.*;
import javax.swing.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.kth.csc.iprog.agendabuilder.controller.AddActivityButtonController;
import se.kth.csc.iprog.agendabuilder.controller.StartController;
import se.kth.csc.iprog.agendabuilder.model.*;
import se.kth.csc.iprog.agendabuilder.util.*;

/**
 * The window to add an activity from.
 * @author Daniel
 *
 */
public class AddActivity{
	private Stage stage;
	private AnchorPane layout;
	private AgendaModel model;
	
	public AddActivity(AgendaModel model){
		stage = new Stage();
		stage.setTitle("Add Activity");
		this.model = model;
		initLayout();

	}
	
	private void initLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("AddActivity.fxml"));
	        layout = (AnchorPane) loader.load();
	        AddActivityButtonController controller = loader.<AddActivityButtonController>getController();
	        controller.setModel(model);
	
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(layout);
	        scene.getStylesheets().add("/resources/css/style.css");
	        stage.setScene(scene);
	        stage.show();
		}catch (IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
