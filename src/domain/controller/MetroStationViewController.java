package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.Observer;
import domain.model.Subject;
import view.MetroStationView;

public class MetroStationViewController implements Observer {
    private Subject subject;
    private MetroStationView view;

    public MetroStationViewController(Subject subject) {
        this.view = new MetroStationView();
        this.setSubject(subject);
    }
    @Override
    public void update(MetroEventsEnum e) {

    }

    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
