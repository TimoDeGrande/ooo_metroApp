package domain.model.db.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    EXCEL("MetroCardExcelLoadSaveStrategy"),
    TEXT("MetroCardTextLoadSaveStrategy");

    String className;

    LoadSaveStrategyEnum(String className) {}

    public String getClassName() {
        return this.className;
    }
}
