package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.MetroFacade;
import domain.model.Observer;
import domain.model.Subject;
import view.MetroStationView;

public class MetroStationViewController implements Observer {
    private MetroFacade facade;
    private MetroStationView view;

    public MetroStationViewController(MetroStationView view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);
    }
    @Override
    public void update(MetroEventsEnum e) {

    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }
}
