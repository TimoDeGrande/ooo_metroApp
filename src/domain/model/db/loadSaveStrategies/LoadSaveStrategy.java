package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import sun.security.util.PendingException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface LoadSaveStrategy {
    void save(String filename, ArrayList<MetroCard> cards);

    ArrayList<MetroCard> load(String filename);
}
