package domain.model;

import domain.controller.ControlCenterPaneController;
import domain.controller.MetroCardOverviewPaneController;
import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import domain.model.db.MetroCardDatabase;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyEnum;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyFactory;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroFacade implements Subject {
    private MetroCardDatabase metroCardDatabase;

    private MetroTicketViewController metroTicketViewController;
    private MetroStationViewController metroStationViewController;
    private MetroCardOverviewPaneController metroCardOverviewPaneController;
    private ControlCenterPaneController controlCenterPaneController;

    public MetroFacade() {
        this.metroCardDatabase = new MetroCardDatabase();
        this.metroTicketViewController = new MetroTicketViewController(this);
        this.metroStationViewController = new MetroStationViewController(this);
        this.metroCardOverviewPaneController = new MetroCardOverviewPaneController(this);
        this.controlCenterPaneController = new ControlCenterPaneController(this);
    }

    public void openMetroStation() {
        LoadSaveStrategy l = LoadSaveStrategyFactory.createLoadSaveStrategy();
        this.metroCardDatabase.setLoadSaveStrategy(l);
        this.metroCardDatabase.load();

    }

    public ArrayList<MetroCard> getMetroCardList() {
        return this.metroCardDatabase.getMetroCardList();
    }

    public ArrayList<Integer> getMetroCardIdList() {
        return this.metroCardDatabase.getMetroCardIdList();
    }
}
