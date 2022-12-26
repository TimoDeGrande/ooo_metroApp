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
import java.util.HashMap;

public class MetroFacade implements Subject {
    private MetroCardDatabase metroCardDatabase;

    public MetroFacade() {
        for (MetroEventsEnum eventType : MetroEventsEnum.values()) {
            observers.put(eventType, new ArrayList<Observer>());
        }
        this.metroCardDatabase = new MetroCardDatabase();

    }

    public void openMetroStation() {
        LoadSaveStrategy l = LoadSaveStrategyFactory.createLoadSaveStrategy();
        this.metroCardDatabase.setLoadSaveStrategy(l);
        this.metroCardDatabase.load();
        this.updateObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public void buyMetroCard() {
        //todo add metrocard to database

    }


    public ArrayList<MetroCard> getMetroCardList() {
        return this.metroCardDatabase.getMetroCardList();
    }

    public ArrayList<Integer> getMetroCardIdList() {
        return this.metroCardDatabase.getMetroCardIdList();
    }



}
