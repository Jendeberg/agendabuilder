package se.kth.csc.iprog.agendabuilder.controller;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.util.ListCellX;

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
		} else if (s.equals("ActivityAdded") || s.equals("ActivityRemoved") || s.equals("ActivityMoved")){
			updateActivities();
		}
	}

	public void setDay(Day d) {
		this.day = d;
		day.addObserver(this);
		updateActivities();
	}

	public void setModel(AgendaModel m) {
		this.model = m;
		model.addObserver(this);
	}

	private void updateActivities() {
		list.setItems(FXCollections.observableArrayList(day.activities));
		updateTime();
		updateColorLabel();
	}

	private void updateTime() {
		String seperator = ":";
		if ((day.getStart() % 60) < 10)
			seperator += "0";
		startTime.setPromptText(day.getStart() / 60 + seperator + (day.getStart() % 60));
		if (!((day.getEnd() % 60) < 10))
			seperator = ":";
		endTime.setText((day.getEnd() / 60) + seperator + (day.getEnd() % 60));
		if ((day.getTotalLength() % 60) < 10) {
			seperator = ":0";
		} else {
			seperator = ":";
		}
		lengthTime.setText((day.getTotalLength() / 60) + seperator + (day.getTotalLength() % 60));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		endTime.setDisable(true);
		lengthTime.setDisable(true);

		list.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {

			@Override
			public ListCell<Activity> call(ListView<Activity> p) {
				return new ListCellX<Activity>();
			}
		});

		startTime.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					String[] slist = startTime.getText().split(":");
					int hour = Integer.parseInt(slist[0]);
					int min = Integer.parseInt(slist[1]);
					if (hour < 24) {
						day.setStart(hour * 60 + min);
					}
					updateTime();
				}
			}
		});

		// **********************************
		/* Drag and drop specific methods */
		// **********************************
		/* Make list rearrangeable */
		list.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {
			@Override
			public ListCell<Activity> call(ListView<Activity> arg0) {
				// My cell is on my way to call
				ListCell<Activity> cell = new ListCell<Activity>() {
					@Override
					public void updateItem(Activity act, boolean empty) {
						super.updateItem(act, empty);
						if (act != null) {
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
					}
				};
				cell.setOnDragDetected(new EventHandler<MouseEvent>() {
				    public void handle(MouseEvent event) {
				        /* drag was detected, start a drag-and-drop gesture*/
				        /* allow any transfer mode */
				        Dragboard db = cell.startDragAndDrop(TransferMode.ANY);
				        
				        /* Put a string on a dragboard */
				        ClipboardContent content = new ClipboardContent();
				        content.putString("Day");
				        content.put(StartController.ACTIVITY_FORMAT, cell.getItem());
				        db.setContent(content);
				        
				        event.consume();
				    }
				});
		        
		        cell.setOnDragDone(new EventHandler<DragEvent>() {
		            public void handle(DragEvent event) {

		                event.consume();
		            }
		        });
		        
		        cell.setOnDragDropped(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* data dropped */
						/* if there is a string data on dragboard, read it and use it */
						Dragboard db = event.getDragboard();
						boolean success = false;
						if(db.hasString() && db.getString().equals("Day")){
							Activity act = (Activity) db.getContent(StartController.ACTIVITY_FORMAT);
							Day oldDay = null;
							int oldIndex = 0;
							for(Day d : model.days){
								for(Activity a : d.activities){
									if(a.getName().equals(act.getName())){
										oldDay = d;
										oldIndex = d.activities.indexOf(a);
										break;
									}
								}
							}
							if(oldDay != null){
								int newIndex = cell.getIndex();
								if(newIndex > day.activities.size())
									newIndex = day.activities.size();
								model.moveActivity(oldDay, oldIndex, day, newIndex);
								success = true;
							}
						}else if (db.hasString() && db.getString().equals("parkedActivity")) {
							Activity act = (Activity) db.getContent(StartController.ACTIVITY_FORMAT);
							model.addActivity(act, day, 0);
							for(Activity a : model.parkedActivites){
								if(a.getName().equals(act.getName())){
									model.removeParkedActivity(model.parkedActivites.lastIndexOf(a));
									success = true;
								}
							}
						}
						/*
						 * let the source know whether the string was successfully
						 * transferred and used
						 */
						event.setDropCompleted(success);

						event.consume();
					}
				});
		        
		        return cell;
		    }
		});
		list.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != list && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.ANY);
				}

				event.consume();
			}
		});
		list.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != list && event.getDragboard().hasString()) {
					list.setCursor(Cursor.CLOSED_HAND);
				}

				event.consume();
			}
		});
		list.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				list.setCursor(Cursor.DEFAULT);

				event.consume();
			}
		});
		list.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString() && db.getString().equals("parkedActivity")) {
					Activity act = (Activity) db.getContent(StartController.ACTIVITY_FORMAT);
					model.addActivity(act, day, 0);
					for(Activity a : model.parkedActivites){
						if(a.getName().equals(act.getName())){
							model.removeParkedActivity(model.parkedActivites.lastIndexOf(a));
							success = true;
						}
					}
				}else if(db.hasString() && db.getString().equals("Day")){
					Activity act = (Activity) db.getContent(StartController.ACTIVITY_FORMAT);
					Day oldDay = null;
					int oldIndex = 0;
					for(Day d : model.days){
						for(Activity a : d.activities){
							if(a.getName().equals(act.getName())){
								oldDay = d;
								oldIndex = d.activities.indexOf(a);
								break;
							}
						}
					}
					if(oldDay != null){
						model.moveActivity(oldDay, oldIndex, day, 0);
						success = true;
					}
				}
			}
		});
	}

	private void updateColorLabel() {
		if(day.getTotalLength() == 0){
			colorLabel.setStyle("-fx-border-color:red; -fx-background-color: beige;");
			return;
		}
		/*Label pres = new Label();
		pres.getStyleClass().add("pres");
		pres.setPrefWidth(colorLabel.getWidth());
		pres.setPrefHeight((day.getLengthByType(Activity.PRESENTATION)*100/day.getTotalLength()));
		
		Label gw = new Label();
		gw.getStyleClass().add("gw");
		gw.setPrefWidth(colorLabel.getWidth());
		gw.setPrefHeight((day.getLengthByType(Activity.GROUP_WORK)*100/day.getTotalLength()));
		gw.setLayoutY(pres.getPrefHeight());
		
		Label disc = new Label();
		disc.getStyleClass().add("disc");
		disc.setPrefWidth(colorLabel.getWidth());
		disc.setPrefHeight((day.getLengthByType(Activity.DISCUSSION)*100/day.getTotalLength()));
		disc.setLayoutY(pres.getPrefHeight()+gw.getPrefHeight());
		
		Label brk = new Label();
		brk.getStyleClass().add("brk");
		brk.setPrefWidth(colorLabel.getWidth());
		brk.setPrefHeight((day.getLengthByType(Activity.BREAK)*100/day.getTotalLength()));
		brk.setLayoutY(pres.getPrefHeight()+gw.getPrefHeight()+disc.getPrefHeight());
		
		colorLabel.getChildrenUnmodifiable().add(pres);
		colorLabel.getChildrenUnmodifiable().add(gw);
		colorLabel.getChildrenUnmodifiable().add(disc);
		colorLabel.getChildrenUnmodifiable().add(brk);*/
	}
}
