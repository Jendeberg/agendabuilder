package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.model.Day;

public class DayController implements Initializable, Observer {
	private Day day;
	private AgendaModel model;

	@FXML
	private TextField startTime;
	@FXML
	private Label endTime;
	@FXML
	private Label lengthTime;
	@FXML
	private Label colorLabel;
	@FXML
	private ListView<Activity> list;
	@FXML 
	private Button removeButton;

	@Override
	public void update(java.util.Observable o, Object arg) {
		String s = (String) arg;
		if (s.equals("StartChanged")) {
			updateTime();
		} else if (s.equals("ActivityAdded")) {
			updateActivities();
		} else if (s.equals("ActivityRemoved")) {
			updateActivities();
		} else if (s.equals("ActivityMoved")) {
			updateActivities();
		}
	}

	public void setDay(Day d) {
		this.day = d;
		day.addObserver(this);
		updateActivities();
	}
	
	public void setModel(AgendaModel m){
		this.model = m;
		model.addObserver(this);
	}
	
	private void updateActivities(){
		list.setItems(FXCollections.observableArrayList(day.activities));
		updateTime();
	}
	

	private void updateTime() {
		String seperator = ":";
		if((day.getStart()%60)<10)
			seperator += "0";
		startTime.setPromptText(day.getStart()/60+ seperator + (day.getStart()%60));
		if(!((day.getEnd()%60)<10))
			seperator = ":";
		endTime.setText((day.getEnd()/60) + seperator + (day.getEnd()%60));
		lengthTime.setText((day.getTotalLength()/60) + ":" + (day.getTotalLength()%60));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		endTime.setDisable(true);
		lengthTime.setDisable(true);
		
		list.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {

			@Override
			public ListCell<Activity> call(ListView<Activity> p) {

				ListCell<Activity> cell = new ListCell<Activity>() {

					@Override
					protected void updateItem(Activity act, boolean bln) {
						super.updateItem(act, bln);
						if (act != null) {
							setText(act.toString());
							switch (act.getType()) {
							case Activity.PRESENTATION:
								getStyleClass().add("pres");
								break;
							case Activity.GROUP_WORK:
								getStyleClass().add("gw");
								break;
							case Activity.DISCUSSION:
								getStyleClass().add("disc");
								break;
							case Activity.BREAK:
								getStyleClass().add("brk");
								break;
							}
						}
					}
				};
				return cell;
			}
		});
		
		startTime.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER)){
	            	String[] slist = startTime.getText().split(":");
	            	int hour = Integer.parseInt(slist[0]);
	            	int min = Integer.parseInt(slist[1]);
	            	if(hour<24){
	            		day.setStart(hour*60+min);
	            	}
	                updateTime();
	            }
	        }
	    });
		
		updateColorLabel();
	}

	private void updateColorLabel() {
		// TODO 
		// Fix the coloring of this label and set a red line at 30%.
		colorLabel.getStyleClass().add("pres");
		
	}
}
