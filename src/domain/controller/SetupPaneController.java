package domain.controller;

import domain.model.*;
import view.panels.SetupPane;

import java.util.ArrayList;

public class SetupPaneController implements Observer {
    private MetroFacade facade;
    private SetupPane view;

    public SetupPaneController(SetupPane view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);

        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }
    @Override
    public void update(MetroEventsEnum event) {
        this.view.initSetup();
    }

    @Override
    public void close(MetroEventsEnum e) {

    }

    public void updateGates(int gates){
        this.facade.updateGates(gates);
        this.facade.updateObservers(MetroEventsEnum.BUY_METROCARD);
    }
    public ArrayList<String> getMetroDiscountList(){
        return this.facade.getMetroTicketDiscountList();
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }
}
