package view;

import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.util.PendingException;

import java.util.ArrayList;

public class MetroTicketView {
	private Stage stage = new Stage();
	private MetroTicketViewController controller;
		
	public MetroTicketView(){
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

    public void updateIdCheckbox(ArrayList<Integer> metroCardIds) {
		ChoiceBox choiceBox = new ChoiceBox();
		choiceBox.setItems(FXCollections.observableArrayList(metroCardIds));
		Group root = new Group();
		root.getChildren().add(choiceBox);
		Scene scene = new Scene(root, 650, 350);
		this.stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
    }

	public void setController(MetroTicketViewController metroTicketViewController) {
		this.controller = metroTicketViewController;
	}
}
