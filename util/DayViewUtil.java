package se.kth.csc.iprog.agendabuilder.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.kth.csc.iprog.agendabuilder.controller.DayController;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.view.MainApp;

public class DayViewUtil {
	
	public static AnchorPane createDayView(Day day) throws IOException{
		AnchorPane dayView = null;
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("DayView.fxml"));
        dayView = (AnchorPane) loader.load();
        DayController controller = loader.<DayController>getController();
        controller.setDay(day);
        
		return dayView;
	}
}
