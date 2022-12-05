package domain.model.db.utilities;

import domain.model.MetroCard;
import sun.security.util.PendingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class TextLoadSaveTemplate {

    private ArrayList<MetroCard> metroCards = new ArrayList<>();

    public void load(){
        ArrayList<MetroCard> returnMap = new ArrayList<>();
        try{
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get("src/bestanden/metrocards.txt"), StandardCharsets.UTF_8);
            for(String line: lines) {
                String[] tokens = line.trim().split(";");
                int month = Integer.parseInt(tokens[1].split("#")[0]);
                int year = Integer.parseInt(tokens[1].split("#")[1]);
                returnMap.add(new MetroCard(Integer.parseInt(tokens[0]),month, year, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
            }
        }
        catch (IOException exc){
            exc.printStackTrace();
        }
        this.metroCards = returnMap;
    }
}
