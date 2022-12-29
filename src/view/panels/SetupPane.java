package view.panels;

import domain.controller.MetroStationViewController;
import domain.model.MetroEventsEnum;
import domain.model.MetroFacade;
import domain.model.MetroGate;
import domain.model.MetroStation;
import domain.model.ticketpricedecorator.TicketPriceDiscountEnum;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.awt.windows.WPrinterJob;
import view.MetroStationView;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SetupPane extends GridPane {

    private String selectedFormat;
    private final static String propertiesPath = "src/bestanden/application.properties";
    private TicketPriceDiscountEnum[] possibleDiscounts = TicketPriceDiscountEnum.class.getEnumConstants();
    private ArrayList<TicketPriceDiscountEnum> selectedDiscounts = new ArrayList<>();
    private ArrayList<String> activeDiscounts = new MetroFacade().getMetroTicketDiscountList();
    private MetroFacade facade;



    public SetupPane(MetroFacade facade){
        this.facade = facade;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        try{
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(propertiesPath);
            properties.load(inputStream);
            String currFormat = properties.getProperty("bestandformaat");
            Text currentformattext = new Text();
            currentformattext.setText(String.format("Geselecteerde formaat: %s. Als u dit wil wijzigen kan u de knoppen hieronder gebruiken", currFormat));
            this.add(currentformattext, 0,0,2,1);
            this.setOptions("Update");
        } catch (IOException e) {
            Text error = new Text();
            error.setText("Problemen bij het vinden van de vorige properties file, kies het formaat opnieuw");
            this.add(error, 0,0,2,1);
            this.setOptions("Save");
        }
        //Kies welke kortingen
        int i = 3;
        for(TicketPriceDiscountEnum discount: possibleDiscounts){
            CheckBox discountBox = new CheckBox();
            discountBox.setText(discount.toString());
            if(activeDiscounts.contains(discount.name())){
                discountBox.setSelected(true);
                selectedDiscounts.add(discount);
            }else{
                discountBox.setSelected(false);
                selectedDiscounts.remove(discount);
            }
            discountBox.setOnAction(event -> {
                if (discountBox.isSelected()) {
                    selectedDiscounts.add(discount);
                } else {
                    selectedDiscounts.remove(discount);
                }
            });
            this.add(discountBox, 0, i, 1,1);
            i++;
        }
        Button saveDicounts = new Button();
        saveDicounts.setText("Save discounts");
        saveDicounts.setOnAction(event -> this.saveDiscounts());
        this.add(saveDicounts, 0, i, 1, 1);

        //Kies aantal gates
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        for (int num = 2; num <= 8; num++) {
            choiceBox.getItems().add(num);
        }
        Button setGateAmountBut = new Button("Kies aantal gates");
        setGateAmountBut.setOnAction(event -> chooseGateAmount(choiceBox));
        this.add(choiceBox, 0, i+=1, 1, 1);
        this.add(setGateAmountBut, 0, i+=1, 1, 1);

    }

    private void chooseGateAmount(ChoiceBox<Integer> amount) {
        System.out.println(this.facade.getMetroGateAmount());
        try{
            int selectedValue = amount.getValue();
            this.facade.updateMetroGatesAmount(selectedValue);
            this.facade.updateGates();
            System.out.println(this.facade.getMetroGateAmount());
        }catch (NullPointerException e){
            System.out.println("fdgdfg");
        }
    }

    private void setOptions(String option){
        Button excel = new Button();
        excel.setText("EXCEL");
        excel.setOnAction(event -> this.setSelectedFormat("EXCEL"));
        Button tekst = new Button();
        tekst.setText("TEXT");
        tekst.setOnAction(event -> this.setSelectedFormat("TEXT"));
        Button save = new Button();
        save.setText(option);
        save.setOnAction(event -> this.saveFormat(event));
        this.add(excel,0,1,1,1);
        this.add(tekst,1,1,1,1);
        this.add(save, 0,2,2,1);
    }

    private void saveDiscounts(){
        if(this.selectedDiscounts != null){
            try {
                Properties properties = new Properties();
                FileInputStream inputStream = new FileInputStream(propertiesPath);
                properties.load(inputStream);

                properties.setProperty("activeDiscounts", selectedDiscounts.stream()
                        .map(TicketPriceDiscountEnum::name)
                        .collect(Collectors.joining(",")));
                FileOutputStream outputStream = new FileOutputStream(propertiesPath);
                properties.store(outputStream, "");
            }
            catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    private void setSelectedFormat(String selectedFormat){
        this.selectedFormat = selectedFormat;
    }
    private void saveFormat(ActionEvent event){
        if(this.selectedFormat != null){
            try {
                Properties properties = new Properties();

                InputStream is = new FileInputStream(propertiesPath);
                properties.load(is);

                properties.setProperty("bestandformaat", this.selectedFormat);
                FileOutputStream os = new FileOutputStream(propertiesPath);
                properties.store(os, "");
            }
            catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    private void editDiscounts(){

    }
}
