package domain.controller;

import domain.model.*;
import view.MetroStationView;

import java.util.ArrayList;

public class MetroStationViewController implements Observer {
    private MetroFacade facade;
    private MetroStationView view;

    public MetroStationViewController(MetroStationView view, MetroFacade facade) {
        this.view = view;
        this.facade = facade;
        this.view.setController(this);

        this.facade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
        this.facade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
    }
    @Override
    public void update(MetroEventsEnum e) {
        ArrayList<Integer> ids = this.facade.getMetroCardIdList();
        this.view.updateIdCheckbox(ids);
        this.view.updateGatesAmount(this.facade.getMetroGateAmount()); //correct line
        this.view.updateGatesList(this.facade.getMetroGates());

    }

    public MetroCard getMetroCard(int id){
        return this.facade.getMetroCardList().get(id);
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }

    public void updateRidesAfterScan(MetroCard m) {
        this.facade.updateRidesAfterScan(m);
    }
}
