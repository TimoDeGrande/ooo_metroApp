package view.panels;


import domain.controller.AdminViewController;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlCenterPane extends GridPane {
    private AdminViewController controller;

    public ControlCenterPane(AdminViewController controller){
        this.controller = controller;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Button button = new Button();
        button.setText("Open metrostation");
        button.setOnAction(event -> this.openMetrostation());
        this.getChildren().add(button);
    }

    public void openMetrostation(){
        System.out.println(controller);
        this.controller.openMetroStation();
    }

    public void setController(AdminViewController adminViewController) {
        System.out.println("setted controller: " + adminViewController);
        this.controller = adminViewController;
    }
}
