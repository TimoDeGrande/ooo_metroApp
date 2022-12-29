package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;
import view.panels.SetupPane;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(MetroCardOverviewPane metroCardOverviewPane, ControlCenterPane controlCenterPane, SetupPane setupPane){
	    TabPane tabPane = new TabPane();
        //MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);

        //maak een controlCenterPane aan
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);

        //maak een setupPane aan

        Tab setupTab = new Tab("Setup", setupPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
