package view.panels;


import domain.controller.ControlCenterPaneController;
import domain.model.MetroEventsEnum;
import domain.model.MetroGate;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Properties;

public class ControlCenterPane extends GridPane {
    private ControlCenterPaneController controller;
    private static final String propertiesPath = "src/bestanden/stats.properties";
    private int soldtickets;
    private double moneySoldTickets;

    public ControlCenterPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button button = new Button();
        button.setText("Open metrostation");
        button.setOnAction(event -> openMetrostation());
        this.add(button, 0,0);
    }

    public void initOptions(){
        HashMap<Integer, MetroGate> gates = this.controller.getGates();
        System.out.println("t");
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
            Text text = new Text(String.format("Gate %s / %s", key, value.getState().toString()));
            Button activate = new Button("Activate");
            activate.setOnAction(event -> {
                value.getState().activate(value);
                this.updateView();
                System.out.println(value.getState().toString());
            });
            Button deactivate = new Button("Deactivate");
            deactivate.setOnAction(event -> {
                value.getState().deactivate(value);
                this.updateView();
                System.out.println(value.getState().toString());
            });
            Text number = new Text("#scanned cards");
            Label label = new Label(String.valueOf(value.getScans()));
            box.getChildren().addAll(text,activate,deactivate,number,label);
            gatesinfo.getChildren().add(box);
        }
        this.add(gatesinfo,0,2);
    }

    public void updateView(){
        controller.update(MetroEventsEnum.UPDATE_GATE);
    }

    public void openMetrostation(){
        this.controller.openMetroStation();
    }

    public void setController(ControlCenterPaneController controlCenterPaneController) {
        System.out.println("setted controller: " + controlCenterPaneController);
        this.controller = controlCenterPaneController;
    }
}
