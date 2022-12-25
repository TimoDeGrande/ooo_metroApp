package domain.controller;

import domain.model.Observer;
import domain.model.Subject;
import view.panels.MetroCardOverviewPane;

public class MetroCardOverviewPaneController implements Observer {
    private Subject subject;
    private MetroCardOverviewPane view;

    public MetroCardOverviewPaneController(Subject subject) {
        this.view = new MetroCardOverviewPane();
        this.setSubject(subject);
    }
    @Override
    public void update() {

    }

    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
