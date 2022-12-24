package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.TextLoadSaveTemplate;
import sun.security.util.PendingException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MetroCardTextLoadSaveStrategy extends TextLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public void save(String filename, ArrayList<MetroCard> cards) {
        try {
            FileWriter out = new FileWriter("src/bestanden/" + filename);
            for (MetroCard card : cards) {
                String string = card.getCardID() + ";" + card.getMonth() + "#" + card.getYear() + ";" + card.getAvailableRides() + ";" + card.getTotalUsedRides() + "\n";
                out.write(string);
            }
            out.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        throw new PendingException("Implement me.");
    }

}
