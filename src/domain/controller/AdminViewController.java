package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.MetroFacade;
import domain.model.Observer;
import domain.model.Subject;
import view.AdminView;
import view.panels.ControlCenterPane;

public class AdminViewController implements Observer {
    private MetroFacade facade;
    private AdminView view;

    public AdminViewController(MetroFacade facade) {
        this.view = new AdminView(this);
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

}
