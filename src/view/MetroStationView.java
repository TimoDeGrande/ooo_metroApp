package view;

import domain.controller.MetroStationViewController;
import domain.model.MetroEventsEnum;
import domain.model.MetroGate;
import domain.model.MetroCard;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.HashMap;

public class MetroStationView {
	private MetroStationViewController controller;
	private Stage stage = new Stage();
	private ChoiceBox ids = new ChoiceBox();
	private GridPane root = new GridPane();
	private int gatesAmount;

	public MetroStationView(){
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Scene scene = new Scene(this.root, 650, 300);
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
		this.ids = choiceBox;
		this.updateView();
	}

	public void updateGatesAmount(int gates) {
		this.gatesAmount = gates;
		this.updateView();
	}

	public void updateView(){
		GridPane newRoot = new GridPane();
		for(int i = 0; i < this.gatesAmount; i++){
			ChoiceBox choiceBox = new ChoiceBox();
			choiceBox.setItems(this.ids.getItems());
			VBox box = new VBox();
			Text text = new Text(String.format("Gate %s", i + 1));
			Button scan = new Button("scan");
			Button walkgate = new Button("Walk through gate");
			Text error = new Text();
			int finalI = i + 1;
			scan.setOnAction(event -> {
				if(choiceBox.getValue() != null){
					error.setText("scanned gate");
					this.scanCard(finalI,  Integer.parseInt((String) choiceBox.getValue()) -1);
				}
				else{
					error.setText("please choose a card id");
				}
			});
			walkgate.setOnAction(event -> {
				if(choiceBox.getValue() != null){
					error.setText("pressed walk through gate");
					this.walkThroughGate(finalI);
				}
				else{
					error.setText("please choose a card id");
				}

			});
			box.getChildren().addAll(text, choiceBox, scan, walkgate, error);
			newRoot.add(box, i,0);
		}
		Scene scene = new Scene(newRoot, 650, 300);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void scanCard(int gateId,int cardId){
		controller.getGates().get(gateId).scanMetroGate(this.controller.getMetroCard(cardId));
		controller.getGates().get(gateId).scan();
		this.updateRidesAfterScan(controller.getMetroCard(cardId));
	}
	public void walkThroughGate(int gateId){
		controller.getGates().get(gateId).walkThroughGate();
	}

	public void updateRidesAfterScan(MetroCard m) {
		//todo fiks dat de rides wel degelijk upgedate worden op overview, en worden opgeslagen in de txt/xls file
		controller.updateRidesAfterScan(m);
	}
}
