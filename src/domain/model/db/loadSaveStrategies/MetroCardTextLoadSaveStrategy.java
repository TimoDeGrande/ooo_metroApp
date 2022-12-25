package domain.model.db.loadSaveStrategies;

import domain.model.MetroCard;
import domain.model.db.utilities.TextLoadSaveTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    public ArrayList<MetroCard> load(String filename) {
        ArrayList<MetroCard> all = new ArrayList<>();
        try {
            File myObj = new File("src/bestanden/" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(";");
                int id = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1].split("#")[0]);
                int year = Integer.parseInt(data[1].split("#")[1]);
                int available = Integer.parseInt(data[2]);
                int used = Integer.parseInt(data[3]);
                all.add(new MetroCard(id, month, year, available, used));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return all;
    }

}
