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
import view.panels.MetroCardOverviewPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class MetroFacade implements Subject {
    private MetroCardDatabase metroCardDatabase;
    private MetroStation station;

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
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();
        int id = metroCardDatabase.getMetroCardIdList().size() + 1;
        this.metroCardDatabase.add(new MetroCard(id, month, year, 0,0));
        this.metroCardDatabase.save();
        this.updateObservers(MetroEventsEnum.BUY_METROCARD);

    }

    public void buyMetroCardTickets(MetroCard m, int rides) {
        int currentlyAvailableRides = m.getAvailableRides();
        int newRidesAmount = currentlyAvailableRides + rides;
        m.setAvailableRides(newRidesAmount);
        this.metroCardDatabase.save();
        this.updateObservers(MetroEventsEnum.BUY_METROCARD);
        this.updateObservers(MetroEventsEnum.BUY_METROCARD_TICKETS);
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

    public int getMetroGateAmount() {
        return this.station.getMetroGateAmount();
    }

    public HashMap<Integer, MetroGate> getMetroGates(){
        return this.station.getGates();
    }

    public void updateMetroGatesAmount(ArrayList<MetroGate> gates){
        this.station.updateMetroGatesAmount(gates);
    }


    public void updateGates(){
        this.updateObservers(MetroEventsEnum.BUY_METROCARD);
    }

    public void updateRidesAfterScan(MetroCard m) {
        int newRidesAmount = m.getAvailableRides() - 1;
        m.setAvailableRides(newRidesAmount);
        this.metroCardDatabase.save();
        this.updateObservers(MetroEventsEnum.BUY_METROCARD);

    }
}
