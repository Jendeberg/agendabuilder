package se.kth.csc.iprog.agendabuilder.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.kth.csc.iprog.agendabuilder.controller.EditActivityController;
import se.kth.csc.iprog.agendabuilder.model.*;

/**
 * The window to add an activity from.
 * @author Daniel
 *
 */
public class EditActivity{
	private Stage stage;
	private AnchorPane layout;
	private AgendaModel model;
	private Activity activity;
	private Day day;
	
	public EditActivity(AgendaModel model, Activity activity, Day day){
		stage = new Stage();
		stage.setTitle("Edit Activity");
		this.model = model;
		this.day = day;
		this.activity = activity;
		initLayout();

	}
	
	private void initLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("EditActivity.fxml"));
	        layout = (AnchorPane) loader.load();
	        EditActivityController controller = loader.<EditActivityController>getController();
	        controller.setModel(model);
	        controller.setDay(day);
	        controller.setActivity(activity);
	        
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
