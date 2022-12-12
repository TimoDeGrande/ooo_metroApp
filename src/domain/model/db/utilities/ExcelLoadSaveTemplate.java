package domain.model.db.utilities;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public abstract class ExcelLoadSaveTemplate <K,V> {
    ExcelPlugin excelPlugin = new ExcelPlugin();

    public void save(Map<K,V> map, File file) throws BiffException, IOException, WriteException {
        excelPlugin.write(file,mapToArraylist(map));
    }

    private ArrayList<ArrayList<String>> mapToArraylist(Map<K,V> map) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        for(V value : map.values()){
            ArrayList<String> l = new ArrayList<>(Arrays.asList(value.toString().split(",")));
            list.add(l);

        }

        return list;
    }


    public Map<K,V> load(File file) throws BiffException, IOException {
        return listArrayToMap(excelPlugin.read(file));
    }

    private Map<K,V> listArrayToMap(ArrayList<ArrayList<String>> list) {
        Map<K,V> map = new TreeMap<>();
        for (ArrayList<String> value :list) {
            V element = maakObject(value.toArray(new String[0]));
            K key = getKey(value.toArray(new String[0]));
            map.put(key,element);
        }

        return map;
    }


    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);
}
