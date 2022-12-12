package domain.model.db.loadSaveStrategies;

import sun.security.util.PendingException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface LoadSaveStrategy <K,V> {
    void save(Map<K,V> map, File file)throws BiffException, IOException, WriteException;
    Map<K,V> load() throws BiffException, IOException;
}
