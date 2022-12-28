package domain.controller;

import domain.model.*;
import view.AdminView;
import view.panels.ControlCenterPane;

import java.util.ArrayList;

public class AdminController  implements Observer {
    private MetroFacade facade;
    private AdminView view;

    public AdminController(AdminView view, MetroFacade facade){
        this.view = view;
        this.setSubject(facade);
        this.view.setController(this);

        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }
    @Override
    public void update(MetroEventsEnum e) {
        ArrayList<MetroCard> cards = this.facade.getMetroCardList();
        this.view.updateMetroCardList(cards);
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }
}
