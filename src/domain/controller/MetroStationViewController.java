package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.MetroFacade;
import domain.model.Observer;
import domain.model.Subject;
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
    }

    @Override
    public void setSubject(Subject subject) {
        this.facade = (MetroFacade) subject;
    }
}
