package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.Observer;
import domain.model.Subject;
import view.MetroTicketView;

public class MetroTicketViewController implements Observer {
    private MetroTicketView view;
    private Subject subject;

    public MetroTicketViewController(Subject subject) {
        this.view = new MetroTicketView();
        this.setSubject(subject);
    }

    @Override
    public void update(MetroEventsEnum e) {

    }

    @Override
    public void setSubject(Subject s) {
        this.subject = s;
    }
}
