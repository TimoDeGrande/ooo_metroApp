package domain.model.db;

import domain.model.MetroCard;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MetroCardDatabase {
    private LoadSaveStrategy loadSaveStrategy;
    private TreeMap<Integer, MetroCard> metrocards;

    public MetroCardDatabase() {
        this.metrocards = new TreeMap<>();
    }

    public void load(String filename) {
        this.metrocards = (TreeMap<Integer, MetroCard>) this.loadSaveStrategy.load(filename);
    }

    public void save(String filename) {
        this.loadSaveStrategy.save(filename, this.metrocards);
    }

    public ArrayList<MetroCard> getMetroCardList() {
        return (ArrayList<MetroCard>) this.metrocards.values();
    }
}
