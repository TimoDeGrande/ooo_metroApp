package view;


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
	public AdminMainPane(){
	    TabPane tabPane = new TabPane();
        MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
	//maak een controlCenterPane aan
	//maak een setupPane aan
        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);

        ControlCenterPane controlCenterPane = new ControlCenterPane();
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);

        SetupPane setupPane = new SetupPane();
        Tab setupTab = new Tab("Setup", setupPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
