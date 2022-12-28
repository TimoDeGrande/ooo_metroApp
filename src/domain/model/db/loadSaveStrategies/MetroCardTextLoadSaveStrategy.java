package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.ExcelLoadSaveTemplate;
import domain.model.db.utilities.TextLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MetroCardTextLoadSaveStrategy extends TextLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    protected MetroCard maakObject(String[] tokens) {
        return new MetroCard(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1].split("#")[0]),
                Integer.parseInt(tokens[1].split("#")[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }


    @Override
    protected String maakSchrijfbareString(Object o) {
        MetroCard m = (MetroCard) o;
        return m.getCardID() + ";" + m.getMonth() + "#" + m.getYear() + ";" + m.getAvailableRides() + ";" + m.getTotalUsedRides();
    }

    protected String getKey(String[] tokens) {
        return tokens[0];
    }

    @Override
    public void save(Map<Integer, MetroCard> cards) {
        super.save(new File(filename), cards);

    }

    @Override
    public Map load() {
        try {
            return super.load(new File(TextLoadSaveTemplate.filename));
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
