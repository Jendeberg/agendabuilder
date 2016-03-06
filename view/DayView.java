package se.kth.csc.iprog.agendabuilder.view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.Day;

public class DayView extends Pane implements Observable{
	private Day day;
	
	public DayView(Day d){
		day = d;
		Label start = new Label();
		start.
		ScrollPane sc = new ScrollPane();
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
