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

        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
        this.facade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
    }

    @Override
    public void update(MetroEventsEnum e) {
        ArrayList<Integer> metroCardIds = this.facade.getMetroCardIdList();
        this.view.updateIdCheckbox(metroCardIds);
    }

    @Override
    public void setSubject(Subject s) {
        this.facade = (MetroFacade) s;
    }
}
