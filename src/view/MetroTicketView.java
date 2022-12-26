package view;

import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
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

    public void updateMetrocardIDList(ArrayList<Integer> metroCardIds) {
		//todo make refresh of choicebox with metrocard id's
//		throw new PendingException("Implement me!");
    }

	public void setController(MetroTicketViewController metroTicketViewController) {
		this.controller = metroTicketViewController;
	}
}
