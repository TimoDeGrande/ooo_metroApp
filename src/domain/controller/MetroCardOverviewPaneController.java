package domain.controller;

import domain.model.*;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;

public class MetroCardOverviewPaneController implements Observer {
    private Subject subject;
    private MetroCardOverviewPane view;

    public MetroCardOverviewPaneController(Subject subject) {
        this.view = new MetroCardOverviewPane();
        this.setSubject(subject);
    }
    @Override
    public void update(MetroEventsEnum e) {
        System.out.println("Updated on : " + e.toString() + "!!!");
        ArrayList<MetroCard> cards = ((MetroFacade) this.subject).getMetroCardList();
        this.view.updateMetroCardList(cards);
    }

    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
