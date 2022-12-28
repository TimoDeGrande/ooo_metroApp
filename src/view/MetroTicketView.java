package view;

import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.util.PendingException;

import java.util.ArrayList;

public class MetroTicketView {
	private Stage stage = new Stage();
	private GridPane root = new GridPane();
	private ChoiceBox ids = new ChoiceBox();
	private MetroTicketViewController controller;

	public MetroTicketView(){
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Button button = new Button("Buy card");
		button.setOnAction(event -> this.buyMetroCard());
		this.root.add(button, 0,0);
		Scene scene = new Scene(this.root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateIdCheckbox(ArrayList<Integer> metroCardIds) {
		GridPane newRoot = new GridPane();
		newRoot.add(this.root.getChildren().get(0), 0,0);
		ChoiceBox choiceBox = new ChoiceBox();
		choiceBox.setItems(FXCollections.observableArrayList(metroCardIds));
		newRoot.add(choiceBox,0,1);
		Scene scene = new Scene(newRoot, 650, 350);
		this.root = newRoot;
		this.stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void setController(MetroTicketViewController metroTicketViewController) {
		this.controller = metroTicketViewController;
	}

	public void buyMetroCard(){
		controller.buyMetroCard();
	}
}