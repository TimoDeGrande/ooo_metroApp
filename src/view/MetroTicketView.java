package view;

import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import domain.model.MetroCard;
import domain.model.db.MetroCardDatabase;
import domain.model.ticketpricedecorator.TicketPrice;
import domain.model.ticketpricedecorator.TicketPriceFactory;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.NumberStringConverter;
import sun.security.util.PendingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

public class MetroTicketView {
	private Stage stage = new Stage();
	private GridPane root = new GridPane();
	private ChoiceBox ids = new ChoiceBox();
	private MetroTicketViewController controller;
	private final static String propertiesPath = "src/bestanden/application.properties";

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
		choiceBox.setOnAction(event -> startBuyPhase(choiceBox.getSelectionModel().getSelectedIndex()));
		choiceBox.setItems(FXCollections.observableArrayList(metroCardIds));
		this.ids = choiceBox;
		newRoot.add(choiceBox,0,1);
		Scene scene = new Scene(newRoot, 650, 350);
		this.root = newRoot;
		this.stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void startBuyPhase(int cardId){
		GridPane buy = new GridPane();
		Text aantalritten = new Text("Number of rides");
		TextField inputField = new TextField();
		NumberStringConverter converter = new NumberStringConverter();
		TextFormatter<Number> formatter = new TextFormatter<>(converter);
		inputField.setTextFormatter(formatter);
		buy.add(aantalritten,0,0);
		buy.add(inputField,0,1);
		CheckBox stud = new CheckBox("Higher education student");
		buy.add(stud,0,2);
		RadioButton young = new RadioButton("younger than 26 years");
		RadioButton mid = new RadioButton("between 26 and 64 years");
		RadioButton old = new RadioButton("older than 64 years");

		ToggleGroup toggleGroup = new ToggleGroup();
		young.setToggleGroup(toggleGroup);
		mid.setToggleGroup(toggleGroup);
		old.setToggleGroup(toggleGroup);

		HBox ageselect = new HBox();
		ageselect.getChildren().addAll(young,mid,old);

		Button addExtraRides = new Button("Add extra rides to metro card");
		addExtraRides.setOnAction(event -> this.displayPrice(inputField.getText(),young.isSelected(),stud.isSelected(),old.isSelected(), cardId));

		buy.add(ageselect, 0, 3);
		buy.add(addExtraRides, 0, 4);
		this.root.add(buy,0,2);
	}

	public void displayPrice(String rides, boolean young, boolean stud, boolean old, int cardId){
		int ridesInt = 0;
		if(!rides.isEmpty()){
			ridesInt = Integer.parseInt(rides);
		}
		GridPane price = new GridPane();
		Text totalprice = new Text("Total price: ");
		TicketPrice ticketPrice = TicketPriceFactory.createTicketPrice(young, old, stud, this.controller.getMetroCard(cardId));

		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String roundedNumber = decimalFormat.format(ticketPrice.getPrice()*ridesInt);
		Label label = new Label(String.valueOf(roundedNumber) + " €");

		Text explanation = new Text(ticketPrice.getPriceText());

		price.add(totalprice, 0,0);
		price.add(label, 1, 0);
		price.add(explanation, 0, 1);

		Button confirm = new Button("confirm");
		int finalRidesInt = ridesInt;
		confirm.setOnAction(event -> buyMetroCardTickets(this.controller.getMetroCard(cardId), finalRidesInt));
		Button cancel = new Button("cancel");
		cancel.setOnAction(event -> this.cancel());
		price.add(confirm,0,2);
		price.add(cancel,1,2);
		this.root.add(price,0,3);
	}

	public void setController(MetroTicketViewController metroTicketViewController) {
		this.controller = metroTicketViewController;
	}

	public void buyMetroCard(){
		controller.buyMetroCard();
	}

	public void cancel(){
		int size = this.root.getChildren().size();
		this.root.getChildren().remove(size -2, size);
	}

	public void buyMetroCardTickets(MetroCard m, int extraRides) {
		controller.buyMetroTickets(m, extraRides);
		this.cancel();
	}


}