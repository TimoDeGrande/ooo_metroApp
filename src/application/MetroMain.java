package application;
	
import domain.controller.ControlCenterPaneController;
import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import domain.model.MetroCard;
import domain.model.MetroFacade;
import domain.model.db.loadSaveStrategies.*;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;
import view.panels.ControlCenterPane;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		MetroFacade facade = new MetroFacade();
		AdminView adminView = new AdminView();

		MetroTicketView metroTicketView = new MetroTicketView();
		MetroStationView metroStationView = new MetroStationView();
		ControlCenterPane controlCenterPane = new ControlCenterPane();

		MetroTicketViewController metroTicketViewController = new MetroTicketViewController(metroTicketView, facade);
		MetroStationViewController metroStationViewController = new MetroStationViewController(metroStationView, facade);
		ControlCenterPaneController controlCenterPaneController = new ControlCenterPaneController(controlCenterPane, facade);

		controlCenterPane.setController(controlCenterPaneController);
//		controlCenterPane.openMetrostation();



	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
