package view;


import domain.controller.ControlCenterPaneController;
import domain.controller.MetroCardOverviewPaneController;
import domain.model.MetroFacade;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;
import view.panels.SetupPane;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(MetroFacade facade){
	    TabPane tabPane = new TabPane();
        //MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
        MetroCardOverviewPane overviewPane = new MetroCardOverviewPane();
        MetroCardOverviewPaneController metroCardOverviewPaneController = new MetroCardOverviewPaneController(overviewPane, facade);
        Tab metroCardOverviewTab = new Tab("Metro cards overview",overviewPane);

        //maak een controlCenterPane aan
        ControlCenterPane controlCenterPane = new ControlCenterPane();
        ControlCenterPaneController controlCenterPaneController = new ControlCenterPaneController(controlCenterPane, facade);
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);

        //maak een setupPane aan
        SetupPane setupPane = new SetupPane(facade);
        Tab setupTab = new Tab("Setup", setupPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
