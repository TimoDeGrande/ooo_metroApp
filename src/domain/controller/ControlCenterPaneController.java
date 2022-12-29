package domain.controller;

import domain.model.*;
import view.panels.ControlCenterPane;

import java.util.HashMap;

public class ControlCenterPaneController implements Observer {
    private MetroFacade facade;
    private ControlCenterPane view;

    public ControlCenterPaneController(ControlCenterPane view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);

        this.facade.addObserver(MetroEventsEnum.BUY_METROCARD_TICKETS, this);
        this.facade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
        this.facade.addObserver(MetroEventsEnum.UPDATE_GATE, this);
        this.facade.addObserver(MetroEventsEnum.NEW_ALERT, this);
        this.facade.addObserver(MetroEventsEnum.SCAN_METROCARD, this);
        this.facade.addObserver(MetroEventsEnum.CLOSE_METROSTATION, this);
    }
    @Override
    public void update(MetroEventsEnum event) {
        this.view.initOptions();
        this.view.updateAlerts(this.facade.getAlerts());
    }

    @Override
    public void close(MetroEventsEnum e) {
        this.view.close();
    }

    public HashMap<Integer, MetroGate> getGates(){
        return this.facade.getMetroGates();
    }

    public void openMetroStation() {
        this.facade.openMetroStation();
    }
    public void closeMetroStation(){
        this.facade.close(MetroEventsEnum.CLOSE_METROSTATION);
    }
    public void updateGate(){
        this.facade.updateObservers(MetroEventsEnum.UPDATE_GATE);
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }

}
