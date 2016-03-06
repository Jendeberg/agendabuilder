package se.kth.csc.iprog.agendabuilder.view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.Day;

public class DayView extends Pane implements Observable{
	private Day day;
	
	public DayView(Day d){
		day = d;
		Label start = new Label();
		start.setText("Start time: " + day.getStart());
		start.setText("End time: " + day.getEnd());
		ListView<String> list = new ListView<String>();
		list.setOrientation(Orientation.HORIZONTAL);
		for(Activity activity : day.activities){
			
		}
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}
	
}
