package domain.model.db.loadSaveStrategies;

import domain.model.db.utilities.TextLoadSaveTemplate;
import sun.security.util.PendingException;

public class MetroCardTextLoadSaveStrategy extends TextLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public void save() {
        throw new PendingException("Implement me.");
    }

    @Override
    public void load() {
        throw new PendingException("Implement me.");
    }
}
