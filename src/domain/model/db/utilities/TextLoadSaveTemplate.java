package domain.model.db.utilities;

import domain.model.MetroCard;
import sun.security.util.PendingException;

import java.util.ArrayList;

public abstract class TextLoadSaveTemplate {
    public abstract ArrayList<MetroCard> load(String filename);

    public abstract void save(String filename, ArrayList<MetroCard> cards);
}
