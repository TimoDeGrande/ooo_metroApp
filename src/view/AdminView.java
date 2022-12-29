package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;
import view.panels.SetupPane;

public class AdminView {
	private Stage stage = new Stage();		
		
	public AdminView(MetroCardOverviewPane metroCardOverviewPane, ControlCenterPane controlCenterPane, SetupPane setupPane){
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(660);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 690, 680);

		BorderPane borderPane = new AdminMainPane(metroCardOverviewPane, controlCenterPane, setupPane);


		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
	public void close(){
		this.stage.close();
	}
}
