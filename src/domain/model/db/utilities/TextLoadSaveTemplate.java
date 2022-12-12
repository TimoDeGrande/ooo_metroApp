package domain.model.db.utilities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class TextLoadSaveTemplate<K,V>{

    public final Map<K,V> load(File file) throws IOException {
        Map<K,V> returnMap = new HashMap<K,V>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(",");
                V element = maakObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);

    public final void save(Map<K,V> map, File file) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (V v : map.values()) {
                writer.println(v);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Fout bij het wegschrijven");
        }
    }
}
