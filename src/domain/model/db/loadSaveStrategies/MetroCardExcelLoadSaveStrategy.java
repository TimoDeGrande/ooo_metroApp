package domain.model.db.loadSaveStrategies;

import domain.model.db.utilities.ExcelLoadSaveTemplate;
import sun.security.util.PendingException;

public class MetroCardExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public void save() {
        throw new PendingException("Implement me.");
    }

    @Override
    public void load() {
        throw new PendingException("Implement me.");
    }
}
