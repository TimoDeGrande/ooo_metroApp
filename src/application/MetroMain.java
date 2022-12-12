package application;
	
import domain.model.db.MetroCardDatabase;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyEnum;
import domain.model.db.loadSaveStrategies.MetroCardExcelLoadSaveStrategy;
import domain.model.db.loadSaveStrategies.MetroCardTextLoadSaveStrategy;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;


public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		MetroCardDatabase db = new MetroCardDatabase(new MetroCardTextLoadSaveStrategy());
//		db.setLoadSaveStrategy(new MetroCardExcelLoadSaveStrategy());
		AdminView adminView = new AdminView(db);
		MetroTicketView metroTicketView = new MetroTicketView();
		MetroStationView metroStationView = new MetroStationView();
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("");
	}
}
