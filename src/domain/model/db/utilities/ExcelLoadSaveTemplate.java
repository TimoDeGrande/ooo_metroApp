package domain.model.db.utilities;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class ExcelLoadSaveTemplate<K, V> {
    public final static String filename = "src/bestanden/metrocards.xls";
    ExcelPlugin excelPlugin = new ExcelPlugin();

    public final void save(File file, Map<K, V> map) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for (V v : map.values()) {
            ArrayList<String> obj = new ArrayList<>(Arrays.asList(maakSchrijfbareString(v).split(";")));
            data.add(obj);
        }
        try {
            this.excelPlugin.write(file, data);
        } catch (BiffException | WriteException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public final Map<K,V> load(File file) throws IOException {
        Map<K, V> returnMap = new HashMap<>();
        try {
            ArrayList<ArrayList<String>> data = excelPlugin.read(file);
            for(ArrayList<String> obj : data) {
                V element = maakObject(obj.toArray(new String[0]));
                K key = getKey(obj.toArray(new String[0]));
                returnMap.put(key, element);
            }

        } catch (BiffException e) {
            throw new RuntimeException(e);
        }
        return returnMap;
    }

    protected abstract V maakObject(String[] tokens);

    protected abstract String maakSchrijfbareString(V v);

    protected abstract K getKey(String[] tokens);


}
