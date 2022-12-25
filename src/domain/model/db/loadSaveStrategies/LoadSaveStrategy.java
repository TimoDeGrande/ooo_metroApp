package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import sun.security.util.PendingException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public interface LoadSaveStrategy {
    void save(String filename, Map<Integer, MetroCard> cards);

    Map<Integer, MetroCard> load(String filename);
}
