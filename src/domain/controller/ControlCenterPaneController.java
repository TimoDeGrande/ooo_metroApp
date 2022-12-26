package domain.controller;

import domain.model.MetroEventsEnum;
import domain.model.Observer;
import domain.model.Subject;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private Subject subject;
    private ControlCenterPane view;

    public ControlCenterPaneController(Subject subject) {
        this.view = new ControlCenterPane();
        setSubject(subject);
    }
    @Override
    public void update(MetroEventsEnum event) {

    }

    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
