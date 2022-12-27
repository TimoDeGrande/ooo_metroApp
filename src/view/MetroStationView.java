package view;

import domain.controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.util.PendingException;

import java.util.ArrayList;

public class MetroStationView {
	private MetroStationViewController controller;
	private Stage stage = new Stage();		
	
	public MetroStationView(){			
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 300);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

	public void setController(MetroStationViewController metroStationViewController) {
		this.controller = metroStationViewController;
	}

	public void updateIdCheckbox(ArrayList<Integer> metroCardIds) {
		ChoiceBox choiceBox = new ChoiceBox();
		choiceBox.setItems(FXCollections.observableArrayList(metroCardIds));
		Group root = new Group();
		root.getChildren().add(choiceBox);
		Scene scene = new Scene(root, 650, 300);
		this.stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}
