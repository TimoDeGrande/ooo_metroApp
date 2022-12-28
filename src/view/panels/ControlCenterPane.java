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
        this.getChildren().add(button);
    }

    public void initOptions(){
        VBox options = new VBox();
        options.setPadding(new Insets(5));
        GridPane ticketstats = new GridPane();
        try{
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(propertiesPath);
            properties.load(inputStream);
            this.soldtickets =  Integer.parseInt(properties.getProperty("soldtickets"));
            this.moneySoldTickets =  Integer.parseInt(properties.getProperty("moneysoldtickets"));
        } catch (IOException e) {
            Text error = new Text();
            error.setText("Problemen bij het vinden van de vorige statistics file, stats zijn gereset");
        }
        Text sold = new Text("Number of sold tickets: ");
        Label soldtickets = new Label(String.valueOf(this.soldtickets));
        Text euro = new Text("Total € amount of sold tickets: ");
        Label euroamount = new Label(String.valueOf(this.moneySoldTickets));

        //toevoegen aan options
        //als je ticket koopt word er een update gedaan + moneys/file updaten!

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
