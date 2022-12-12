package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.TextLoadSaveTemplate;
import sun.security.util.PendingException;

public class MetroCardTextLoadSaveStrategy extends TextLoadSaveTemplate implements LoadSaveStrategy {
    public Object maakObject(String[] tokens) {
        int month = Integer.parseInt(tokens[1].split("#")[0]);
        int year = Integer.parseInt(tokens[1].split("#")[1]);
        return new MetroCard(Integer.parseInt((tokens[0])), month, year, Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
    }
    @Override
    public Object getKey(String[] tokens){
        return Integer.parseInt(tokens[0]);
    }
}
