package domain.controller;

import domain.model.Observer;
import domain.model.Subject;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private Subject subject;
    private ControlCenterPane view = new ControlCenterPane();

    public ControlCenterPaneController(Subject subject) {
        this.view = new ControlCenterPane();
        setSubject(subject);
    }
    @Override
    public void update() {

    }

    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
