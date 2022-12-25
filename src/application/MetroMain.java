package application;
	
import domain.model.MetroCard;
import domain.model.db.loadSaveStrategies.*;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		AdminView adminView = new AdminView();
		MetroTicketView metroTicketView = new MetroTicketView();
		MetroStationView metroStationView = new MetroStationView();
//		Map<Integer, MetroCard> list = new TreeMap<>();
//		list.put(1, new MetroCard(1, 10, 2021,3, 59));
//		list.put(2, new MetroCard(2, 1, 2022,10, 5));
//		list.put(3, new MetroCard(3, 1, 2022,0, 10));
//		list.put(4, new MetroCard(4, 2, 2022,10, 60));
//
//		LoadSaveStrategy l = LoadSaveStrategyFactory.createLoadSaveStrategy("EXCEL");
//		System.out.println(l.load("src/bestanden/metrocards2.xls").values().toString());

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
