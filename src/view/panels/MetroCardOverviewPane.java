package view.panels;


import domain.controller.MetroCardOverviewPaneController;
import domain.model.MetroCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class MetroCardOverviewPane extends GridPane{
	private TableView<MetroCard> table = new TableView<>();
	private ObservableList<MetroCard> cards;
	private MetroCardOverviewPaneController controller;
	
	
	public MetroCardOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("List of Metro cards:"), 0, 0, 1, 1);
		this.refresh();
		TableColumn<MetroCard, Integer> colID = new TableColumn<MetroCard, Integer>("card ID");
		colID.setMinWidth(300);
		colID.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("ID"));
		TableColumn<MetroCard, Integer> colMonth = new TableColumn<MetroCard, Integer>("month");
		colMonth.setMinWidth(100);
		colMonth.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("month"));
		TableColumn<MetroCard, Integer> colYear = new TableColumn<MetroCard, Integer>("Year");
		colYear.setMinWidth(100);
		colYear.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("year"));
		TableColumn<MetroCard, Integer> colAvailableRides = new TableColumn<MetroCard, Integer>("available rides");
		colAvailableRides.setMinWidth(100);
		colAvailableRides.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("available rides"));
		TableColumn<MetroCard, Integer> colUsedRides = new TableColumn<MetroCard, Integer>("Total used rides");
		colUsedRides.setMinWidth(100);
		colUsedRides.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("total used rides"));
		table.getColumns().addAll(colID, colMonth, colYear, colAvailableRides, colUsedRides);

		this.getChildren().add(table);
	}
	public void refresh(){
		cards = FXCollections.observableArrayList(); // in observable de metrocards ophalen
		table.setItems(cards);
		table.refresh();
	}

	public void updateMetroCardList(ArrayList<MetroCard> cards) {

		this.cards.remove(0, this.cards.size());
		this.cards.addAll(cards);
		this.refresh();
	}

	public void setController(MetroCardOverviewPaneController metroCardOverviewPaneController) {
		this.controller = metroCardOverviewPaneController;

		table.setItems(FXCollections.observableArrayList(cards));
		table.refresh();
		//todo has to be implemented: replace cards in this class to cards that are given
		//throw new PendingException("Implement me!!");
	}
}
