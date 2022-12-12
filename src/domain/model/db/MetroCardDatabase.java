package domain.model.db;

import domain.model.MetroCard;
import domain.model.db.loadSaveStrategies.LoadSaveStrategy;
import jxl.read.biff.BiffException;
import sun.security.util.PendingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class MetroCardDatabase {
    private LoadSaveStrategy loadSaveStrategy;
    public static Map<Integer, MetroCard> metroCards;

    public MetroCardDatabase(LoadSaveStrategy loadSaveStrategy) {
        metroCards = new TreeMap<Integer, MetroCard>();
        this.loadSaveStrategy = loadSaveStrategy;
        this.load();

    }


    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
        this.load();

    }


    public void load() {

        try {
            metroCards = loadSaveStrategy.load();
        } catch (BiffException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void save() {
        throw new PendingException("Implement me.");
    }

    public ArrayList<MetroCard> getMetroCardList(){
        return new ArrayList<MetroCard>(metroCards.values());
    }
}