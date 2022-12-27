package domain.model;

import domain.controller.ControlCenterPaneController;
import domain.controller.MetroCardOverviewPaneController;
import domain.controller.MetroStationViewController;
import domain.controller.MetroTicketViewController;
import domain.model.db.MetroCardDatabase;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyEnum;
import domain.model.db.loadSaveStrategies.LoadSaveStrategyFactory;
import domain.model.ticketpricedecorator.TicketPriceFactory;
import view.MetroStationView;
import view.MetroTicketView;
import view.panels.MetroCardOverviewPane;

import java.util.ArrayList;
import java.util.HashMap;

public class MetroFacade implements Subject {
    private MetroCardDatabase metroCardDatabase;
    private MetroStation station;

    private MetroTicketView gate;

    public MetroFacade() {
        for (MetroEventsEnum eventType : MetroEventsEnum.values()) {
            observers.put(eventType, new ArrayList<Observer>());
        }
        this.metroCardDatabase = new MetroCardDatabase();
        this.station = new MetroStation();

    }

    public void openMetroStation() {
        LoadSaveStrategy l = LoadSaveStrategyFactory.createLoadSaveStrategy();
        this.metroCardDatabase.setLoadSaveStrategy(l);
        this.metroCardDatabase.load();
        this.updateObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public void buyMetroCard() {
        //todo add metrocard to database q: welke parameters?
//        this.metroCardDatabase.add(new MetroCard(????));

    }

    public void buyMetroCardTickets(MetroCard m) {
        //todo q: hoeveel tickets moeten erbij komen?
    }

    public void scanMetroGate(int metroCardId, int metroGateId) {
        MetroCard card = this.metroCardDatabase.get(metroCardId);
        this.station.scanMetroGate(metroGateId, card);
    }

    public double getPrice(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard m) {
        return TicketPriceFactory.createTicketPrice(is24Min, is64Plus, isStudent, m).getPrice();
    }

    public String getPriceText(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard m) {
        return TicketPriceFactory.createTicketPrice(is24Min, is64Plus, isStudent, m).getPriceText();
    }

    public ArrayList<String> getMetroTicketDiscountList() {
        return TicketPriceFactory.getAllActiveDiscounts();
    }

    public ArrayList<MetroCard> getMetroCardList() {
        return this.metroCardDatabase.getMetroCardList();
    }

    public ArrayList<Integer> getMetroCardIdList() {
        return this.metroCardDatabase.getMetroCardIdList();
    }

    public void activateMetroGate(int id) {
        this.station.activateMetroGate(id);
    }

    public void decativateMetroGate(int id) {
        this.station.deactivateMetroGate(id);
    }



}
