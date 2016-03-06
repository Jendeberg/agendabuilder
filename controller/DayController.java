package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
	private TextField endTime;
	@FXML
	private TextField lengthTime;
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
		updateActivities();
	}
	
	public void setModel(AgendaModel model){
		this.model = model;
	}
	
	private void updateActivities(){
		list.setItems(FXCollections.observableArrayList(day.activities));
		updateTime();
	}
	

	private void updateTime() {
		startTime.setPromptText(day.getStart()/60+ ":" + (day.getStart()%60));
		//endTime.setDisable(false);
		//lengthTime.setDisable(false);
		endTime.setText((day.getEnd()/60) + ":" + (day.getEnd()%60));
		lengthTime.setText((day.getTotalLength()/60) + ":" + (day.getTotalLength()%60));
		//endTime.setDisable(true);
		//lengthTime.setDisable(true);
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
	            	System.out.println("Enter");
	            	String[] slist = startTime.getText().split(":");
	            	int hour = Integer.parseInt(slist[0]);
	            	int min = Integer.parseInt(slist[1]);
	            	if(hour>24){
	            		day.setStart(hour*60+min);
	            	}
	                updateTime();
	            }
	        }
	    });
	}
}
