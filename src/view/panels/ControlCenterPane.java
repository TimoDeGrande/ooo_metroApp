package view.panels;


import domain.controller.ControlCenterPaneController;
import domain.model.MetroGate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

public class ControlCenterPane extends GridPane {
    private ControlCenterPaneController controller;
    private static final String propertiesPath = "src/bestanden/stats.properties";
    private int soldtickets;
    private double moneySoldTickets;
    private ArrayList<String> alertsList = new ArrayList<>();

    public ControlCenterPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button button = new Button();
        button.setText("Open metrostation");
        button.setOnAction(event -> openMetrostation());
        Button close = new Button();
        close.setText("Close metrostation");
        close.setOnAction(event -> closeMetrostation());
        this.add(button, 0,0);
        this.add(close, 1,0);
    }

    public void initOptions(){
        HashMap<Integer, MetroGate> gates = this.controller.getGates();
        if(this.lookup("#optionsbox") != null){
            this.getChildren().remove(this.lookup("#optionsbox"));
        }
        VBox options = new VBox();
        options.setId("optionsbox");
        options.setPadding(new Insets(5));
        GridPane ticketstats = new GridPane();
        try{
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(propertiesPath);
            properties.load(inputStream);
            this.soldtickets =  Integer.parseInt(properties.getProperty("soldtickets"));
            this.moneySoldTickets = DecimalFormat.getNumberInstance().parse(properties.getProperty("moneysoldtickets")).doubleValue();
        } catch (IOException | ParseException e) {
            Text error = new Text();
            error.setText("Problemen bij het vinden van de vorige statistics file, stats zijn gereset");
        }
        Text sold = new Text("Number of sold tickets: ");
        Label soldtickets = new Label(String.valueOf(this.soldtickets));
        Text euro = new Text("Total € amount of sold tickets: ");
        Label euroamount = new Label(String.valueOf(this.moneySoldTickets));

        ticketstats.add(sold, 0, 0);
        ticketstats.add(soldtickets, 1, 0);
        ticketstats.add(euro, 0,1);
        ticketstats.add(euroamount, 1, 1);
        options.getChildren().add(ticketstats);
        this.add(options, 0,1);

        //gates
        if(this.lookup("#gatesinfobox") != null){
            this.getChildren().remove(this.lookup("#gatesinfobox"));
        }
        HBox gatesinfo = new HBox();
        gatesinfo.setId("gatesinfobox");
        for (int key : gates.keySet()) {
            MetroGate value = gates.get(key);
            VBox box = new VBox();
            box.setPadding(new Insets(7));

            box.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5) )));


            if (value.getState().toString().equals("Inactive")) {
                box.setStyle("-fx-background-color: orange;");
            } else {
                box.setStyle("-fx-background-color: white;");
            }
            String printState = value.getState().toString();
            if (printState.equals("Closed")) {
                printState = "Active";
            }
            Text text = new Text(String.format("Gate %s / %s", key, printState));
            Button activate = new Button("Activate");
            activate.setOnAction(event -> {
                value.getState().activate(value);
                this.updateView();
            });
            Button deactivate = new Button("Deactivate");
            deactivate.setOnAction(event -> {
                value.getState().deactivate(value);
                this.updateView();
            });
            Text number = new Text("#scanned cards");
            Label label = new Label(String.valueOf(value.getScans()));
            box.getChildren().addAll(text,activate,deactivate,number,label);
            gatesinfo.getChildren().add(box);
        }
        this.add(gatesinfo,0,2);
        this.initAlerts();
    }

public void updateAlerts(ArrayList<String> alerts){
        this.alertsList = alerts;
        this.initAlerts();
    }

    public void initAlerts(){
        ScrollPane alerts = new ScrollPane();
        ObservableList<String> items = FXCollections.observableArrayList(alertsList);
        Collections.reverse(items);
        ListView<String> listView = new ListView<>();
        listView.setItems(items);
        alerts.setContent(listView);
        this.add(alerts,0,3);
    }

    public void updateView(){
        controller.updateGate();
    }

    public void openMetrostation(){
        this.controller.openMetroStation();
    }
    public void closeMetrostation(){
        this.controller.closeMetroStation();
    }

    public void setController(ControlCenterPaneController controlCenterPaneController) {
        this.controller = controlCenterPaneController;
    }
    public void close(){
        ((Stage)this.getScene().getWindow()).close();
    }
}