package domain.model.db.loadSaveStrategies;

import sun.security.util.PendingException;

import java.lang.reflect.Constructor;

public class LoadSaveStrategyFactory {

    private final static String location = "domain.model.db.loadSaveStrategies.";

    public static LoadSaveStrategy createLoadSaveStrategy(String type, Object... args) {
        LoadSaveStrategy instance = null;

        //opzoeken klassenaam in enum
        LoadSaveStrategyEnum loadSaveStrategyEnum = LoadSaveStrategyEnum.valueOf(type);
        String className = loadSaveStrategyEnum.getClassName();

        //een array maken opgevuld met de types van de meegegeven parameters uit args
        Class <?> [] dataTypes = new Class[args.length];
        int index = 0;
        for (Object object:args){
            dataTypes[index] = object.getClass();
            index++;
        }

        //een object aanmaken met behulp van een constructor met paramaters gelijk aan deze uit de dataTypes array
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(dataTypes);
            instance = (LoadSaveStrategy) constructor.newInstance(args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (instance == null) System.out.println("instance is null");
        return instance;
    }
}


