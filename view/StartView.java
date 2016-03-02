package se.kth.csc.iprog.agendabuilder.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;

public class StartView extends Application{
	private Stage stage;
	private AnchorPane layout;
	private AgendaModel model;
	
	public StartView() {
		this.model = new AgendaModel();
		//StartButtonController SBC = new StartButtonController(model);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setTitle("Agenda Builder");
		
		initLayout();
	}
	
	private void initLayout(){
		try{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartView.class.getResource("Test.fxml"));
        layout = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(layout);
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
