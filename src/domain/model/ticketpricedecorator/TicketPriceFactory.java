package domain.model.ticketpricedecorator;

import domain.model.MetroCard;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class TicketPriceFactory {
    public static TicketPrice createTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard metroCard) {
        TicketPrice res = new BasisTicketPrice();

        if(is24Min);
        if (is64Plus) res = new Age64PlusDiscount(res);
        if (isStudent) res = new StudentDiscount(res);
        if (metroCard.getTotalUsedRides() > 50) res = new FrequentTravellerDiscount(res);

        //read from properties to get all active discounts
        ArrayList<String> allDiscounts = TicketPriceFactory.getAllActiveDiscounts();
        //for every discount, nest new constructor
        for (String disType : allDiscounts) {
             res = wrapTicketPrice(disType, res);
        }

        res.setMetroCard(metroCard);
        return res;
    }

    private static TicketPrice wrapTicketPrice(String disType, TicketPrice ticketPrice) {
        //parse to Enum
        TicketPriceDiscountEnum type = TicketPriceDiscountEnum.valueOf(disType);
        //make object of this class
        String path = type.getPath();
        TicketPrice instance;
        try{
            Class clazz = Class.forName(path);
            Constructor<?> constructor = clazz.getConstructor(TicketPrice.class);
            instance = (TicketPrice) constructor.newInstance(ticketPrice);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static ArrayList<String> getAllActiveDiscounts() {
        ArrayList<String> activeDiscounts = new ArrayList<>();
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("src/bestanden/application.properties");
            properties.load(is);

            activeDiscounts.addAll(Arrays.asList(properties.getProperty("activeDiscounts").split(",")));
            is.close();


        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return activeDiscounts;
    }
}
