package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.ExcelLoadSaveTemplate;
import sun.security.util.PendingException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MetroCardExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public void save(String filename, ArrayList<MetroCard> cards) {
        throw new PendingException("Implement me.");
    }

    @Override
    public ArrayList<MetroCard> load(String filename) {
        throw new PendingException("Implement me.");
    }
}
