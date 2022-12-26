package domain.controller;

import domain.model.*;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private MetroFacade facade;
    private MetroCardOverviewPane view;

    public MetroCardOverviewPaneController(MetroCardOverviewPane view, MetroFacade facade) {
        this.view = view;
        this.setSubject(facade);
        this.view.setController(this);

        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }
    @Override
    public void update(MetroEventsEnum e) {
        System.out.println(this.getClass().getSimpleName() + " updated on " + e.toString() + "!!!");
        ArrayList<MetroCard> cards = this.facade.getMetroCardList();
        this.view.updateMetroCardList(cards);
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }
}
