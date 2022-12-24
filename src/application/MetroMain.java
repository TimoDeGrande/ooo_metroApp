package application;
	
import domain.model.MetroCard;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyEnum;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyFactory;
import domain.model.db.loadSaveStrategies.MetroCardTextLoadSaveStrategy;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;

import java.util.ArrayList;


public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
//		AdminView adminView = new AdminView();
//		MetroTicketView metroTicketView = new MetroTicketView();
//		MetroStationView metroStationView = new MetroStationView();
		ArrayList list = new ArrayList();
		list.add(new MetroCard(1, 10, 2021,3, 59));
		list.add(new MetroCard(2, 1, 2022,10, 5));
		list.add(new MetroCard(3, 1, 2022,0, 10));
		list.add(new MetroCard(4, 2, 2022,10, 60));

		LoadSaveStrategy l = new MetroCardTextLoadSaveStrategy();
		l.save("newfile", list);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
