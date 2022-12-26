package domain.model.db.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    EXCEL("MetroCardExcelLoadSaveStrategy"),
    TEXT("MetroCardTextLoadSaveStrategy");

    private String className;

    LoadSaveStrategyEnum(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }
}
