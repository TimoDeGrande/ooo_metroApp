package view;

import domain.controller.MetroStationViewController;
import domain.model.metroGateStates.Alert;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class MetroStationView {
	private MetroStationViewController controller;
	private Stage stage = new Stage();
	private ChoiceBox ids = new ChoiceBox();
	private GridPane root = new GridPane();

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

	public void updateView(){
		GridPane newRoot = new GridPane();
		for(int i = 0; i < this.controller.getGates().size(); i++){
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
			if(this.controller.getGates().get(finalI).getState().toString().equals("Inactive")){
				choiceBox.setDisable(true);
				walkgate.setDisable(true);
				scan.setDisable(true);
			}
			else{
				choiceBox.setDisable(false);
				walkgate.setDisable(false);
				scan.setDisable(false);
			}
			box.getChildren().addAll(text, choiceBox, scan, walkgate, error);
			newRoot.add(box, i,0);
		}
		Scene scene = new Scene(newRoot, 650, 300);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void scanCard(int gateId,int cardId){
		try{
			controller.getGates().get(gateId).scanMetroGate(this.controller.getMetroCard(cardId));
			controller.getGates().get(gateId).scan();
		}
		catch (Alert alert){
			this.controller.updateAlerts(alert.getMessage());
		}
		this.updateRidesAfterScan(cardId);
	}
	public void walkThroughGate(int gateId){
		try{
			controller.getGates().get(gateId).walkThroughGate();
		}
		catch (Alert alert){
			this.controller.updateAlerts(alert.getMessage());
		}
	}

	public void updateRidesAfterScan(int cardId) {
		controller.updateRidesAfterScan(controller.getMetroCard(cardId));
	}
	public void close(){
		this.stage.close();
	}
}
