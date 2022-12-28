package view;

import domain.controller.AdminController;
import domain.controller.MetroCardOverviewPaneController;
import domain.controller.MetroStationViewController;
import domain.model.MetroCard;
import domain.model.MetroEventsEnum;
import domain.model.MetroFacade;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class AdminView {
	private Stage stage = new Stage();
	private AdminController controller;
	private AdminMainPane pane;
		
	public AdminView(MetroFacade facade){
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(660);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 690, 680);

		this.pane = new AdminMainPane(facade);


		this.pane.prefHeightProperty().bind(scene.heightProperty());
		this.pane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(this.pane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void setController(AdminController metroStationViewController) {
		this.controller = metroStationViewController;
	}
	public void updateMetroCardList(ArrayList<MetroCard> cards){
		this.pane.getOverviewPane().updateMetroCardList(cards);
	}
}
