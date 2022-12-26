package view;

import domain.controller.AdminViewController;
import domain.controller.MetroStationViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;	

public class AdminView {
	private Stage stage = new Stage();
	private AdminViewController controller;
		
	public AdminView(AdminViewController controller){
		this.controller = controller;
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(660);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 690, 680);
		AdminMainPane borderPane = new AdminMainPane(controller);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void setController(AdminViewController adminViewController) {
		this.controller = adminViewController;
	}
}
