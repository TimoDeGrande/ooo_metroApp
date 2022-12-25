package domain.model.db.utilities;

import domain.model.MetroCard;

import java.util.ArrayList;

public abstract class ExcelLoadSaveTemplate {
    private ExcelPlugin excelPlugin;

    public abstract ArrayList<MetroCard> load(String filename);

    public abstract void save(String filename, ArrayList<MetroCard> cards);
}
