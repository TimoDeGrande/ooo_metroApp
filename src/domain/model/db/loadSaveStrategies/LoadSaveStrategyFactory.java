package domain.model.db.loadSaveStrategies;

import sun.security.util.PendingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class LoadSaveStrategyFactory {

    private final static String location = "domain.model.db.loadSaveStrategies.";

    public static LoadSaveStrategy createLoadSaveStrategy(String type, Object... args) {
        LoadSaveStrategy instance = null;

        //opzoeken klassenaam in enum
        LoadSaveStrategyEnum loadSaveStrategyEnum;
        try {
            loadSaveStrategyEnum = LoadSaveStrategyEnum.valueOf(type);
        } catch (IllegalArgumentException | NullPointerException e) {
            //default: TEXT
            System.out.println("Could not find '" + type + "' in LoadSaveStratEnum... Using default: TEXT");
            loadSaveStrategyEnum = LoadSaveStrategyEnum.valueOf("TEXT");
        }
        String className = loadSaveStrategyEnum.getClassName();

        //een array maken opgevuld met de types van de meegegeven parameters uit args
        Class<?>[] dataTypes = new Class[args.length];
        int index = 0;
        for (Object object : args) {
            dataTypes[index] = object.getClass();
            index++;
        }

        //een object aanmaken met behulp van een constructor met paramaters gelijk aan deze uit de dataTypes array
        try {
            Class<?> clazz = Class.forName(location + className);
            Constructor<?> constructor = clazz.getConstructor(dataTypes);
            instance = (LoadSaveStrategy) constructor.newInstance(args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (instance == null) System.out.println("instance is null");
        return instance;
    }

    public static LoadSaveStrategy createLoadSaveStrategy(Object... args) {
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("src/bestanden/application.properties");
            properties.load(is);

            String dbTypeName = properties.getProperty("bestandformaat");
            is.close();

            return LoadSaveStrategyFactory.createLoadSaveStrategy(dbTypeName);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


