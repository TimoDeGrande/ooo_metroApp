package view.panels;


import domain.model.MetroCard;
import domain.model.db.MetroCardDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MetroCardOverviewPane extends GridPane{
	private TableView<MetroCard> table = new TableView<MetroCard>();
	private ObservableList<MetroCard> cards;

	private MetroCardDatabase db;
	
	public MetroCardOverviewPane(MetroCardDatabase db) {
		this.db = db;
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("List of Metro cards:"), 0, 0, 1, 1);
		this.refresh();
		TableColumn<MetroCard, Integer> colID = new TableColumn<MetroCard, Integer>("card ID");
		colID.setMinWidth(300);
		colID.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("CardID"));
		TableColumn<MetroCard, Integer> colMonth = new TableColumn<MetroCard, Integer>("month");
		colMonth.setMinWidth(100);
		colMonth.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("month"));
		TableColumn<MetroCard, Integer> colYear = new TableColumn<MetroCard, Integer>("Year");
		colYear.setMinWidth(100);
		colYear.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("year"));
		TableColumn<MetroCard, Integer> colAvailableRides = new TableColumn<MetroCard, Integer>("available rides");
		colAvailableRides.setMinWidth(100);
		colAvailableRides.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("AvailableRides"));
		TableColumn<MetroCard, Integer> colUsedRides = new TableColumn<MetroCard, Integer>("Total used rides");
		colUsedRides.setMinWidth(100);
		colUsedRides.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("TotalUsedRides"));
		table.getColumns().addAll(colID, colMonth, colYear, colAvailableRides, colUsedRides);
		this.getChildren().add(table);
	}
	public void refresh(){
		cards = FXCollections.observableArrayList(db.getMetroCardList()); // in observable de metrocards ophalen
		table.setItems(cards);
		table.refresh();
	}
}
