package view;

import domain.controller.MetroStationViewController;
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

public class MetroStationView {
	private MetroStationViewController controller;
	private Stage stage = new Stage();
	private ChoiceBox ids = new ChoiceBox();
	private GridPane root = new GridPane();
	private int gates = 0;

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

	public void updateGates(int gates) {
		this.gates = gates;
		this.updateView();
	}

	public void updateView(){
		GridPane newRoot = new GridPane();
		for(int i = 0; i < this.gates; i++){
			ChoiceBox choiceBox = new ChoiceBox();
			choiceBox.setItems(this.ids.getItems());
			VBox box = new VBox();
			Text text = new Text(String.format("Gate %s", i + 1));
			Button scan = new Button("scan");
			Button walkgate = new Button("Walk through gate");
			Text error = new Text();
			scan.setOnAction(event -> error.setText("pressed scan"));
			walkgate.setOnAction(event -> error.setText("pressed walk through gate"));
			box.getChildren().addAll(text, choiceBox, scan, walkgate, error);
			newRoot.add(box, i,0);
		}
		Scene scene = new Scene(newRoot, 650, 300);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}



	public GridPane startMetroSim(GridPane root, ArrayList<Integer> metroCardIds, int gates){
		for(int i = 0; i < gates; i++){
			ChoiceBox test = new ChoiceBox();
			test.setItems(FXCollections.observableArrayList(metroCardIds));
			VBox box = new VBox();
			Text text = new Text(String.format("Gate %s", i + 1));
			Button scan = new Button("scan");
			Button walkgate = new Button("Walk through gate");
			Text error = new Text();
			scan.setOnAction(event -> error.setText("pressed scan"));
			walkgate.setOnAction(event -> error.setText("pressed walk through gate"));
			box.getChildren().addAll(text, test, scan, walkgate, error);
			root.add(box, i,0);
		}
		return root;
	}
}
