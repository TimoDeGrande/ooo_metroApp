package domain.model.db.utilities;

public abstract class ExcelLoadSaveTemplate {
    private ExcelPlugin excelPlugin;

    public abstract void load();

    public abstract void save();
}
