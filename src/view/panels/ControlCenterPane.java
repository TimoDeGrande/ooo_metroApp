package view.panels;


import domain.controller.ControlCenterPaneController;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlCenterPane extends GridPane {
    private ControlCenterPaneController controller;

    public ControlCenterPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button button = new Button();
        button.setText("Open metrostation");
        button.setOnAction(event -> openMetrostation());
        this.getChildren().add(button);
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
