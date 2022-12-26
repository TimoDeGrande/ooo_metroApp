package view;


import domain.controller.AdminViewController;
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
    AdminViewController controller;

	public AdminMainPane(AdminViewController controller){
	    TabPane tabPane = new TabPane();
        MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
	//maak een controlCenterPane aan
	//maak een setupPane aan

        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);

        System.out.println(controller);
        ControlCenterPane controlCenterPane = new ControlCenterPane(controller);
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);

        SetupPane setupPane = new SetupPane();
        Tab setupTab = new Tab("Setup", setupPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
    public void setController(AdminViewController adminViewController) {
        this.controller = adminViewController;
    }
}
