package view.panels;


import domain.controller.ControlCenterPaneController;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
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
        this.initOptions();
    }

    public void initOptions(){
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
        VBox gatesinfo = new VBox();
        gatesinfo.setId("gatesinfobox");


    }

    public void openMetrostation(){
        System.out.println(controller);
        this.controller.openMetroStation();
    }

    public void setController(ControlCenterPaneController controlCenterPaneController) {
        System.out.println("setted controller: " + controlCenterPaneController);
        this.controller = controlCenterPaneController;
    }
}
