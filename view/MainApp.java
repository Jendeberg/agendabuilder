//TESTING GIT


package se.kth.csc.iprog.agendabuilder.view;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.kth.csc.iprog.agendabuilder.controller.StartController;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;

public class MainApp extends Application{
	private Stage stage;
	private AnchorPane layout;
	private AgendaModel model;
	

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setTitle("Agenda Builder");
		initLayout();
	}
	
	private void initLayout(){
		try{
		this.model = new AgendaModel();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("MainView.fxml"));
        layout = (AnchorPane) loader.load();
        StartController controller = loader.<StartController>getController();
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
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getStage() {
        return stage;
    }
    
	public static void main(String[] args){
		launch(args);
	}
}
