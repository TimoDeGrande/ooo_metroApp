package view.panels;


import domain.controller.ControlCenterPaneController;
import javafx.scene.layout.BorderPane;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlCenterPane extends GridPane {
    ControlCenterPaneController controller;


    public ControlCenterPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        Button button = new Button();
        button.setText("open metrostation");
        button.setOnAction(event -> this.openMetrostation(event));
        this.getChildren().add(button);
    }

    private void openMetrostation(ActionEvent e){
        System.out.println("testje");
    }

    public void setController(ControlCenterPaneController controlCenterPaneController) {
        this.controller = controlCenterPaneController;
    }
}
