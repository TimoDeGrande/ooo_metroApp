package view.panels;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.*;
import java.util.Properties;

public class SetupPane extends GridPane {

    private String selectedFormat;
    private final static String propertiesPath = "src/bestanden/application.properties";

    public SetupPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        try{
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(propertiesPath);
            properties.load(inputStream);
            String currFormat = properties.getProperty("bestandformaat");

            //als er in de properties file kortingen staan moet je onderstaande lijn verwijderen en de comments weg doen
            String[] discounts = {"a", "b", "c"};
            //String[] discounts = properties.getProperty("actievekortingen").split(",");
            Text currentformattext = new Text();
            currentformattext.setText(String.format("Geselecteerde formaat: %s. Als u dit wil wijzigen kan u de knoppen hieronder gebruiken", currFormat));
            this.add(currentformattext, 0,0,2,1);
            this.setOptions("Update", discounts);
        } catch (IOException e) {
            Text error = new Text();
            error.setText("Problemen bij het vinden van de vorige properties file, kies het formaat opnieuw");
            this.add(error, 0,0,2,1);
            this.setOptions("Save", null);
        }

    }

    private void setOptions(String option, String[] discounts){
        Button excel = new Button();
        excel.setText("EXCEL");
        excel.setOnAction(event -> this.setSelectedFormat("excel"));
        Button tekst = new Button();
        tekst.setText("TEXT");
        tekst.setOnAction(event -> this.setSelectedFormat("tekst"));
        Button save = new Button();
        save.setText(option);
        save.setOnAction(event -> this.saveFormat(event));
        this.add(excel,0,1,1,1);
        this.add(tekst,1,1,1,1);
        this.add(save, 0,2,2,1);

        for(int i = 0; i < discounts.length; i++){
            CheckBox x = new CheckBox();
            x.setText(discounts[i]);
            int finalI = i;
            x.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue){
                    setActiveDiscount(discounts[finalI]);
                }
                else{
                    rmActiveDiscount(discounts[finalI]);
                }
            });
            this.add(x, 0, 3 + i, 1, 1);
        }
    }

    private void setActiveDiscount(String discount){
        //hier ding toevoegen
        System.out.println(String.format("korting '%s' geselecteerd", discount));
    }
    private void rmActiveDiscount(String discount){
        //hier ding toevoegen
        System.out.println(String.format("korting '%s' gedeselecteerd", discount));
    }

    private void setSelectedFormat(String selectedFormat){
        this.selectedFormat = selectedFormat;
    }
    private void saveFormat(ActionEvent event){
        if(this.selectedFormat != null){
            try {
                Properties properties = new Properties();
                properties.setProperty("bestandformaat", this.selectedFormat);
                FileOutputStream outputStream = new FileOutputStream(propertiesPath);
                properties.store(outputStream, "");
            }
            catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }
}
