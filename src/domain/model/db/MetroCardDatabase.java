package domain.model.db;

import domain.model.MetroCard;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;
import sun.security.util.PendingException;

import java.util.ArrayList;
import java.util.TreeMap;

public class MetroCardDatabase {
    private LoadSaveStrategy loadSaveStrategy;
    private TreeMap<Integer, MetroCard> metrocards;

    public MetroCardDatabase() {
        this.metrocards = new TreeMap<>();
    }

    public void load() {
        throw new PendingException("Implement me.");
    }

    public void save() {
        throw new PendingException("Implement me.");
    }

    public ArrayList<MetroCard> getMetroCardList() {
        throw new PendingException("Implement me.");
    }
}
