package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.ExcelLoadSaveTemplate;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MetroCardExcelLoadSaveStrategy <K,V> extends ExcelLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public Object maakObject(String[] tokens) {
        int month = Integer.parseInt(tokens[1].split("#")[0]);
        int year = Integer.parseInt(tokens[1].split("#")[1]);
        return new MetroCard(Integer.parseInt((tokens[0])), month, year, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    public Object getKey(String[] tokens) {
        return Integer.parseInt(tokens[0]);
    }


    @Override
    public Map<K, V> load() {
        File file = new File("ooo_metroApp/src/bestanden/metrocards.xls");
        Map<K, V> cards = new HashMap<K, V>();
        try {
            cards = load(file);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            throw new RuntimeException(e);
        }

        return cards;

    }

}