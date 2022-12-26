package domain.model.db.utilities;

import domain.model.MetroCard;
import sun.security.util.PendingException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class TextLoadSaveTemplate<K,V> {
    public final static String filename = "src/bestanden/metrocards.txt";

    public final void save(File file, Map<K, V> map) {
        try {
            FileWriter out = new FileWriter(file.getPath());
            for (V val : map.values()) {
                String writeable = maakSchrijfbareString(val) + "\n";
                out.write(writeable);
            }
            out.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public final Map<K,V> load(File file) throws IOException {
        Map<K,V> returnMap = new HashMap<K,V>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(";");
                V element = maakObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }

    protected abstract V maakObject(String[] tokens);

    protected abstract String maakSchrijfbareString(V v);

    protected abstract K getKey(String[] tokens);
}
