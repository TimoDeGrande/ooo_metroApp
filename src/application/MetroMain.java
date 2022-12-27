package application;

import domain.controller.ControlCenterPaneController;
import domain.controller.MetroCardOverviewPaneController;
import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import domain.model.MetroCard;
import domain.model.MetroFacade;
import domain.model.db.loadSaveStrategies.*;
import domain.model.ticketpricedecorator.*;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;
import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class MetroMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        MetroFacade facade = new MetroFacade();
        AdminView adminView = new AdminView(facade);

         MetroTicketView metroTicketView = new MetroTicketView();
         MetroStationView metroStationView = new MetroStationView();
         MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
//
         MetroTicketViewController metroTicketViewController = new MetroTicketViewController(metroTicketView, facade);
         MetroStationViewController metroStationViewController = new MetroStationViewController(metroStationView, facade);
         MetroCardOverviewPaneController metroCardOverviewPaneController = new MetroCardOverviewPaneController(metroCardOverviewPane, facade);

        MetroCard m = new MetroCard(1, 12, 2022, 3, 10);
        TicketPrice price = TicketPriceFactory.createTicketPrice(true, false, true, m);
        System.out.println(price.getPrice());
        System.out.println(price.getPriceText());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
