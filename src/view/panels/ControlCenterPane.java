package view.panels;

import domain.controller.ControlCenterPaneController;
import javafx.scene.layout.BorderPane;

public class ControlCenterPane extends BorderPane {
    private ControlCenterPaneController controller;

    public ControlCenterPane() {

    }

    public void setController(ControlCenterPaneController controlCenterPaneController) {
        this.controller = controlCenterPaneController;
    }

}
