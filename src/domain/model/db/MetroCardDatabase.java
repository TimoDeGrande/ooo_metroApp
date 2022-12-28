package domain.model.db;

import domain.model.MetroCard;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MetroCardDatabase {
    private LoadSaveStrategy loadSaveStrategy;
    private HashMap<Integer, MetroCard> metrocards;

    public MetroCardDatabase() {
        this.metrocards = new HashMap<>();
    }

    public void add(MetroCard m) {
        this.metrocards.put(m.getCardID(), m);
    }
    public void load() {
        this.metrocards = (HashMap<Integer, MetroCard>) this.loadSaveStrategy.load();
    }

    public void save(String filename) {
        this.loadSaveStrategy.save(filename, this.metrocards);
    }

    public ArrayList<MetroCard> getMetroCardList() {
        return new ArrayList<>(this.metrocards.values());
    }

    public ArrayList<Integer> getMetroCardIdList() {
        return new ArrayList<>(this.metrocards.keySet());
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public MetroCard get(int metroCardId) {
        return this.metrocards.get(metroCardId);
    }
}
