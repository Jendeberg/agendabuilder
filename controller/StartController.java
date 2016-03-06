package se.kth.csc.iprog.agendabuilder.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.model.Day;
import se.kth.csc.iprog.agendabuilder.util.DayViewUtil;
import se.kth.csc.iprog.agendabuilder.view.AddActivity;

/**
 * An ActionListener that listens to the buttons in the start view.
 * 
 * @author Daniel
 *
 */
public class StartController implements Initializable, Observer {
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
				AnchorPane dayView = DayViewUtil.createDayView(d);
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
		if (s.equals("ActivityParked")) {
			ObservableList<Activity> listItems = FXCollections.observableArrayList();
			for (Activity act : model.parkedActivites) {
				listItems.add(act);
				System.out.println(act.toString());
			}
			activityList.setItems(null);
			activityList.setItems(listItems);
		} else if (s.equals("AddedDay") || s.equals("ActivityAddedToDay") || s.equals("ActivityMoved")) {
			updateDayPane();
		}
	}
}
