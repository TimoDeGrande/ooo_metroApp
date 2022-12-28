package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.MetroFacade;
import domain.model.Observer;
import domain.model.Subject;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private MetroFacade facade;
    private ControlCenterPane view;

    public ControlCenterPaneController(ControlCenterPane view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);

        this.facade.addObserver(MetroEventsEnum.BUY_METROCARD_TICKETS, this);
    }
    @Override
    public void update(MetroEventsEnum event) {
        this.view.initOptions();
    }

    public void openMetroStation() {
        this.facade.openMetroStation();
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }

}
