package domain.model.db.loadSaveStrategies;

import sun.security.util.PendingException;

public interface LoadSaveStrategy {
    void save();

    void load();
}
