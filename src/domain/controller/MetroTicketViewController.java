package domain.controller;

import domain.model.*;
import view.MetroTicketView;

import java.util.ArrayList;

public class MetroTicketViewController implements Observer {
    private MetroTicketView view;
    private MetroFacade facade;

    public MetroTicketViewController(MetroTicketView view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);

//        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    @Override
    public void update(MetroEventsEnum e) {
        System.out.println(this.getClass().getSimpleName() + " updated on " + e.toString() + "!!!");
        ArrayList<Integer> metroCardIds = this.facade.getMetroCardIdList();
        this.view.updateMetrocardIDList(metroCardIds);
    }

    @Override
    public void setSubject(Subject s) {
        this.facade = (MetroFacade) s;
    }
}
