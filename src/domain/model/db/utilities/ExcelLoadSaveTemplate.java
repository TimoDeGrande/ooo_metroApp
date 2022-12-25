package domain.model.db.utilities;

import domain.model.MetroCard;

import java.util.ArrayList;
import java.util.Map;

public abstract class ExcelLoadSaveTemplate {
    private ExcelPlugin excelPlugin;

    public abstract Map<Integer, MetroCard> load(String filename);

    public abstract void save(String filename, Map<Integer, MetroCard> cards);
}
