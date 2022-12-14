package view;


import domain.controller.MetroTicketViewController;
import domain.model.ticketpricedecorator.TicketPrice;

import domain.model.ticketpricedecorator.TicketPriceFactory;
import javafx.collections.FXCollections;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.NumberStringConverter;


import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

public class MetroTicketView {
	private Stage stage = new Stage();
	private GridPane root = new GridPane();
	private MetroTicketViewController controller;
	private static final String statsPath = "src/bestanden/stats.properties";


	public MetroTicketView(){
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Scene scene = new Scene(this.root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateIdCheckbox(ArrayList<Integer> metroCardIds) {
		Button button = new Button("Buy card");
		button.setOnAction(event -> this.buyMetroCard());
		this.root.add(button, 0,0);
		GridPane newRoot = new GridPane();
		newRoot.add(this.root.getChildren().get(0), 0,0);
		ChoiceBox choiceBox = new ChoiceBox();
		choiceBox.setOnAction(event -> startBuyPhase(choiceBox.getSelectionModel().getSelectedIndex()));
		choiceBox.setItems(FXCollections.observableArrayList(metroCardIds));
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
		stud.setDisable(!this.controller.getDiscounts().contains("STUDENTDISCOUNT"));
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
		if(this.root.lookup("#pricebox") != null){
			this.root.getChildren().remove(this.root.lookup("#pricebox"));
		}
		int ridesInt = 0;
		if(!rides.isEmpty()){
			ridesInt = Integer.parseInt(rides);
		}
		GridPane price = new GridPane();
		price.setId("pricebox");
		Text totalprice = new Text("Total price: ");
		TicketPrice ticketPrice = TicketPriceFactory.createTicketPrice(young, old, stud, this.controller.getMetroCard(cardId));

		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String roundedNumber = decimalFormat.format(ticketPrice.getPrice()*ridesInt);
		Label label = new Label(String.valueOf(roundedNumber) + " ???");

		Text explanation = new Text(ticketPrice.getPriceText());

		price.add(totalprice, 0,0);
		price.add(label, 1, 0);
		price.add(explanation, 0, 1);

		Button confirm = new Button("confirm");
		int finalRidesInt = ridesInt;
		int finalRidesInt1 = ridesInt;
		confirm.setOnAction(event -> buyMetroCardTickets(cardId, finalRidesInt, ticketPrice.getPrice()* finalRidesInt1));
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
		this.controller.cancel();
	}

	public void buyMetroCardTickets(int cardId, int extraRides, double price) {
		try {
			Properties properties = new Properties();
			FileInputStream inputStream = new FileInputStream(statsPath);
			properties.load(inputStream);

			int soldtickets = Integer.parseInt(properties.getProperty("soldtickets"));
			double moneysoldtickets = DecimalFormat.getNumberInstance().parse(properties.getProperty("moneysoldtickets")).doubleValue();



			int solticketsCalc = soldtickets + extraRides;
			double moneysoldticketsCalc = moneysoldtickets + price;

			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String roundedNumber = decimalFormat.format(moneysoldticketsCalc);

			properties.setProperty("soldtickets", String.valueOf(solticketsCalc));
			properties.setProperty("moneysoldtickets", String.valueOf(roundedNumber));

			FileOutputStream outputStream = new FileOutputStream(statsPath);
			properties.store(outputStream, "");
		}
		catch (IOException | ParseException exc) {
			exc.printStackTrace();
		}
		controller.buyMetroTickets(this.controller.getMetroCard(cardId), extraRides);
		this.cancel();
	}
	public void close(){
		this.stage.close();
	}


}