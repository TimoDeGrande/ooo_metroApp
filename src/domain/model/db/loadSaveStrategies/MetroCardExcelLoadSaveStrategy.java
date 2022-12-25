package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.ExcelLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MetroCardExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public void save(String filename, Map<Integer, MetroCard> cards) {
        super.save(new File(filename), cards);
    }

    @Override
    public Map load(String filename) {
        try {
            return super.load(new File(filename));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

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

    @Override
    protected Object getKey(String[] tokens) {
        return tokens[0];
    }


}
