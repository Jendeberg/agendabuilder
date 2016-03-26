package se.kth.csc.iprog.agendabuilder.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.util.DayViewUtil;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;
import se.kth.csc.iprog.agendabuilder.view.EditActivity;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class StartController implements Initializable, Observer {
	public static final DataFormat ACTIVITY_FORMAT = new DataFormat("Activity.custom");
	
	private AgendaModel model;

	@FXML
	private Button addActivity;
	@FXML
	private Button addDay;
	@FXML
	private ListView<Activity> activityList;
	@FXML
	private ScrollPane dayPane;

	public StartController() {

	}

	/**
	 * 
	 */
	public StartController(AgendaModel model) {
		this.model = model;
	}
	
	//CHECKS WHICH ITEM HAS BEEN CLICKED - NEEDS TO REDIRECT TO EDITOR.
	@FXML public void updateAct(MouseEvent arg0){
		Activity activity = activityList.getSelectionModel().getSelectedItem();
		if(activity == null || !activity.editing ){
			activity.editing = true;
			new EditActivity(model, activity, null);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (addActivity == null || addDay == null) {
			System.out.println("buttons not initialized");
		}
		addActivity.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AddActivity(model);
			}
		});
		addDay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.addDay(8, 0);
			}
		});

		activityList.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {
				@Override
			    public ListCell<Activity> call(ListView<Activity> arg0) {
			        //My cell is on my way to call
			        final ListCell<Activity> cell = new ListCell<Activity>(){
			            @Override
			            public void updateItem(Activity act, boolean empty){
			                super.updateItem(act,empty);
			                if(act!=null){   
			                	if (act != null) {
			                		String hour = "" + act.getLength()/60;
			                		if(act.getLength()/60 < 10){ hour = "0"+hour; }
			                		String min = "" + act.getLength()%60;
			                		if(act.getLength()%60 < 10){ min = "0"+min; }
									setText(hour + ":" + min + " | " +act.toString());
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
			                }else{
			                	setText(null);
			                	getStyleClass().removeAll(getStyleClass());
			                }
			            }
			        };
			        cell.setOnDragDetected(new EventHandler<MouseEvent>() {
					    @Override
						public void handle(MouseEvent event) {
					        /* drag was detected, start a drag-and-drop gesture*/
					        /* allow any transfer mode */
					        Dragboard db = activityList.startDragAndDrop(TransferMode.ANY);
					        
					        /* Put a string on a dragboard */
					        ClipboardContent content = new ClipboardContent();
					        content.putString("parkedActivity");
					        content.put(ACTIVITY_FORMAT, cell.getItem());
					        db.setContent(content);
					        
					        event.consume();
					    }
					});
			        
			        cell.setOnDragDone(new EventHandler<DragEvent>() {
			            @Override
						public void handle(DragEvent event) {
			                event.consume();
			            }
			        });
			        return cell;
			    }
		});
		
		
		activityList.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != activityList && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.ANY);
				}

				event.consume();
			}
		});
		activityList.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != activityList && event.getDragboard().hasString()) {
					activityList.setCursor(Cursor.CLOSED_HAND);
				}

				event.consume();
			}
		});
		activityList.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				activityList.setCursor(Cursor.DEFAULT);

				event.consume();
			}
		});
		activityList.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
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
						model.moveActivity(oldDay, oldIndex, null, model.parkedActivites.size());
						success = true;
					}
				}
				event.setDropCompleted(success);

				event.consume();
			}
		});
	}

	/**
	 * Sets the model for the controller.
	 * 
	 * @param model
	 *            The model to be used.
	 */
	public void setModel(AgendaModel model) {
		this.model = model;
		this.model.addObserver(this);
	}

	/**
	 * Updates the DayPane. Adding and removing days as needed.
	 * 
	 */
	public void updateDayPane() {
		Pane p = new Pane();
		int i = 0;
		for (Day d : model.days) {
			try {
				AnchorPane dayView = DayViewUtil.createDayView(d, model);
				dayView.setLayoutX(i);
				i += dayView.getPrefWidth();
				p.getChildren().add(dayView);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dayPane.setContent(p);
	}

	@Override
	public void update(Observable o, Object arg) {
		String s = (String) arg;
		if (s.equals("ActivityParked") || s.equals("ParkedActivityRemoved")) {
			activityList.setItems(FXCollections.observableArrayList(model.parkedActivites));
		} else if (s.equals("AddedDay") || s.equals("ActivityAddedToDay") || s.equals("ActivityMoved")) {
			updateDayPane();
		}
	}
}
