package view;

import domain.controller.MetroStationViewController;
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

	public void updateIdCheckbox(ArrayList<Integer> metroCardIds, int gates) {
		GridPane root = new GridPane();
		root = startMetroSim(root, metroCardIds, gates);
		Scene scene = new Scene(root, 650, 300);
		this.stage.setScene(scene);
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
