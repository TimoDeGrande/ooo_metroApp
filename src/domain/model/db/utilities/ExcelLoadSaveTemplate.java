package domain.model.db.utilities;

import domain.model.MetroCard;

import java.util.ArrayList;

public abstract class ExcelLoadSaveTemplate {
    private ExcelPlugin excelPlugin;

    public abstract void load();

    public abstract void save(String filename, ArrayList<MetroCard> cards);
}
