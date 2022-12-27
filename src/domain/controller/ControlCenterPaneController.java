package domain.controller;

import domain.model.*;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private MetroFacade facade;
    private ControlCenterPane view;

    public ControlCenterPaneController(ControlCenterPane view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);
    }
    @Override
    public void update(MetroEventsEnum event) {

    }

    public void openMetroStation() {
        this.facade.openMetroStation();
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }

    public void activateMetroGate(int id) {
        this.facade.activateMetroGate(id);
    }

    public void deactivateMetroGate(int id) {
        this.facade.decativateMetroGate(id);
    }

}
